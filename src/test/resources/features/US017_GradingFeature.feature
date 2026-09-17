Feature: Grading - Print and Download Transcript

  Background:
    Given User navigates to the "https://test.mersys.io/" page
    And User logs in with valid credentials

  Scenario: User views and downloads their Course Grade transcript as PDF
    When User navigates to the 'Grading' page.
    Then User should see a 'Print' icon on the page.
    When User clicks the 'Print' icon.
    Then User should see the transcript document in PDF format.
    And User must be able click and download the document