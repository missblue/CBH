package com.priyanka;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public class BasePage {
    WebDriver driver;

    public WebDriver initDriver(){
        System.setProperty("webdriver.chrome.driver","webdrivers/chromedriver");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(6000));
        return driver;
    }
}
