package ru.stqa.selenium.litercart;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import ru.stqa.selenium.litercart.entity.Product;

import java.util.List;

import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;

public class CheckProductPageTest extends TestBase {

  @Test
  public void checkProductTest() {

    driver.navigate().to("http://localhost/litecart/");
    wait.until(titleIs("Online Store | My Store"));

    List<WebElement> productBoxes = driver.findElements(By.cssSelector("div#box-campaigns.box li.product"));
    Assert.assertTrue("No products found in block Campaigns", productBoxes.size() > 0);

    WebElement productBox = productBoxes.get(0); // get first product LI element from main page
    String mainPageProductName = productBox.findElement(By.cssSelector("div.name")).getText();

    Product mainPageProduct = new Product(mainPageProductName, productBox); // create product object from main page
    String productUrl = productBox.findElement(By.cssSelector("a.link")).getAttribute("href");

    driver.navigate().to(productUrl);
    productBox = driver.findElement(By.cssSelector("div#box-product.box")); // get product from product page

    String productPageProductName = findElementByCssSelector(productBox, "h1.title").getText();
    Product productPageProduct = new Product(productPageProductName, productBox); // create product object from product page
    // checks from here
    Assert.assertEquals("Names of products are not equal", mainPageProduct.getName(), productPageProduct.getName());
    Assert.assertEquals("Regular prices are not equal",
            mainPageProduct.getRegularPrice().getValue(), productPageProduct.getRegularPrice().getValue());
    Assert.assertEquals("Campaign prices are not equal",
            mainPageProduct.getCampaignPrice().getValue(), productPageProduct.getCampaignPrice().getValue());
    // checks for main page
    Assert.assertEquals("Regular price from main page is not crossed out",
            mainPageProduct.getRegularPrice().getTagName(), "s");
    Assert.assertTrue("Regular price color from main page is not gray",
            (mainPageProduct.getRegularPrice().getColor().getColor().getRed() == mainPageProduct.getRegularPrice().getColor().getColor().getGreen()
                    && mainPageProduct.getRegularPrice().getColor().getColor().getRed() == mainPageProduct.getRegularPrice().getColor().getColor().getBlue()));
    Assert.assertEquals("Campaign price from main page is not bold",
            mainPageProduct.getCampaignPrice().getTagName(), "strong");
    Assert.assertTrue("Campaign price color from main page is not red",
            (mainPageProduct.getCampaignPrice().getColor().getColor().getGreen() == 0
                    && mainPageProduct.getCampaignPrice().getColor().getColor().getBlue() == 0));
    Assert.assertTrue("Campaign price is not bigger then regular on main page",
            mainPageProduct.getCampaignPrice().getFontSize() > mainPageProduct.getRegularPrice().getFontSize());
    // checks for product page
    Assert.assertEquals("Regular price from product page is not crossed out",
            productPageProduct.getRegularPrice().getTagName(), "s");
    Assert.assertTrue("Regular price color from product page is not gray",
            (productPageProduct.getRegularPrice().getColor().getColor().getRed() == productPageProduct.getRegularPrice().getColor().getColor().getGreen()
                    && productPageProduct.getRegularPrice().getColor().getColor().getRed() == productPageProduct.getRegularPrice().getColor().getColor().getBlue()));
    Assert.assertEquals("Campaign price from product page is not bold",
            productPageProduct.getCampaignPrice().getTagName(), "strong");
    Assert.assertTrue("Campaign price color from product page is not red",
            (productPageProduct.getCampaignPrice().getColor().getColor().getGreen() == 0
                    && productPageProduct.getCampaignPrice().getColor().getColor().getBlue() == 0));
    Assert.assertTrue("Campaign price is not bigger then regular on product page",
            productPageProduct.getCampaignPrice().getFontSize() > productPageProduct.getRegularPrice().getFontSize());
  }



}
