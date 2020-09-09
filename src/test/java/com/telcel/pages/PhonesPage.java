package com.telcel.pages;

import com.telcel.utils.Celular;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

import static org.junit.Assert.assertTrue;


public class PhonesPage extends BasePage {


    //Locators
    By listlocator = By.cssSelector("[ng-repeat*='devices']");
    By namelocator = By.cssSelector(".telcel-mosaico-equipos-nombre-equipo");
    By capacitylocator = By.cssSelector(".telcel-mosaico-equipos-capacidad-numero");
    By pricelocator = By.cssSelector(".telcel-mosaico-equipos-precio");

    Celular phoneinfo;

    public PhonesPage(WebDriver driver)
    {
       super(driver);

    }

    public void listofPhones() throws Exception {

        assertTrue("The list of phones is not displayed",isElementpresent(listlocator));
        System.out.println("There are " +  driver.findElements(listlocator).size() + " cellpones displayed");
    }

    public Celular selectPhone(String phone) throws Exception {
        assertTrue("x", Integer.parseInt(phone) >= 0);
        assertTrue("The list of phones is not displayed",isElementpresent(listlocator));
        List<WebElement> list_telephones = driver.findElements(listlocator);
        WebElement cellphone = list_telephones.get(Integer.parseInt(phone));
        System.out.println("The telephone selected was : " + phone);
        phoneinfo = saveInfo(cellphone);
        cellphone.click();
        return phoneinfo;
     }

   public Celular saveInfo(WebElement element)
   {
       try {
           isElementpresent(namelocator);
           isElementpresent(capacitylocator);
           isElementpresent(pricelocator);

           String namephone = element.findElement(namelocator).getText().trim();
           String capacityphone = element.findElement(capacitylocator).getText().trim();
           String pricephone = element.findElement(pricelocator).getText().trim();

           int capac = Integer.parseInt(capacityphone.split(" ")[0]);
           double price = Double.parseDouble((pricephone.replace("$", "").replace(",", "")));

           Celular cellphone = new Celular(namephone, capac, price);
           System.out.println("The information of the cell phone was saved");
           return cellphone;
       } catch(Exception e)
       {
           System.out.println(e.getMessage());
           System.out.println("Information of the cellphone was not saved");
           return null;
       }
   }

    //////////////////////////////////////////
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
