Feature: QuickSearch Book Cart
  This feature file demonstrate the flow of quick search on book cart

  Scenario: Find a specific book - Positive
    Given user already logged in
    When user searches for a "The Hookup" book
    Then user find "The Hookup" on the search result
