@Regression
Feature: Grading - Grading page buttons

  Background:
    Given User navigates to the "https://test.mersys.io/" page
    And User logs in with valid credentials

  Scenario: User verifies that the buttons on the Grading page are active
    When User navigates to "Grading" page
    Then User verifies being successfully redirected to the "Grading" page
    And User verifies that the "Course Grade" button on the page is visible and clickable
    And User verifies that the course grades are successfully displayed in the list
    And User verifies that the "Student Transcript" button on the page is visible and clickable
