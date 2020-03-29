Feature: Reply to message test

  Scenario: Send reply to message
    Given i sign in inbox page
    When i write test message
    And i open reply to message window
    And i write text of message
    And i send message
    Then i see confirm of sending message

  Scenario: Send reply to message without text
    Given i open inbox page
    When i open reply to message window
    And i send message
    Then i see confirmation of send empty letter
    And i close alert

  Scenario: Send reply to message using wrong destination address
    Given i open inbox page
    When i open reply to message window
    And i enter wrong destination address
    And i send message
    Then i see alert that destination address is wrong
    And i close alert
