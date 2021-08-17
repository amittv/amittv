Feature: Test API Requests

  Background:
    Given Project configurations are loaded

  @test
  Scenario: Verify List users API
    When I call list users api
    Then Verify the response contains list of users with details
    Then Verify the response status and "List Users" json schema

  Scenario: Verify Single User API
    When I call Single user api
    Then Verify the response contains data of single user
    Then Verify the response status and "Single User" json schema

  Scenario: User Not found API
    When I call Single User API and user doesn't exist
    Then Verify the response status and "Single User Not Exist" json schema

  Scenario: Verify List users Resource API
    When I call list resource api
    Then Verify the response contains list of resources with details
    Then Verify the response status and "List Resource" json schema

  Scenario: Verify Single User Resource API
    When I call Single resource api
    Then Verify the response contains data of resource user
    Then Verify the response status and "Single Resource" json schema

  Scenario: User Not found Resource API
    When I call Single resource API and user doesn't exist
    Then Verify the response status and "Single Resource Not Exist" json schema

#

  Scenario: Verify Create User API
    When I create a new user
    Then Verify the response status and "Create User" json schema

  Scenario: Verify Update User API
    When I update an existing user
    Then Verify the response status and "Update User" json schema

  Scenario: Verify Patch User API
    When I update an existing user with Patch
    Then Verify the response status and "Patch User" json schema

  Scenario: Verify the Delete API
    When I delete a user
    Then Verify the response status and "Delete User" json schema

#

  Scenario: Verify Register User API
    When I register a new user
    Then Verify the response status and "Register User" json schema

  Scenario: Verify Register User API fails
    When I register a new user with incorrect details
    Then Verify the response status and "Register User Fail" json schema

  Scenario: Verify LogIn User API
    When I Login a user with email and password
    Then Verify the response status and "Login User" json schema

  Scenario: Verify LogIn User API fails
    When I login a user with missing "email"
    Then Verify the response status and "Login User fail" json schema

  Scenario: Verify LogIn User API fails
    When I login a user with missing "password"
    Then Verify the response status and "Login User fail" json schema

  Scenario: Verify LogIn User API fails
    When I login a user with missing "user"
    Then Verify the response status and "Login User fail" json schema

  Scenario: Verify delay List users API
    When List users api is called with delay
    Then Verify the response status and "List Users Delayed" json schema