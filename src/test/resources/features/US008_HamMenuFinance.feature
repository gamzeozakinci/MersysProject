Feature: Access Finance page from Hamburger Menu

  Background:
    Given User navigates to the "https://test.mersys.io/" page
    And User logs in with valid credentials

  Scenario: User accesses Finance page from Hamburger Menu
    When User clicks hamburger menu.
    And User clicks 'My finance' from 'Finance' option
    Then User should be able to access Finance page.