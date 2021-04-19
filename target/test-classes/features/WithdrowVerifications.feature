Feature: Withdraw Verification

  Background:
    Given user goes to the main page
    When user logs in with valid credentials

  Scenario: Welcome Name verification
    Then user should see "Welcome Terminator" message

  Scenario: error verification
  When user press on Withdraw button
  And user press Submit button
  Then user should be able to see error message "Please select an item in the list"


#  Functionalities:
#  A. User should be able to select an any savings or checking accounts for the deposit.
#  Submitting deposit with unselected account, should throw an error: “Please select an item in the list.”
#  B. User should be able to enter deposit amount: Deposit amount must be greater than 0.00.
#  Submitting deposit without an amount, should throw an error: “Please fill out this field.”
#  Submitting deposit amount equal to 0.00, should throw an error: “The deposit amount ($entered amount) must be greater than $0.00"
#  C. After a successful deposit, User will be redirected to account page and be able to see that entered amount has been applied to selected account balance. eg. Selected account: My checking account: balance $0.00, Deposit amount: $20.50. My checking account: balance $20.50
#  User story #2: As a User, I want to be able to withdraw money from my account