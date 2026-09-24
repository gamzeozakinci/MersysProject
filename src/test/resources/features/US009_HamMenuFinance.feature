@Regression
Feature: Finance - View payments

  Background:
    Given User navigates to the "https://test.mersys.io/" page
    And User logs in with valid credentials

  Scenario: User sees the details of payments
    When User clicks hamburger menu
    And User clicks "My finance" from "Finance" option
    And User finds his/her name and clicks
    And User clicks "Fee/Balance Detail"
    Then User should be able to see the details of payments
