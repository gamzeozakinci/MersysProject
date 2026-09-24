@Regression
Feature: Assignments - Assignment list and count

  Background:
    Given User navigates to the "https://test.mersys.io/" page
    And User logs in with valid credentials

  Scenario: User sees the number of assignments and accesses their details
    When User hovers over the "Assignments" link on the home page
    Then User verifies that the total number of assigned tasks is displayed
    When User clicks on the "Assignments" link on the home page
    Then User verifies that all assigned tasks are listed
