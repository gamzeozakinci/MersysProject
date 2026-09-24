@Regression
Feature: Calendar - Class recording

  Background:
    Given User navigates to the "https://test.mersys.io/" page
    And User logs in with valid credentials

  Scenario: User accesses the recording of a finished class
    When User navigates to "Calendar" page
    And User clicks on the previous week button
    And User clicks on a random completed class
    Then User should see and click the Recording button
    And User should access the class recording
    And User should see and click the Play button
    And User should be able to start watching the class video
