@Regression
Feature: Assignments - Quick actions

  Background:
    Given User navigates to the "https://test.mersys.io/" page
    And User logs in with valid credentials
    And User clicks on the "Assignments" link on the home page
    And User widens the due date filter to list past assignments

  Scenario: User verifies the quick action icons on a random assignment
    Then User should see Information, Submit and Mark it icons on a random assignment
    When User clicks anywhere except the icons on a random assignment
    Then User should access the assignment details page
    And User should see Discussion icon if a discussion exists for the assignment
