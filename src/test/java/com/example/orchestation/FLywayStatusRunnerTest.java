package com.example.orchestation;

import com.example.orchestation.Config.FlywayStatusRunner;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class FLywayStatusRunnerTest {

    @Autowired
    private FlywayStatusRunner fLywayStatusRunner;

    @Test
    void testFlywayStatusRunnerExecution() throws Exception {
        fLywayStatusRunner.run();
    }

}
