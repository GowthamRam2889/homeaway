package com.homeaway.qa.solution_1.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * Created by urajago on 5/26/16.
 */
public class QADemoRegister {

    private static WebElement element = null;


    //TODO : I am having issues creating a user account with valid credentials.

    public static void Register(WebDriver driver) {

        driver.navigate().to("http://store.demoqa.com/products-page/your-account/");

        driver.findElement(By.xpath("//*[@id=\"meta\"]/ul/li[1]/a")).click();
    }

    public static void userDetails(WebDriver driver) {

        driver.findElement(By.id("user_login")).sendKeys("HaTest");

        driver.findElement(By.id("user_email")).sendKeys("gow@ha.com");
    }


}
