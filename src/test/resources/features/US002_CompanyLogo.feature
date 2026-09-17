Feature: Company logo navigation

  Background:
    Given User navigates to the "https://test.mersys.io/" page
    And User logs in with valid credentials

  Scenario: User clicks company logo and redirects to Techno Study
    Then User should see the company logo
    When User clicks the company logo
    Then User should be redirected to Techno Study website