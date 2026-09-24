@Regression
Feature: Attendance - Submit an attendance excuse

  Background:
    Given User navigates to the "https://test.mersys.io/" page
    And User logs in with valid credentials

  Scenario: User adds an attendance excuse
    When User clicks "Attendance" mainpage header
    And User opens "Attendance Excuses" screen and clicks "Add Excuse"
    And User adds a description for the excuse
    And User adds a file to support the excuse and clicks "send"
    Then User must be able to see the confirm message
