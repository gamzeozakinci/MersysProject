@Regression
Feature: Finance - Monthly payment

  Background:
    Given User navigates to the "https://test.mersys.io/" page
    And User logs in with valid credentials

  @wip
  Scenario: User pays the monthly fee on the Finance page
    When User goes to finance page through hamburger menu
    And User clicks on student name
    And User clicks on Stripe payment button
    And User clicks on payment fee
    And User fills the card details
    Then User is able to see paid fee
