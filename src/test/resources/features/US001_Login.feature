@Regression
Feature: Authentication - Login

  Background:
    Given User navigates to the "https://test.mersys.io/" page

  @Smoke
  Scenario: User logs in with valid credentials
    When User logs in with valid credentials
    Then User should be successfully logged in and redirected to the homepage

  @Negative
  Scenario: User cannot log in with invalid credentials
    When User enters invalid username or invalid password
    Then User should see an error message regarding invalid credentials
