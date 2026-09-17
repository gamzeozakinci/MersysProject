Feature: Assignment Discussion Module

  Background:
    Given User navigates to the "https://test.mersys.io/" page
    And User logs in with valid credentials
    And User clicks on the "Assignments" link on the home page

  @Regression
  Scenario: Student should be able to start a discussion on the assignment page and view the flow
    When User clicks on the "Discussion" icon of a random assignment in the list
    Then User verifies the chat area where they can view past discussions
    And User types "I have a question about this assignment" into the text editor on the Assignment page
    And User attaches a sample file for the assignment from the "Attach Files" section
    And User clicks the "Send" button on the Assignment page
    Then User verifies that a "Success" message is not displayed on the screen
    And User verifies that the sent message, file name, and time information are displayed in the flow