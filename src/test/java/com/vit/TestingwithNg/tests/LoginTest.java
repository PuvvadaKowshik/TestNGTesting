package com.vit.TestingwithNg.tests;

import com.vit.TestingwithNg.base.BaseTest;
import com.vit.TestingwithNg.pages.LoginPage;
import com.vit.TestingwithNg.pages.ProductsPage;
import com.vit.TestingwithNg.listeners.ExtentReportListener;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {
    
    @Test(priority = 1, description = "Valid login test with screenshot")
    public void testValidLogin() {
        ExtentReportListener.getTest().info("Starting valid login test");
        LoginPage loginPage = new LoginPage();
        ProductsPage productsPage = loginPage.loginAsStandardUser();
        Assert.assertTrue(productsPage.isProductsPageDisplayed(), "Products page should display after login");
        ExtentReportListener.getTest().pass("Valid login successful");
    }
    
    @Test(priority = 2, description = "Invalid login test with screenshot")
    public void testInvalidLogin() {
        ExtentReportListener.getTest().info("Starting invalid login test");
        LoginPage loginPage = new LoginPage();
        loginPage.loginWithInvalidCredentials();
        Assert.assertTrue(loginPage.isErrorMessageDisplayed(), "Error message should display");
        ExtentReportListener.getTest().pass("Invalid login shows error message");
    }
}

