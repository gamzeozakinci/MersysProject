@Regression
Feature: Grading - Print and download the transcript

  Background:
    Given User navigates to the "https://test.mersys.io/" page
    And User logs in with valid credentials

  Scenario: User views and downloads the Course Grade transcript as PDF
    When User opens the "Grading" page
    Then User should see a "Print" icon on the page
    When User clicks the "Print" icon
    Then User should see the transcript document in PDF format
    And User must be able to click and download the document
