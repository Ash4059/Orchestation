package com.example.orchestation.Config;

import lombok.RequiredArgsConstructor;
import org.flywaydb.core.Flyway;
import org.flywaydb.core.api.MigrationInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class FlywayStatusRunner implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(FlywayStatusRunner.class);

    private final ObjectProvider<Flyway> flywayProvider;


    @Override
    public void run(String... args) throws Exception {
        log.info("==================================================");
        log.info("             FLYWAY MIGRATION STATUS              ");
        log.info("==================================================");

        Flyway flyway = flywayProvider.getIfAvailable();

        if (flyway == null) {
            log.warn("Flyway bean is NOT present in ApplicationContext.");
            log.info("==================================================");
            return;
        }

        MigrationInfo[] migrations = flyway.info().all();

        if (migrations == null || migrations.length == 0) {
            log.warn("No migration scripts detected by Flyway!");
            log.info("==================================================");
            return;
        }

        for (MigrationInfo info : migrations) {
            log.info(String.format("[%s] Version: %-5s | Description: %-30s | Installed On: %s",
                    info.getState(),
                    info.getVersion() != null ? info.getVersion().getVersion() : "N/A",
                    info.getDescription(),
                    info.getInstalledOn() != null ? info.getInstalledOn() : "Pending"
            ));
        }

        MigrationInfo current = flyway.info().current();
        if (current != null) {
            log.info("--------------------------------------------------");
            log.info("Current Database Version: {}", current.getVersion());
            log.info("--------------------------------------------------");
        } else {
            log.warn("Database schema has no applied migrations.");
        }
        log.info("==================================================");
    }
}
