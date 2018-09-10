@UserLogin
Feature: As a user
  I want to create a user login for users of the system
  So that it can be registered with the bank


  Background:
    Given I am on the user login end point


  Scenario: A client can create a user login
    Given I set the request header "Accept" as "application/json"
    And I set the request header "Content-Type" as "application/json"
    And I have an email of "patrick@sentiapps.com"
    And I have a password of "password"
    When I make a request to post the user login details
    Then I should see the response code is 201
    And I should see the user login id as 1 in the response
    And I should see the user email as "patrick@sentiapps.com" in the response

  Scenario: A client cannot create a user login with an existing email address
    Given I set the request header "Accept" as "application/json"
    And I set the request header "Content-Type" as "application/json"
    And I have an email of "patrick@sentiapps.com"
    And I have a password of "password"
    When I make a request to post the user login details
    And I have an email of "patrick@sentiapps.com"
    And I have a password of "passworder"
    When I make a request to post the user login details
    Then I should see the response code is 400


  Scenario: A client cannot create a user login without an email address
    Given I set the request header "Accept" as "application/json"
    And I set the request header "Content-Type" as "application/json"
    And I have an email of ""
    And I have a password of "password"
    When I make a request to post the user login details
    Then I should see the response code is 400

  Scenario: A client can retrieve a user login
    Given I set the request header "Accept" as "application/json"
    And I have created 1 user login
    When I make a request to get the user login details with the email address "matthew.jones@sentiapps.com"
    Then I should see the response code is 200
    And I should see the user login id as 3 in the response
    And I should see the user email as "matthew.jones@sentiapps.com" in the response


   Scenario: A client cannot retrieve a user login with an email account that does not exist
     Given I set the request header "Accept" as "application/json"
     And I have created 1 user login
     When I make a request to get the user login details with the email address "harry.jones@sentiapps.com"
     Then I should see the response code is 404
     And I should see the error message as "User login details cannot be found with this email address" in the response








