@Regression
Feature: Finance - Access the Finance page

  Background:
    Given User navigates to the "https://test.mersys.io/" page
    And User logs in with valid credentials

  Scenario: User accesses the Finance page from the hamburger menu
    When User clicks hamburger menu
    And User clicks "My finance" from "Finance" option
    Then User should be able to access Finance page
