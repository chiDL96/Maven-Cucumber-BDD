@Login
Feature: Login to Facebook

 #Background:
    #Given Open facebook application

  @no-param
  Scenario: Verify login page
    #Given Open facebook application
    When Enter to Email textbox
    When Enter to Password textbox
    And Closed application

  @param_mark
  Scenario: Verify login page
    #Given Open facebook application
    When Enter to Email textbox with "chidl6996@gmail.com"
    When Enter to Password textbox with "12345678"
    And Closed application

  @param_no_mark
  Scenario: Verify login page
    #Given Open facebook application
    When Enter to Email textbox with chidl96@gmail.com
    When Enter to Password textbox with 12345678
    And Closed application

  @multiple_param
  Scenario: Verify login page
    #Given Open facebook application
    When Enter to Email textbox with "chidl1996@gmail.com" and Password textbox with "12345678"
    And Closed application

  @datatable_step
  Scenario Outline: Verify login page
    #Given Open facebook application
    When Input to Username and Password
      | Username   | Password |
      | chidl0000@gmail.com | 12345678 |
      | chidl1111@gmail.com | 87654321 |
    Then Verify submit info Address and Age
      | Address | Age |
      | Ha Noi  | 27  |
      | Viet Nam  | 21  |
    And Closed application

    Examples:
      | Username            | Password | Address | Age |
      |                     |          |         |     |



  @datatable_scenario
  Scenario Outline: Verify login page
    #Given Open facebook application
    When Input to "<Username>" and "<Password>"
    Then Verify submit info "<Address>" and "<Age>"
    And Closed application

    Examples:
      | Username            | Password | Address | Age |
      | chidl0000@gmail.com | 12345678 | Ha Noi  | 27  |
      | chidl11114@gmail.com | 12345678 | Viet Nam  | 20  |
      | chidl2222@gmail.com | 12345678 | Xa Dan  | 18  |