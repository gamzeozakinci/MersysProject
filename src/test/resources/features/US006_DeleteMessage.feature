@Regression
Feature: Messaging - Delete a sent message

  Background:
    Given User navigates to the "https://test.mersys.io/" page
    And User logs in with valid credentials

  @Smoke @wip
  Scenario: User deletes a sent message from the Outbox
    When User clicks on the "Outbox" button
    And User selects a sent message
    And User clicks on the Move to Trash icon for a sent message
    Then User should see a deletion confirmation pop-up on the screen
    And User should see a "Success" message on the screen
