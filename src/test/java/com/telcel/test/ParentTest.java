package com.telcel.test;

import com.telcel.pages.PhoneInfoPage;
import com.telcel.pages.PhonesPage;
import com.telcel.pages.HomePage;
import com.telcel.pages.ModalPage;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.FileSystemNotFoundException;
import java.util.Collections;
import java.util.Properties;
import java.util.concurrent.TimeUnit;


public class ParentTest {

    public WebDriver driver;
    public HomePage home;
    public ModalPage modal;
    public PhonesPage phones;
    public PhoneInfoPage phoneinfo;

    protected WebDriverWait wait;
    protected String url;
    protected String state;
    protected String phone;

    public void loadBrowser() {
        String path;
        path = ".\\src\\test\\java\\resources\\drivers\\chromedriver.exe";

        System.setProperty("webdriver.chrome.driver", path);
        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("useAutomationExtension", false);
        options.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));
        options.addArguments("start-maximized");
        options.addArguments("incognito");

        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().deleteAllCookies();
        driver.manage().window().maximize();


    }


    private void getPropValues() throws IOException {

        Properties prop = new Properties();

        //System.getProperty("user.dir") + ".\\src\\test\\java\\resources\\configuration\\data.properties";

        String propFilename = ".\\src\\test\\java\\resources\\configuration\\data.properties";
        InputStream inputStream = new FileInputStream(propFilename);
        if (inputStream != null) {
            prop.load(inputStream);
        } else {
            FileSystemNotFoundException ex = new FileSystemNotFoundException();
            ex.printStackTrace();
         }
        url = prop.getProperty("url");
        state = prop.getProperty("state");
        phone = prop.getProperty("phone");
    }


    @Before
    public void before() throws IOException {
        getPropValues();
        loadBrowser();

        home = new HomePage(driver);
        modal = new ModalPage(driver);
        phones = new PhonesPage(driver);
        phoneinfo = new PhoneInfoPage(driver);

    }


    @After
    public void quit() {
        home = null;
        modal = null;
        phones = null;
        phoneinfo = null;
        driver.quit();
    }


}
