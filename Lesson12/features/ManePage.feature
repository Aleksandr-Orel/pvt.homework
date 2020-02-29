Feature: Test main page

  Scenario: Move message to spam
    Given I am on main page of email
    When I send message to spam
    Then I can found this message in the spam folder

  Scenario: Write message and send it
    Given I am on main page of email
    When I open write message form
    And I declare destination address of message
    And I write message
    And Send message
    Then I see confirm of sending message