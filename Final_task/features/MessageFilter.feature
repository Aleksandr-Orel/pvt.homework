Feature: Test message filter

  Scenario: Test filter of unread messages
    Given i enter into inbox page
    And i open message filter
    And i check unread messages
    Then i see only unread messages

  Scenario: Test filter of messages with flag
    Given i enter into inbox page
    And i open message filter
    And i check messages with flag
    Then i see only messages with flag