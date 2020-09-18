package com.telcel.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {
    protected WebDriver driver;
    protected WebDriverWait wait;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, 15);

    }

    public boolean isElementpresent (By locator) throws Exception
    {
        try {
            //wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            wait.until(ExpectedConditions.presenceOfElementLocated(locator));
            return true;
        }
        catch (Exception e)
        {
            return false;
        }
    }

    public void scrollToWebElement(WebElement webElement) throws Exception {
        try {
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", webElement);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean clickonElement(By locator) throws Exception
    {
        try {
            wait.until(ExpectedConditions.presenceOfElementLocated(locator));
            wait.until(ExpectedConditions.elementToBeClickable(locator));
            scrollToWebElement(driver.findElement(locator));
            driver.findElement(locator).click();
            return true;
        }
        catch(Exception e)
        {
            return false;
        }
    }

    public boolean enterText(By locator,String state) throws Exception {
        try {
            wait.until(ExpectedConditions.presenceOfElementLocated(locator));
            scrollToWebElement(driver.findElement(locator));
            driver.findElement(locator).clear();
            driver.findElement(locator).sendKeys(state);
            return true;
        }
        catch(Exception e)
        {
            return false;
        }
    }

}
