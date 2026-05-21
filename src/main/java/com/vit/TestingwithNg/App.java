//package com.vit.TestingwithNg;
//
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.OutputType;
//import org.openqa.selenium.TakesScreenshot;
//import org.apache.commons.io.FileUtils;
//import com.vit.TestingwithNg.pages.LoginPage;
//import com.vit.TestingwithNg.pages.ProductsPage;
//import com.vit.TestingwithNg.pages.CartPage;
//import com.vit.TestingwithNg.pages.CheckoutPage;
//import io.github.bonigarcia.wdm.WebDriverManager;
//import java.io.File;
//import java.text.SimpleDateFormat;
//import java.util.Date;
//
//public class App 
//{
//    public static void main( String[] args )
//    {
//        System.out.println("=========================================");
//        System.out.println("STARTING TEST EXECUTION");
//        System.out.println("=========================================\n");
//        
//        WebDriver driver = null;
//        
//        try {
//            WebDriverManager.chromedriver().setup();
//            driver = new ChromeDriver();
//            driver.manage().window().maximize();
//            
//            driver.get("https://www.saucedemo.com/");
//            Thread.sleep(2000);
//            System.out.println("Step 1: Website opened");
//            
//            LoginPage loginPage = new LoginPage(driver);
//            ProductsPage productsPage = loginPage.login("standard_user", "secret_sauce");
//            Thread.sleep(2000);
//            System.out.println("Step 2: Login successful");
//            
//            productsPage.addTwoProducts();
//            Thread.sleep(2000);
//            System.out.println("Step 3: Added 2 products to cart");
//            
//            CartPage cartPage = productsPage.goToCart();
//            Thread.sleep(2000);
//            System.out.println("Step 4: Navigated to cart page");
//            
//            CheckoutPage checkoutPage = cartPage.clickCheckout();
//            Thread.sleep(2000);
//            System.out.println("Step 5: Clicked checkout button");
//            
//            checkoutPage.fillCheckoutInfo("John", "Doe", "12345");
//            Thread.sleep(2000);
//            System.out.println("Step 6: Filled checkout information");
//            
//            checkoutPage.clickFinish();
//            Thread.sleep(3000);
//            System.out.println("Step 7: Order placed successfully");
//            
//            String successMessage = checkoutPage.getSuccessMessage();
//            System.out.println("Success Message: " + successMessage);
//            
//            File screenshotDir = new File("screenshots");
//            if (!screenshotDir.exists()) {
//                screenshotDir.mkdir();
//            }
//            
//            TakesScreenshot ts = (TakesScreenshot) driver;
//            File source = ts.getScreenshotAs(OutputType.FILE);
//            
//            String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
//            String fileName = "Order_Successful_" + timestamp + ".png";
//            File destination = new File("screenshots/" + fileName);
//            
//            FileUtils.copyFile(source, destination);
//            
//            System.out.println("\n=========================================");
//            System.out.println("✓ TEST EXECUTION COMPLETED SUCCESSFULLY!");
//            System.out.println("✓ Order Message: " + successMessage);
//            System.out.println("✓ Screenshot saved: " + destination.getAbsolutePath());
//            System.out.println("=========================================");
//            
//        } catch (Exception e) {
//            System.out.println("Error occurred: " + e.getMessage());
//            e.printStackTrace();
//            
//            if (driver != null) {
//                try {
//                    TakesScreenshot ts = (TakesScreenshot) driver;
//                    File source = ts.getScreenshotAs(OutputType.FILE);
//                    String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
//                    String fileName = "Test_Failed_" + timestamp + ".png";
//                    File destination = new File("screenshots/" + fileName);
//                    FileUtils.copyFile(source, destination);
//                    System.out.println("Error screenshot saved: " + destination.getAbsolutePath());
//                } catch (Exception ex) {
//                    System.out.println("Failed to capture error screenshot: " + ex.getMessage());
//                }
//            }
//            
//        } finally {
//            try {
//                Thread.sleep(2000);
//                if (driver != null) {
//                    driver.quit();
//                    System.out.println("Browser closed");
//                }
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//    }
//}