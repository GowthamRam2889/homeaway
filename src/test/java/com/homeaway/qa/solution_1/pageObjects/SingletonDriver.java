package com.homeaway.qa.solution_1.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 * Created by urajago on 5/26/16.
 */

/**
 *  I am making the Driver object Singleton so that there is only one driver instance
 */

public class SingletonDriver {

    private static FirefoxDriver driver;

    private SingletonDriver() {

    }

    public static WebDriver getDriver() {

        if(driver == null)
            driver = new FirefoxDriver();

        return driver;
    }
}
