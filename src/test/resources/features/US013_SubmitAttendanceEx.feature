Feature: Submit Attendance

  Background:
    Given User Opens the website.
    And Logs into website.

  Scenario: User successfully adds a attendance excuse
    When User clicks 'Attendance' mainpage header.
    And User opens 'Attendance Excuses' screen and clicks 'Add Excuse'.
    Then User adds a description for the excuse.
    And User adds a file to support the excuse and clicks 'send'.
    And User must be able to see the confirm message.