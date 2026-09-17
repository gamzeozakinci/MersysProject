Feature: Weekly Course Plan Functionality

  Background:
    Given User navigates to the "https://test.mersys.io/" page
    And User logs in with valid credentials

  @Regression
  Scenario: Validate completed course details and tabs
    When User navigates to "Calendar" page
    Then User is able to see class names
    And User clicks on the previous week button
    And User clicks on a completed class
    Then User should see "Information", "Topic", "Attachments", "Recent Events" tabs and confirm they are working