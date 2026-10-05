@Regression
Feature: Finance - Download Fee/Balance report

  Background:
    Given User navigates to the "https://test.mersys.io/" page
    And User logs in with valid credentials

  @Bug
  Scenario: User downloads the Fee/Balance report as Excel or PDF
    When User goes to finance page through hamburger menu
    And User clicks on student name
    And User clicks "Fee/Balance Detail"
    Then User should be able to see the details of payments
    And User should see a button to download the report as Excel or PDF
