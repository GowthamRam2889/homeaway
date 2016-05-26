package com.homeaway.qa.solution_1.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

/**
 * Created by urajago on 5/26/16.
 */
public class QADemoPage {

    private static WebElement element = null;

    public static void goToHome(WebDriver driver) {

        driver.navigate().to("http://www.store.demoqa.com");
    }

    public static WebElement placeOrder(WebDriver driver) {
        element = driver.findElement(By.name("s"));
        element.sendKeys("iPhone 4S SIM FREE BLACK");
        element.submit();

        driver.findElement(By.name("Buy")).click();

        driver.findElement(By.cssSelector("#fancy_notification_content > a.go_to_checkout")).click();

        return element;
    }

    public static WebElement checkOutPriceAndValidate(WebDriver driver) {
        element = driver.findElement(By.xpath("//*[@id=\"checkout_page_container\"]/div[1]/table/tbody/tr[2]/td[3]/form/input[1]"));

        Assert.assertEquals(element.getAttribute("value"), "1");

        Assert.assertEquals(driver.findElement(By.xpath("//*[@id=\"checkout_page_container\"]/div[1]/table/tbody/tr[2]/td[5]/span/span")).getText(), "$270.00");

        driver.findElement(By.xpath("//*[@id=\"checkout_page_container\"]/div[1]/a/span")).click();

        return element;
    }

    public static void updateAccountDetails(WebDriver driver) {

        driver.findElement(By.id("wpsc_checkout_form_2")).sendKeys("home");

        driver.findElement(By.id("wpsc_checkout_form_3")).sendKeys("Away");

        driver.findElement(By.id("wpsc_checkout_form_4")).sendKeys("10 Downing Street");

        driver.findElement(By.id("wpsc_checkout_form_5")).sendKeys("Austin");

        driver.findElement(By.id("wpsc_checkout_form_6")).sendKeys("Texas");

        driver.findElement(By.xpath("//*[@id=\"current_country\"]/option[233]")).click();

        driver.findElement(By.id("wpsc_checkout_form_8")).sendKeys("00000");

        driver.findElement(By.id("wpsc_checkout_form_9")).sendKeys("1111111111");
    }

    public static void countrySelectionAndPriceValidation(WebDriver driver) {

        //driver.findElement(By.id("current_country")).click();

        driver.findElement(By.xpath("//*[@id=\"current_country\"]/option[233]")).click();

        driver.findElement(By.xpath("//*[@id=\"change_country\"]/input[2]")).sendKeys("colorado");

        driver.findElement(By.xpath("//*[@id=\"change_country\"]/input[4]")).submit();

        element = driver.findElement(By.xpath("//*[@id=\"checkout_total\"]/span"));

        Assert.assertEquals(element.getText(), "$282.00");
    }

    public static void emptyCart(WebDriver driver) {

        driver.findElement(By.xpath("//*[@id=\"header_cart\"]/a")).click();

        driver.findElement(By.xpath("//*[@id=\"checkout_page_container\"]/div[1]/table/tbody/tr[2]/td[6]/form/input[4]")).click();

        element = driver.findElement(By.xpath("//*[@id=\"post-29\"]/div"));

        Assert.assertEquals(element.getText(), "Oops, there is nothing in your cart.");
    }
}
