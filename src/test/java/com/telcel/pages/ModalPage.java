package com.telcel.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

public class ModalPage extends BasePage {


    //locators
    //By stateLocator = By.cssSelector(".chosen-single span");
    By buttonLocator = By.cssSelector("#entrarPerfilador");
    By dropboxlocator = By.cssSelector(".chosen-single span");
    By liststateLocator= By.cssSelector(".chosen-results li");
    By stateLocator = By.cssSelector(".chosen-search input");


    public ModalPage(WebDriver driver)
    {
        super(driver);
    }

    public void isModalPage() throws Exception {
        assertTrue("The modal window is failing", isElementpresent(stateLocator));
        assertTrue("The modal window is failing", isElementpresent(buttonLocator));
        System.out.println("The modal window is opened");
    }


    public void selectState(String state) throws Exception {
        assertTrue("Fails to select a state",clickonElement(dropboxlocator));
        assertTrue("The state was not enter",enterText(stateLocator,state));
        assertTrue("Fails to select a state",clickonElement(liststateLocator));

        if (driver.findElement(liststateLocator).getText().length() >= 20) {
            assertNotEquals(driver.findElement(liststateLocator).getText(), "No hay coincidencias", driver.findElement(liststateLocator).getText().substring(0, 20));
        }

        assertTrue("Fails to select a state",clickonElement(buttonLocator));
        System.out.println("The state " + state + " was selected");
    }




    ///////////////////////////
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
