Feature: Grading Module

  Background:
    Given User navigates to the "https://test.mersys.io/" page
    And User logs in with valid credentials

  @Regression
  Scenario: Student should be able to verify that buttons on the grading page are active
    When User navigates to "Grading" page
    Then User verifies being successfully redirected to the "Grading" page
    And User verifies that the "Course Grade" button on the page is visible and clickable
    Then User verifies that the course grades are successfully displayed in the list
    And User verifies that the "Student Transcript" button on the page is visible and clickable
    # And User verifies that the "Transcript By Subject" button on the page is visible and clickable
    # BUG: Commented out because the "Transcript By Subject" button is not present in the UI.
    # And User verifies that the "Transcript By Subject" button on the page is visible and clickable