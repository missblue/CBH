package com.priyankatest;

import com.priyanka.BasePage;
import com.priyanka.HomePage;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class AmazonTest1 extends BasePage {

    static WebDriver driver;
    static HomePage fields;

    @BeforeClass
    public void setUp(){
        driver = initDriver();
        fields = new HomePage(driver);
    }
    @AfterClass
    public void tearDown(){
        driver.quit();
    }
    @Test
    public void checkSamsungTV(){
        driver.get(fields.HomePageUrl);
        fields.clickHamburgerMenu();
        fields.clickOnDeptByLinkText("TV, Appliances, Electronics");
        fields.clickOnDeptByLinkText("Televisions");
        fields.waitForUrlChange("television");
        fields.clickOnDeptByLinkText("Samsung");
        fields.selectSortOption("Price: High to Low");
        //click on 2nd item in the list
        fields.clickOnGivenTVItem(1);
        //assert that item opened in new tab
        Assert.assertEquals(driver.getWindowHandles().size(),2);

        //switch to new window//
        String originalWindow = driver.getWindowHandle();
        for(String windowhandle : driver.getWindowHandles()){
            if((!windowhandle.equalsIgnoreCase(originalWindow))){
                driver.switchTo().window(windowhandle);
                break;
            }
        }
        Assert.assertTrue(driver.findElement(By.cssSelector("#feature-bullets")).isDisplayed());
        System.out.println(fields.getItemDesc());



    }
}
