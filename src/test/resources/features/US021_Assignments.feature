@Regression
Feature: Assignments - Homework submission

  Background:
    Given User navigates to the "https://test.mersys.io/" page
    And User logs in with valid credentials

  Scenario: User sees the Submit icon for each homework
    When User opens the "Assignments" page
    And User widens the due date filter to list past assignments
    Then User should see a "Submit" icon on every homework in the Homework list

  Scenario: User submits a homework
    When User opens the "Assignments" page
    And User widens the due date filter to list past assignments
    And User clicks the "Submit" icon on a homework
    Then A pop-up text editor should open
    When User types text into the text editor
    And User pastes text into the text editor
    And User inserts an image into the text editor
    And User inserts a table into the text editor
    And User clicks "Attach Files" and adds a file to the homework
    And User clicks "Save As Draft"
    Then User should see a "Success" message
    When User clicks the "Submit" button
    Then A confirmation pop-up should open
    When User confirms the submission
    Then User should see a "Success" message

  Scenario: User cannot send a homework until the draft is saved
    When User opens the "Assignments" page
    And User widens the due date filter to list past assignments
    And User clicks the "Submit" icon on a homework
    And User types text into the text editor
    Then The "Send" button should not be active

  Scenario: User sees the New Submission button on a homework detail page
    When User opens the "Assignments" page
    And User widens the due date filter to list past assignments
    And User opens the detail page of a homework
    Then User should see a "New Submission" button

  Scenario: User opens the text editor with the New Submission button
    When User opens the "Assignments" page
    And User widens the due date filter to list past assignments
    And User opens the detail page of a homework
    And User clicks the "New Submission" button
    Then A pop-up text editor should open
