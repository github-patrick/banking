package com.granite.banking.bdd.api.hooks;

import com.granite.banking.BankingAppApplication;
import cucumber.api.java.Before;
import io.restassured.RestAssured;
import org.junit.After;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ContextConfiguration(classes = BankingAppApplication.class)
@TestPropertySource("classpath:application-test.properties")
public class BaseHook {

    @LocalServerPort
    public int port;

    @Value("${com.granite.banking.bdd.api.baseuri:http://localhost}")
    public String baseUri;

    @Before
    public void setUp() {

        RestAssured.port = port;
        RestAssured.baseURI = baseUri;
    }
}
