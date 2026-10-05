@Regression
Feature: Grading - Grading page tabs

  Background:
    Given User navigates to the "https://test.mersys.io/" page
    And User logs in with valid credentials

  Scenario: User sees the Class Grade and Reports tabs on the Grading page
    When User navigates to "Grading" page
    Then User verifies being successfully redirected to the "Grading" page
    And User verifies that the "Class Grade" tab is visible and clickable
    And User verifies that the course grades are successfully displayed in the list
    And User verifies that the "Reports" tab is visible and clickable
    And User verifies that the "Student Transcripts" section is listed under Reports
