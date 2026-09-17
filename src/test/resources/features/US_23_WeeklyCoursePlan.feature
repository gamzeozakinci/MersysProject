Feature: Weekly Course Plan functionality

  Background:
    Given User navigates to the "https://test.mersys.io/" page
    And User logs in with valid credentials

  @Regression
  Scenario: Validate weekly course plan and course status information
    When User navigates to "Calendar" page
    Then User should see the current date and Weekly Course Plan by default
    And User should see course status icons "P", "S", "E", "C"
    And User should see status meanings "Published", "Started", "Completed", "Cancelled"
    And User should see and click "Weekly Course Plan" and "Calendar" links
    And User should see and click Previous, Today and Next navigation buttons
    And User should see and click responsible courses