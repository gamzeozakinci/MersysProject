Feature: Access to payments

  Background:
    Given User Opens the website.
    And Logs into website.

  Scenario: User successfully opens and sees the payments
    When User clicks hamburger menu.
    And User clicks 'My finance' from 'Finance' option
    Then User finds his/her name and clicks.
    And User clicks 'Fee/Balance Detail'.
    And User should be able to see the details of payments.
