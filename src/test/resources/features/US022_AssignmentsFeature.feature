Feature: Assignments - Search, Filter and Sort

  Background:
    Given User navigates to the "https://test.mersys.io/" page
    And User logs in with valid credentials

  Scenario: User sees and uses the Search button on the Assignments page
    When User navigates to the 'Assignments' page.
    Then User should see the 'Search' button.
    When User clicks the 'Search' button without applying any filter.
    Then User should see all assigned tasks listed.

  Scenario: User filters the assignment search results
    When User navigates to the 'Assignments' page.
    And User filters the search by 'Course'.
    And User clicks the 'Search' button.
    Then User should see the filtered assignment results.
    When User filters the search by 'Status'.
    And User clicks the 'Search' button.
    Then User should see the filtered assignment results.
    When User filters the search by 'Semester'.
    And User clicks the 'Search' button.
    Then User should see the filtered assignment results.

  Scenario: User sorts the assignment search results using the Show By dropdown
    When User navigates to the 'Assignments' page.
    Then User should see the 'Show By' dropdown menu.
    When User sorts the results by 'Course' from the 'Show By' dropdown.
    Then User should see the results sorted by 'Course'.
    When User sorts the results by 'Type' from the 'Show By' dropdown.
    Then User should see the results sorted by 'Type'.
    When User sorts the results by 'Date' from the 'Show By' dropdown.
    Then User should see the results sorted by 'Date'.
    When User sorts the results by 'Chart' from the 'Show By' dropdown.
    Then User should see the results sorted by 'Chart'.
