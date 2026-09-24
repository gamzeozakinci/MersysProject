@Regression
Feature: Calendar - Completed class details

  Background:
    Given User navigates to the "https://test.mersys.io/" page
    And User logs in with valid credentials

  Scenario: User verifies the details and tabs of a completed class
    When User navigates to "Calendar" page
    Then User is able to see class names
    When User clicks on the previous week button
    And User clicks on a completed class
    Then User should see "Information", "Topic", "Attachments", "Recent Events" tabs and confirm they are working
