Feature: QuickLookup Book Cart2
  This feature file demonstrate the flow of quick search on book cart

  Scenario: Lookup a specific book - Positive
    Given user already logged in
    When user searches for a "HP4" book
    Then user find "HP4" on the search result
