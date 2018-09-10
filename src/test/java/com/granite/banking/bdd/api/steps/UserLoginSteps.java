package com.granite.banking.bdd.api.steps;

import com.granite.banking.controller.UserLoginApiController;
import com.granite.banking.dtos.UserLoginDto;
import com.granite.banking.exceptions.ErrorDetail;
import com.granite.banking.exceptions.ErrorMessages;
import com.granite.banking.utils.TestUtils;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.springframework.http.MediaType;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class UserLoginSteps {

    private String email;
    private String password;

    private RequestSpecification requestSpecification;

    private Response response;

    @Given("^I am on the user login end point$")
    public void i_am_on_the_user_login_end_point() throws Throwable {
        RestAssured.basePath = UserLoginApiController.RESOURCE_PATH;
        requestSpecification = given().log().all();
    }

    @Given("^I set the request header \"([^\"]*)\" as \"([^\"]*)\"$")
    public void i_set_the_request_header_as(String header, String value) throws Throwable {
        requestSpecification.header(header,value);
    }

    @Given("^I have an email of \"([^\"]*)\"$")
    public void i_have_an_email_of(String email) throws Throwable {
        this.email = email;
    }

    @Given("^I have a password of \"([^\"]*)\"$")
    public void i_have_a_password_of(String password) throws Throwable {
        this.password = password;
    }

    @When("^I make a request to post the user login details$")
    public void i_make_a_request_to_post_the_user_login_details() throws Throwable {
         response = requestSpecification.body(setAndReturnBody()).post();
    }

    @Then("^I should see the response code is (\\d+)$")
    public void i_should_see_the_response_code_is(int statusCode) throws Throwable {
        response.then().log().all().statusCode(statusCode);
    }

    @Then("^I should see the user login id as (\\d+) in the response$")
    public void i_should_see_the_user_login_id_as_in_the_response(int id) throws Throwable {
        UserLoginDto userLoginDto = response.as(UserLoginDto.class);
        assertEquals(new Long(id),userLoginDto.getUserLoginId());
    }

    @Then("^I should see the user email as \"([^\"]*)\" in the response$")
    public void i_should_see_the_user_email_as(String email) throws Throwable {
        UserLoginDto userLoginDto = response.as(UserLoginDto.class);
        assertEquals(email,userLoginDto.getEmail());
    }



    @Given("^I have created (\\d+) user login$")
    public void i_have_created_user_login(int quantity) throws Throwable {

        for (int i=0 ; i<quantity; i++) {
            RequestSpecBuilder builder = new RequestSpecBuilder();
            builder.setAccept(MediaType.APPLICATION_JSON_VALUE);
            builder.setContentType(MediaType.APPLICATION_JSON_VALUE);
            builder.setBody(TestUtils.getDefaultValidUserLoginDto());
            RequestSpecification requestSpec = builder.build();

            given().spec(requestSpec).
                    when().
                        post().
                    then().
                        statusCode(201);
        }
    }

    @When("^I make a request to get the user login details$")
    public void i_make_a_request_to_get_the_user_login_details() throws Throwable {
        response = requestSpecification.pathParam("userLoginId", 1).
                when().
                    get("/{userLoginId}");
    }

    @When("^I make a request to get the user login details with the email address \"([^\"]*)\"$")
    public void i_make_a_request_to_get_the_user_login_details_with_the_email_address(String email) throws Throwable {
        response = requestSpecification.pathParam("email", email).
                when().
                get("/{email}");
    }

    @Then("^I should see the error message as \"([^\"]*)\" in the response$")
    public void i_should_see_the_error_message_as_in_the_response(String errorMessage) throws Throwable {
        ErrorDetail errorDetail = response.as(ErrorDetail.class);
        assertEquals(ErrorMessages.USER_DETAILS_NOT_FOUND, errorMessage);

    }

    private Map<String,String> setAndReturnBody() {
        Map<String,String> body = new HashMap<>();
        body.put("email", email);
        body.put("password", password);
        requestSpecification.body(body);
        return body;
    }
}
