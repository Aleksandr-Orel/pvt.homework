Feature: Test login box

  Scenario: Try to enter into the mail using wrong login
    Given i open main application page
    When i enter wrong login
    Then i see mailbox error

  Scenario: Try to enter into the mail using wrong password
    Given i open main application page
    When i enter right login and wrong password
    Then i see mailbox error

  Scenario: Enter into the mail using wrong login
    Given i open main application page
    When i enter right login and password
    Then i see inbox page