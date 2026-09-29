@Regression
Feature: Messaging - Send a message

  Background:
    Given User navigates to the "https://test.mersys.io/" page
    And User logs in with valid credentials

  Scenario: User sends a message to a teacher
    When User clicks on the New Message button
    And User closes the error message
    And User clicks on the icon, searches for "Teac" and selects a receiver
    And User enters "Automation Test Subject" as the message subject
    And User types "This is an automation test message. Images and tables can also be added." into the text editor
    And User attaches a sample file from the "Attach Files" section
    And User clicks the "Send" button
    Then User should see a "Success" message on the screen
    And User navigates to the "Outbox" page from the hamburger menu and verifies that the sent message is in the list
