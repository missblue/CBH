package com.priyankatest;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Objects;

public class AmazonTest {
    static WebDriver driver;

    @BeforeClass
    public void setUp(){
        System.setProperty("webdriver.chrome.driver", "webdrivers/chromedriver");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized");
        driver = new ChromeDriver(options);
        driver.get("https://www.amazon.in/");
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(5000));
    }

    @Test
    public void checkSamsungTV() throws InterruptedException {
        driver.findElement(By.id("nav-hamburger-menu")).click();
        JavascriptExecutor js = (JavascriptExecutor) driver;
        WebElement foundIt = driver.findElement(By.linkText("#FoundItOnAmazon"));

        WebElement submenuForTV = driver.findElement(By.linkText("TV, Appliances, Electronics"));
        js.executeScript("arguments[0].scrollIntoView(true)",submenuForTV);
        submenuForTV.click();
       //Assert code //
        //Assert.assertEquals();

        driver.findElement(By.linkText("Televisions")).click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(5000));
        wait.until(ExpectedConditions.urlContains("television"));

        WebElement SamsungBrand = driver.findElement(By.linkText("Samsung"));
        js.executeScript("arguments[0].scrollIntoView(true)",SamsungBrand);
        SamsungBrand.click();


//       WebElement sortOption = driver.findElement(By.cssSelector("#s-result-sort-select"));
//       JavascriptExecutor js1 = (JavascriptExecutor) driver;
//       js.executeScript("arguments[0].click();",sortOption);
//        Thread.sleep(2000);
//        Actions action = new Actions(driver);
        WebElement element1 = driver.findElement(By.cssSelector("#s-result-sort-select"));
//        action.moveToElement(element1).click();
//        Thread.sleep(1000);

        Select sortopt = new Select(element1);
        sortopt.selectByVisibleText("Price: High to Low");

        driver.findElement(By.cssSelector("img[data-image-index='2']")).click();
        Assert.assertEquals(driver.getWindowHandles().size(),2);
        String originalWindow = driver.getWindowHandle();
        for(String windowhandle : driver.getWindowHandles()){
            if((!windowhandle.equalsIgnoreCase(originalWindow))){
                driver.switchTo().window(windowhandle);
                break;
            }
        }

        Assert.assertTrue(driver.findElement(By.cssSelector("#feature-bullets")).isDisplayed());
        String text = driver.findElement(By.cssSelector("#feature-bullets")).getText();
        System.out.println(text);


    }

    //@AfterClass
    public void tearDown(){
        driver.quit();
    }
}
