@BankGuru
Feature: Login to Facebook

  @Register_Login
  Scenario: Verify login page
    Given Open BankGuru Register page
    When Input to email textbox
    And Get username and password info
    And Open Login page
    Then Submit username password to Login page
    And Verify Home page displayed


  @New_Customer
  Scenario Outline: New customer
    Given Open "New Customer" page
    When Input to "Customer Name" textbox with value "<CustomerName>"
    And Click to "Gender" radio button with value "<Gender>"
    And Input to "Date of Birth" textbox with value "<DateOfBirth>"
    And Input to "Address" textarea with value "<Address>"
    And Input to "City" textbox with value "<City>"
    And Input to "Mobile Number" textbox with value "<MobileNumber>"
    And Input to "E-mail" textbox with value "<Email>"
    And Input to "Password" textbox with value "<Password>"
    And Click to "Submit" button
    Then Verify Success message displayed with "Customer Registered Successfully!!!"

    Examples:
    |CustomerName |Gender|DateOfBirth  |Address|City|MobileNumber |Email                      |Password        |
    |Harry Potter |   m  |09/06/1996   |Vietnam| VN | 0123456789  |  harrypotter@gmail.com    |  12345678      |