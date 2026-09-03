Feature: Profile theme change

  Background:
    Given User Opens the website.
    And Logs in to website
    # TODO: Login testi yazılınca burası düzeltilecek.


  Scenario: User successfully changes the theme to "Purple"
    When User clicks settings on profile dropdown menu.
    And User changes default theme to purple.
    Then User confirms that 'purple' theme is applied.
    And User must see the success message when clicked 'save' button.

  Scenario: User successfully changes the theme to "Dark Purple"
    When User clicks settings on profile dropdown menu.
    And User changes default theme to dark purple.
    Then User confirms that 'dark purple' theme is applied.
    And User must see the success message when clicked 'save' button.

  Scenario: User successfully changes the theme to "Indigo"
    When User clicks settings on profile dropdown menu.
    And User changes default theme to indigo.
    Then User confirms that 'indigo' theme is applied.
    And User must see the success message when clicked 'save' button.
