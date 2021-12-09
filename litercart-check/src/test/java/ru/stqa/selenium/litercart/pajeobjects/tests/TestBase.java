package ru.stqa.selenium.litercart.pajeobjects.tests;


import org.junit.Before;
import ru.stqa.selenium.litercart.pajeobjects.appmanager.ApplicationManager;

public class TestBase {

  public static ThreadLocal<ApplicationManager> tlApp = new ThreadLocal<>();
  public ApplicationManager app;

  @Before
  public void start() {

    if (tlApp.get() != null) {
      app = tlApp.get();
      return;
    }

    app = new ApplicationManager();
    tlApp.set(app);

    Runtime.getRuntime().addShutdownHook(
            new Thread(() -> {
              app.quit();
              app = null;
            }));
  }

}
