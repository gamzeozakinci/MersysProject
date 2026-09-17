Feature: Make a payment

  Background:
    Given User navigates to the "https://test.mersys.io/" page
    And User logs in with valid credentials


  Scenario: User successfully opens and sees the payments
    When User clicks hamburger menu.
    And User clicks 'My finance' from 'Finance' option
    Then User finds his/her name and clicks.
    And User clicks 'Stripe' to make a payment.
    And User chooses 'Pay Amount Due 100.00$' to pay minimum amount.
    And User enters cart info.
    And User clicks 'Stripe' to pay.

