Feature: Actions with cart

  Scenario: Add and delete from cart test.
    Given we open 'http://localhost/litecart/en/'
    When  we added 3 items to the cart we open the cart page
    Then  we open the cart page
    And   we delete everything in the cart
