Feature: Access Finance page from Hamburger Menu

  Background:
    Given User Opens the website.
    And Logs into website.

  Scenario: User accesses Finance page from Hamburger Menu
    When User clicks hamburger menu.
    And User clicks 'My finance' from 'Finance' option
    Then User should be able to access Finance page.