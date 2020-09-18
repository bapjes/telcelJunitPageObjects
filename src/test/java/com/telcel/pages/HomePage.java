package com.telcel.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import static org.junit.Assert.assertTrue;

public class HomePage extends BasePage {

    //Locators
    By logo = By.cssSelector("[title=\"Telcel\"]");
    By tiendaenlineaLocator = By.cssSelector("[data-nombreboton='Tienda en linea superior']");
    By smartphonelocator = By.cssSelector("[data-nombreboton=\"Tienda en linea superior\"]+ul a[data-submenu=\"Telefonos y smartphones\"]");

    public HomePage(WebDriver driver)
    {
        super(driver);
    }

    public void goToPage(String url) {
        driver.get(url);
        System.out.println("Telcel page was loaded");
    }


    public void selectSmartPhone() throws Exception {
        clickonElement(tiendaenlineaLocator);
        clickonElement(smartphonelocator);
        System.out.println("'Telefonos y Smart Phones' option was selected ");
    }


    public void isTelcelHomePage() throws Exception {
        assertTrue("You are not on telcel page",isElementpresent(logo));
        System.out.println("You are on telcel page");
    }



}
