Feature: User Authentication Functionality

  Background:
    Given User navigates to the "https://test.mersys.io/" page

  @Smoke @Regression
  Scenario: Successful login with valid credentials
    When User logs in with valid credentials
    Then User should be successfully logged in and redirected to the homepage

  @Negative @Regression
  Scenario: Unsuccessful login with invalid credentials
    When User enters invalid username or invalid password
    Then User should see an error message regarding invalid credentials