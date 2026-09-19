Feature: Upload and change profile picture

  Background:
    Given User navigates to the "https://test.mersys.io/" page
    And User logs in with valid credentials

  Scenario: Student successfully uploads and changes profile picture
    When User clicks settings on profile dropdown menu.
    And User clicks on the profile picture
    Then Profile Photo window should be displayed
    When User selects a profile picture
    Then User should see the uploaded image size
    And User clicks the Upload button
    And User closes the Profile Photo window
    And User clicks the Save button
    Then User should see "Profile successfully updated" message