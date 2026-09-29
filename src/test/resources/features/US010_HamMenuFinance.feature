@Regression
Feature: Finance - Make a payment

  Background:
    Given User navigates to the "https://test.mersys.io/" page
    And User logs in with valid credentials
#buglı
  Scenario: User makes a payment with Stripe
    When User clicks hamburger menu
    And User clicks "My finance" from "Finance" option
    And User finds his/her name and clicks
    And User clicks "Stripe" to make a payment
    And User chooses "Pay Amount Due 100.00$" to pay minimum amount
    And User enters card info
    And User clicks "Stripe" to pay
    And User able to pay
