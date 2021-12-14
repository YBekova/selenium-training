package ru.stqa.selenium.litercart.pajeobjects.tests;



public class CucumberTestSteps extends  CucumberTestBase {




    public CucumberTestSteps() {
        Given("^we open 'http://localhost/litecart/en/'$", () -> {
            app.mainPage.open();
        });
        When("we added {int} items to the cart we open the cart page", (Integer itemCount) -> {
            for (int i = 1; i<=itemCount; i++) {
                app.mainPage.chooseFirstItem();
                app.itemPage.addItemToCart();
                app.itemPage.goToMainPage();
            }
        });
        Then("^we open the cart page$", () -> {
            app.cartPage.open();
        });
        And("^we delete everything in the cart$", () -> {
            app.cartPage.clearCart();
        });
    }
}
