package ru.stqa.selenium.litercart.pajeobjects.app;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.stqa.selenium.litercart.pajeobjects.pages.CartPage;
import ru.stqa.selenium.litercart.pajeobjects.pages.ItemPage;
import ru.stqa.selenium.litercart.pajeobjects.pages.MainPage;

public class Application {

  private WebDriver driver;
  private WebDriverWait wait;
  public MainPage mainPage;
  public CartPage cartPage;
  public ItemPage itemPage;

  public Application() {

    WebDriverManager.firefoxdriver().setup();
    driver = new FirefoxDriver();
    wait = new WebDriverWait(driver, 10);
    mainPage = new MainPage(driver);
    cartPage = new CartPage(driver);
    itemPage = new ItemPage(driver);

  }

  @After
  public void quit() {
    driver.quit();
  }

}
