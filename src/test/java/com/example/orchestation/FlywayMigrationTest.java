package com.example.orchestation;

import org.flywaydb.core.Flyway;
import org.flywaydb.core.api.MigrationInfo;
import org.flywaydb.core.api.MigrationState;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Standalone Flyway migration test — no Spring context needed.
 *
 * Builds a Flyway instance directly against an H2 in-memory database
 * (PostgreSQL-compatibility mode) to validate that all migration scripts
 * execute cleanly without needing the full application to boot.
 *
 * This test is intentionally lightweight: it exercises only the SQL
 * migration scripts, not the Spring wiring.
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class FlywayMigrationTest {

    private static final String H2_URL =
            "jdbc:h2:mem:flywaytest;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE;" +
            "MODE=PostgreSQL;DEFAULT_NULL_ORDERING=HIGH";

    private Flyway flyway;

    @BeforeAll
    void setUpFlyway() {
        flyway = Flyway.configure()
                .dataSource(H2_URL, "sa", "")
                .locations("classpath:db/migration")
                .baselineOnMigrate(true)
                .baselineVersion("0")
                .load();

        // Run all pending migrations once before all tests
        flyway.migrate();
    }

    @Test
    void flyway_allAppliedMigrations_areSuccessful() {
        MigrationInfo[] applied = flyway.info().applied();

        assertThat(applied)
                .as("At least one migration should have been applied")
                .isNotEmpty();

        for (MigrationInfo info : applied) {
            assertThat(info.getState())
                    .as("Migration V%s (%s) should be SUCCESS or BASELINE, but was %s",
                            info.getVersion(), info.getDescription(), info.getState())
                    .isIn(MigrationState.SUCCESS, MigrationState.BASELINE);
        }
    }

    @Test
    void flyway_noFailedMigrations() {
        long failedCount = Arrays.stream(flyway.info().all())
                .filter(m -> m.getState() == MigrationState.FAILED)
                .count();

        assertThat(failedCount)
                .as("There should be no failed migrations")
                .isZero();
    }

    @Test
    void flyway_noPendingMigrations() {
        long pendingCount = Arrays.stream(flyway.info().all())
                .filter(m -> m.getState() == MigrationState.PENDING)
                .count();

        assertThat(pendingCount)
                .as("All migrations should have been applied — none should remain pending")
                .isZero();
    }

    @Test
    void flyway_currentVersion_isLatest() {
        MigrationInfo current = flyway.info().current();

        assertThat(current)
                .as("A current applied migration version must exist")
                .isNotNull();

        assertThat(current.getVersion().getVersion())
                .as("Current schema version should be 2 (V2__add_workspace_id_to_employee.sql)")
                .isEqualTo("2");
    }

}
