package ru.stqa.selenium.litercart.pajeobjects.appmanager;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.stqa.selenium.litercart.pajeobjects.pages.CartPage;
import ru.stqa.selenium.litercart.pajeobjects.pages.ItemPage;
import ru.stqa.selenium.litercart.pajeobjects.pages.MainPage;

public class ApplicationManager {

  public MainPage mainPage;
  public CartPage cartPage;
  public ItemPage itemPage;
  private WebDriver driver;
  private WebDriverWait wait;

  public ApplicationManager() {

    WebDriverManager.chromedriver().setup();
    driver = new ChromeDriver();
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
