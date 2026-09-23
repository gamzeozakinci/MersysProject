Feature: Download Fee/Balance report

  Background:
    Given User navigates to the "https://test.mersys.io/" page
    And User logs in with valid credentials

  Scenario: Student should be able to download Fee/Balance report as Excel or PDF
    Given Users goes to finance page through hamburger menu
    Then User clicks on student name