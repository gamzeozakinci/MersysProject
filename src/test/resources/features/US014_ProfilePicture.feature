@Regression @NoCI
Feature: Profile - Change the profile picture

  Background:
    Given User navigates to the "https://test.mersys.io/" page
    And User logs in with valid credentials

  Scenario: User uploads and changes the profile picture
    When User clicks settings on profile dropdown menu
    And User clicks on the profile picture
    Then Profile Photo window should be displayed
    When User selects a profile picture
    Then User should see the uploaded image size
    When User clicks the Upload button
    And User closes the Profile Photo window
    And User clicks the Save button
    Then User should see "Profile successfully updated" message
