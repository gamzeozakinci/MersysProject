Feature: Top navigation menu functionality

  Background:
    Given User navigates to the "https://test.mersys.io/" page
    And User logs in with valid credentials

  Scenario Outline: User verifies top navigation menu items
    When User navigates to "<menu>" page

    Examples:
      | menu           |
      | Courses        |
      | Calendar       |
      | Attendance     |
      | Assignments    |
      | Grading        |
      | Chat Msg.      |
      | Messages       |
      | Announcements  |
      | Profile        |
      | Hamburger Menu |