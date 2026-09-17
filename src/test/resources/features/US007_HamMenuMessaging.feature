Feature: Messaging Feature

  Background:
    Given User navigates to the "https://test.mersys.io/" page
    And User logs in with valid credentials

  Scenario: User restores a deleted message from Trash
    When User clicks hamburger menu.
    And User clicks 'Messaging' and then 'Trash'.
    And Sets the dates to see messages.
    Then User should see the list of deleted messages.
    And User should see a 'Restore' icon on a deleted message.
    When User clicks the 'Restore' icon on a message.
    Then User should see a 'Success' message confirming the message was restored.

  Scenario: User permanently deletes a message from Trash
    When User clicks hamburger menu.
    And User clicks 'Messaging' and then 'Trash'.
    And Sets the dates to see messages.
    Then User should see the list of deleted messages.
    And User should see a 'Delete' icon on a deleted message.
    When User clicks the 'Delete' icon on a message.
    Then A confirmation pop-up should open before permanent deletion.
    When User confirms the permanent deletion.
    Then User should see a 'Success' message confirming the message was permanently deleted.