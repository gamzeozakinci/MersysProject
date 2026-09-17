Feature: Homework Submission

  Background:
    Given User navigates to the "https://test.mersys.io/" page
    And User logs in with valid credentials

  Scenario: User sees the submit icon for each homework
    When User navigates to the 'Assignments' page.
    Then User should see a 'Submit' icon on every homework in the Homework list.

  Scenario: User successfully submits a homework
    When User navigates to the 'Assignments' page.
    And User clicks the 'Submit' icon on a homework.
    Then A pop-up text editor should open.
    And User types text into the text editor.
    And User pastes text into the text editor.
    And User inserts an image into the text editor.
    And User inserts a table into the text editor.
    And User clicks 'Attach Files' and adds a file to the homework.
    And User clicks 'Save As Draft'.
    Then User should see a 'Success' message.
    And User clicks the 'Submit' button.
    Then A confirmation pop-up should open.
    When User confirms the submission.
    Then User should see a 'Success' message.

  Scenario: 'Send' button is not active until the draft is saved
    When User navigates to the 'Assignments' page.
    And User clicks the 'Submit' icon on a homework.
    And User types text into the text editor.
    Then The 'Send' button should not be active.

  Scenario: User sees 'New Submission' button on a homework detail page
    When User navigates to the 'Assignments' page.
    And User opens the detail page of a homework.
    Then User should see a 'New Submission' button.

  Scenario: User opens the text editor via 'New Submission' button
    When User navigates to the 'Assignments' page.
    And User opens the detail page of a homework.
    And User clicks the 'New Submission' button.
    Then A pop-up text editor should open.