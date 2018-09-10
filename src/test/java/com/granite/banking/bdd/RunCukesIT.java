package com.granite.banking.bdd;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        format = "pretty",
        tags = "@UserLogin",
        features = "src/test/resources/com/granite/banking/bdd/api/features",
        glue = {"com.granite.banking.bdd.api.steps", "com.granite.banking.bdd.api.hooks"})
public class RunCukesIT {
}
