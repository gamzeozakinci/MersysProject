Feature: Hamburger Menu - Messaging Submenu

  Background:
    Given User navigates to the "https://test.mersys.io/" page
    And User logs in with valid credentials

  Scenario: User sees and uses the Messaging submenu links from the Hamburger Menu
    When User clicks hamburger menu.
    And User hovers over the 'Messaging' page.
    Then User should see the 'New Message' page.
    And User should see the 'Inbox' page.
    And User should see the 'Outbox' page.
    And User should see the 'Trash' page.
    When User clicks the 'New Message' page.
    Then User should be navigated to the 'New Message' page.
    When User clicks hamburger menu.
    And User hovers over the 'Messaging' page.
    And User clicks the 'Inbox' page.
    Then User should be navigated to the 'Inbox' page.
    When User clicks hamburger menu.
    And User hovers over the 'Messaging' page.
    And User clicks the 'Outbox' page.
    Then User should be navigated to the 'Outbox' page.
    When User clicks hamburger menu.
    And User hovers over the 'Messaging' page.
    And User clicks the 'Trash' page.
    Then User should be navigated to the 'Trash' page.
