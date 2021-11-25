package ru.stqa.selenium.litercart.entity;

import lombok.Data;
import org.openqa.selenium.WebElement;
import ru.stqa.selenium.litercart.TestBase;

@Data
public class Product extends TestBase {

    private String name;
    private Price regularPrice;
    private Price campaignPrice;

    public Product(String name, WebElement productBox) {
      this.name = name;
      WebElement priceWrapper = findElementByCssSelector(productBox, "div.price-wrapper");
      regularPrice = new Price(priceWrapper, "regular-price");
      campaignPrice = new Price(priceWrapper, "campaign-price");
    }

  }

