package com.vit.TestingwithNg.tests;

import com.vit.TestingwithNg.base.BaseTest;
import com.vit.TestingwithNg.pages.CartPage;
import com.vit.TestingwithNg.pages.CheckoutPage;
import com.vit.TestingwithNg.pages.LoginPage;
import com.vit.TestingwithNg.pages.ProductsPage;
import com.vit.TestingwithNg.listeners.ExtentReportListener;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CheckoutTest extends BaseTest {
    
    @Test
    public void testCompleteCheckout() throws Exception {
        ExtentReportListener.getTest().info("Step 1: Login");
        LoginPage loginPage = new LoginPage();
        ProductsPage productsPage = loginPage.loginAsStandardUser();
        Thread.sleep(2000);
        
        ExtentReportListener.getTest().info("Step 2: Add two products");
        productsPage.addFirstProductToCart();
        productsPage.addSecondProductToCart();
        Thread.sleep(1000);
        
        ExtentReportListener.getTest().info("Step 3: Go to cart");
        CartPage cartPage = productsPage.clickCartIcon();
        Thread.sleep(2000);
        
        ExtentReportListener.getTest().info("Step 4: Click checkout");
        CheckoutPage checkoutPage = cartPage.clickCheckout();
        Thread.sleep(2000);
        
        ExtentReportListener.getTest().info("Step 5: Fill details");
        checkoutPage.enterFirstName("kowshik");
        checkoutPage.enterLastName("puvvada");
        checkoutPage.enterPostalCode("534202");
        Thread.sleep(500);
        
        ExtentReportListener.getTest().info("Step 6: Click continue");
        checkoutPage.clickContinue();
        Thread.sleep(3000);
        
        ExtentReportListener.getTest().info("Step 7: Click finish");
        checkoutPage.clickFinish();
        Thread.sleep(3000);
        
        ExtentReportListener.getTest().info("Step 8: Verify");
        Assert.assertTrue(checkoutPage.isCheckoutComplete(), "Checkout failed");
        ExtentReportListener.getTest().pass("Checkout successful");
    }
}