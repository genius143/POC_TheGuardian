#Author: sandhya.singh5@cognizant.com

@theGuardian
Feature: Validate displayed News is valid on the Guardian site

  @onGoogle
  Scenario: Verify the displayed first news is valid on the Guardian site
    Given user navigates to application
    When user gets the text of first news
    Then navigate to google to validate the news text

  @onTimesOfIndia
  Scenario: Verify the displayed first news is valid on the Guardian site
    Given user navigates to application
    When user gets the text of first news
    Then navigate to Times of India to validate the news text
