package ru.stqa.selenium.litercart.entity;

import lombok.Data;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.Color;
import ru.stqa.selenium.litercart.TestBase;

@Data
public class Price extends TestBase {

  private String tagName;
  private String value;
  private Color color;
  private double fontSize;

  public Price(WebElement priceWrapper, String priceClassName) {

    WebElement priceTag = findElementByCssSelector(priceWrapper, "." + priceClassName);

    if (priceTag != null) {
      tagName = priceTag.getTagName();
      value = priceTag.getText();
      color = parseColor(priceTag.getCssValue("color"));
      fontSize = parseFontSize(priceTag.getCssValue("font-size"));
    }

  }

  private Color parseColor(String colorStr) {

    if (colorStr == null || colorStr.isEmpty()) {
      return null;
    }

    if (colorStr.contains("rgba")) {
      int comma1ix = colorStr.indexOf(',');
      int red = Integer.parseInt(colorStr.substring(5, comma1ix));
      int comma2ix = colorStr.indexOf(',', comma1ix + 1);
      int green = Integer.parseInt(colorStr.substring(comma1ix + 1, comma2ix).trim());
      int comma3ix = colorStr.indexOf(',', comma2ix + 1);
      int blue = Integer.parseInt(colorStr.substring(comma2ix + 1, comma3ix).trim());
      int alpha = Integer.parseInt(colorStr.substring(comma3ix + 1, colorStr.indexOf(')')).trim());
      return new Color(red, green, blue, alpha);
    } else if (colorStr.contains("rgb")) {
      int comma1ix = colorStr.indexOf(',');
      int red = Integer.parseInt(colorStr.substring(4, comma1ix));
      int comma2ix = colorStr.indexOf(',', comma1ix + 1);
      int green = Integer.parseInt(colorStr.substring(comma1ix + 1, comma2ix).trim());
      int blue = Integer.parseInt(colorStr.substring(comma2ix + 1, colorStr.indexOf(')')).trim());
      return new Color(red, green, blue, 1);
    }

    return null;
  }

  private double parseFontSize(String fontSizeStr) {

    double val;

    try {
      val = Double.parseDouble(fontSizeStr.substring(0, fontSizeStr.length() - 2)); // cut "px"
    } catch (NumberFormatException ex) {
      val = 0;
    }

    return val;
  }
}

