package com.telcel.pages;

import com.telcel.utils.Celular;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PhoneInfoPage extends BasePage {

    public PhoneInfoPage(WebDriver driver)
    {
        super(driver);

    }


    public void compareCellphoneselected(Celular phoneinfo) throws Exception {
        By namelocator = By.cssSelector("header>#ecommerce-ficha-tecnica-nombre");
        isElementpresent(namelocator);

        By capacitylocator = By.cssSelector(".ecommerce-ficha-tecnica-opciones-compra-caracteristicas-etiqueta");
        isElementpresent(capacitylocator);

        By pricelocator = By.cssSelector(".ecommerce-ficha-tecnica-precio-pagos>#ecommerce-ficha-tecnica-precio-obj");
        isElementpresent(pricelocator);

        String namephone = driver.findElement(namelocator).getText().trim();
        String capacityphone = driver.findElement(capacitylocator).getText().trim();
        String pricephone = driver.findElement(pricelocator).getText().trim();

        int capac = Integer.parseInt(capacityphone.split(" ")[0]);
        double price = Double.parseDouble((pricephone.replace("$", "").replace(",", "")));

        if(phoneinfo.getNombre().equals(namephone)
                &&  phoneinfo.getPrecio() == price
                &&  phoneinfo.getCapacidad() == capac) {
            System.out.println("The information of the telephone selected is correct : ");
            System.out.println("Name : " + phoneinfo.getNombre());
            System.out.println("Price : " + phoneinfo.getPrecio());
            System.out.println("Capacity: " + phoneinfo.getCapacidad());
        } else {
            System.out.println("Information of the telephone is incorrect");
            throw new Exception("Information of the telephone is incorrect");
        }
    }

    //////
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

}
