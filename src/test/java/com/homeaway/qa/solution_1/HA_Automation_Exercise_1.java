package com.homeaway.qa.solution_1;

import com.homeaway.qa.solution_1.pageObjects.SingletonDriver;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.homeaway.qa.solution_1.pageObjects.QADemoPage;

import java.util.concurrent.TimeUnit;

/**
 * Created by urajago on 5/23/16.
 */
public class HA_Automation_Exercise_1 {

    private WebDriver driver = null;

    @BeforeClass (alwaysRun = true)
    public void setup() {
        driver = SingletonDriver.getDriver();

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        QADemoPage.goToHome(driver);

        System.out.println(" Successfully navigated to QA Demo home !!");

    }

    @AfterClass (alwaysRun = true)
    public void quit() {
        driver.quit();
    }

    /**
     * This test places the order for the iPhone 4s
     * Adds state, zip and country details
     * Validate the total price after Tax
     */
    @Test (description = "Places an Order for iPhone 4S, adds user country information and validate the total price", priority = 1)
    public void firstTestCase() throws Exception{

        QADemoPage.placeOrder(driver);

        QADemoPage.checkOutPriceAndValidate(driver);

        QADemoPage.countrySelectionAndPriceValidation(driver);
    }

    /**
     * This tests Updates the account details
     */
    @Test (description = "Updating the account details" , dependsOnMethods = "firstTestCase")
    public void secondTestCase() throws Exception{

        QADemoPage.updateAccountDetails(driver);
    }

    /**
     * This test removes the placed iPhone order from the Cart
     */
    @Test (description = "Removing the items from the Cart", dependsOnMethods = "secondTestCase")
    public void thirdTestCase() throws Exception{
        QADemoPage.emptyCart(driver);
    }

}
