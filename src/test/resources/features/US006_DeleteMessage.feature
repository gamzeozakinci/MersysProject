Feature: Outbox Message Deletion Functionality

  Background:
    Given User navigates to the "https://test.mersys.io/" page
    And User logs in with valid credentials

  @Smoke @Regression
  Scenario: Student successfully deletes a sent message from the Outbox
    Given User clicks on the "Outbox" button
    And User selects a sent message
    When User clicks on the Move to Trash icon for a sent message
    Then User should see a deletion confirmation pop-up on the screen
    Then User should see a "Success" message on the screen