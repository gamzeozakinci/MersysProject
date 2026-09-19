Feature:Montly Patment On Finance Page

  Background:
    Given User navigates to the "https://test.mersys.io/" page
    And User logs in with valid credentials

    Scenario: Student succesfully does a payment fee
      Given Users goes to finance page through hamburger menu
      Then User clicks on student name
      When User clicks on Stribe payment button
      And User clicks on payment fee
      And User fills the card details
      And User is able to see payed fee
