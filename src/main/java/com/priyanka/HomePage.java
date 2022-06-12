package com.priyanka;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class HomePage {

    WebDriver driver;
    public final String HomePageUrl = "https://www.amazon.in/";
    public HomePage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);


    }
    @FindBy(css = "#nav-hamburger-menu") WebElement field_hamburgermenu;
    @FindBy(linkText = "TV, Appliances, Electronics") WebElement field_subMenuforTV;
    @FindBy(linkText = "Televisions") WebElement field_Television;
    @FindBy(linkText = "Samsung") WebElement field_SamsungBrand;
    @FindBy(css = "#s-result-sort-select") WebElement field_sortButton;
    //@FindBy()
    @FindBy(css = "div[data-component-type='s-search-result']") List<WebElement> field_TVList;
    @FindBy(css = "#feature-bullets") WebElement field_itemDescription;

    //METHODS//
    public void clickHamburgerMenu(){
        field_hamburgermenu.click();
    }
    public void clickSubMenuforTV(){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click()",field_subMenuforTV);
    }
    public void clickTelevisions(){
        field_Television.click();
    }
    public void waitForUrlChange(String text){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(5000));
        wait.until(ExpectedConditions.urlContains(text));
    }
    public void checkSamsungBrand(){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true)",field_SamsungBrand);
        field_SamsungBrand.click();
    }
    public void selectSortOption(String text){
        Select item = new Select(field_sortButton);
        item.selectByVisibleText(text);
    }
    public void clickOnGivenTVItem(int idx){
        field_TVList.get(idx).click();
    }

    public String getItemDesc(){
        return field_itemDescription.getText();
    }

}
