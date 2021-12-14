package ru.stqa.selenium.litercart.pajeobjects.tests;

import io.cucumber.java8.En;
import ru.stqa.selenium.litercart.pajeobjects.app.Application;

public class CucumberTestBase implements En {
    public static ThreadLocal<Application> tlApp= new ThreadLocal<>();
    public Application app;

    public CucumberTestBase() {
        Before(() -> {
            if (tlApp.get()!=null){
                app= tlApp.get();
                return;
            }
            app=new Application();
            tlApp.set(app);
            Runtime.getRuntime().addShutdownHook(new Thread(() -> {app.quit(); app=null;}));});
    }
}
