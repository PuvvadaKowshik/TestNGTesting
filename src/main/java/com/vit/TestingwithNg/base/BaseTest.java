package com.vit.TestingwithNg.base;

import com.vit.TestingwithNg.listeners.ExtentReportListener;
import com.vit.TestingwithNg.utils.ConfigReader;
import com.vit.TestingwithNg.utils.waitutil;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;

@Listeners({ExtentReportListener.class})
public class BaseTest {
    
    protected static ThreadLocal<WebDriver> driverThread = new ThreadLocal<>();
    protected static ThreadLocal<waitutil> waitUtilThread = new ThreadLocal<>();
    
    public WebDriver getDriver() {
        return driverThread.get();
    }
    
    public waitutil getWaitUtil() {
        return waitUtilThread.get();
    }
    
    @BeforeMethod
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        options.addArguments("--disable-notifications");
        WebDriver driver = new ChromeDriver(options);
        driver.manage().deleteAllCookies();
        driverThread.set(driver);
        
        waitutil waitUtil = new waitutil(driver);
        waitUtilThread.set(waitUtil);
        
        ExtentReportListener.setDriver(driver);
        driver.get(ConfigReader.getProperty("base.url"));
    }
    
    @AfterMethod
    public void tearDown() {
        WebDriver driver = driverThread.get();
        if (driver != null) {
            driver.quit();
            driverThread.remove();
            waitUtilThread.remove();
        }
    }
}