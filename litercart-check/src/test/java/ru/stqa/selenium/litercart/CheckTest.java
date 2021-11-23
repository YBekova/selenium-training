package ru.stqa.selenium.litercart;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;


public class CheckTest extends TestBase{


  @Test
  public void checkCountriesTest(){


    LoginTest.logInAdmin(TestBase.LOGIN, TestBase.PASSWORD);
    driver.navigate().to("http://localhost/litecart/admin/?app=countries&doc=countries");
    wait.until(titleIs("Countries | My Store"));
    List<WebElement> rows = driver.findElements(By.cssSelector("table.dataTable tr.row"));
    ArrayList<String> hrefs = new ArrayList<>();
    String prevCountry = "";
    for (WebElement row:rows) {
      WebElement countryLink = row.findElement(By.cssSelector("td:nth-child(5) a"));
      String country = countryLink.getText();
      int compare = country.compareToIgnoreCase(prevCountry);
      Assert.assertTrue("Sorting failed on country: "+ country, compare >= 0);
      String numZonesStr = row.findElement(By.cssSelector("td:nth-child(6)")).getText();
      if(Integer.parseInt(numZonesStr) > 0){
        hrefs.add(countryLink.getAttribute("href"));
      }
      prevCountry = country;
    }
    System.out.println(rows.size() + " rows sorted well in table Countries");


    for (String href:hrefs) {
      driver.navigate().to(href);
      wait.until(titleIs("Edit Country | My Store"));
      String country = driver.findElement(By.cssSelector("input[name=name]")).getAttribute("value");
      String cellsSel = "table#table-zones.dataTable tr:not(.header) td:nth-child(3)";
      List<WebElement> cells = driver.findElements(By.cssSelector(cellsSel));
      int zoneCount = cells.size()-1;
      String prevZoneName = "";
      for (int i = 0; i < zoneCount; i++) {
        String zoneName = cells.get(i).getText();
        int compare = zoneName.compareToIgnoreCase(prevZoneName); // current and previous zone names comparing
        Assert.assertTrue("Zone sorting failed on country="+ country + ", zone=" + zoneName + ", href=" + href,
                compare >= 0); // sorting check
        prevZoneName = zoneName;
      }
      System.out.println(zoneCount + " zones sorted well for country=" + country);
    }
  }



  @Test
  public void checkZonesTest(){
    
    LoginTest.logInAdmin(TestBase.LOGIN, TestBase.PASSWORD);
    driver.navigate().to("http://localhost/litecart/admin/?app=geo_zones&doc=geo_zones");
    wait.until(titleIs("Geo Zones | My Store"));
    String selector = "tr.row td:nth-child(3) a";
    List<WebElement> cells = driver.findElements(By.cssSelector(selector));
    ArrayList<String> hrefs = new ArrayList<>();
    for(WebElement cell:cells){
      hrefs.add(cell.getAttribute("href"));
    }
    for(String href:hrefs){
      driver.navigate().to(href);
      wait.until(titleIs("Edit Geo Zone | My Store"));
      String geoZoneName = driver.findElement(By.cssSelector("input[name=name]")).getAttribute("value");
      selector = "table#table-zones.dataTable tr td:nth-child(3) option[selected]";
      cells = driver.findElements(By.cssSelector(selector));
      String prevZoneName = "";
      for(WebElement cell:cells){
        String zoneName = cell.getText();
        int compare = zoneName.compareToIgnoreCase(prevZoneName);
        Assert.assertTrue("Zone sorting failed on Geo zone="+ geoZoneName + ", zone=" + zoneName + ", href=" + href,
                compare >= 0); // sorting check
        prevZoneName = zoneName;
      }
      System.out.println(cells.size() + " zones sorted well for Geo zone=" + geoZoneName);
    }
  }
}

