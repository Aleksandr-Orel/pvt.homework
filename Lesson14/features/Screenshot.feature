Feature: Test inbox page

  Scenario: Write message and send it
    Given i am on main page of email
    When i open write message form
    And i declare destination address of message
    And i write message
    And send message and see confirm
    Then test and close