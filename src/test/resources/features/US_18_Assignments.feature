Feature: Student Assignments Module

  Background:
    Given User navigates to the "https://test.mersys.io/" page
    And User logs in with valid credentials

  @Regression
  Scenario: Student should be able to see the number of assignments and access their details
    Given User hovers over the "Assignments" link on the home page
    Then User verifies that the total number of assigned tasks is displayed
    When User clicks on the "Assignments" link on the home page
    Then User verifies that all assigned tasks are listed