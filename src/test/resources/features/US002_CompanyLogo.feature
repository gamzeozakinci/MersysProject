@Regression
Feature: Navigation - Company logo

  Background:
    Given User navigates to the "https://test.mersys.io/" page
    And User logs in with valid credentials

  Scenario: User is redirected to the Techno Study website by clicking the company logo
    Then User should see the company logo
    When User clicks the company logo
    Then User should be redirected to Techno Study website
