package com.telcel.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

public class ModalPage extends BasePage {


    //locators
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


}
