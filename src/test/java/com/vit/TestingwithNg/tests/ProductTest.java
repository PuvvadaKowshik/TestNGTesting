package com.vit.TestingwithNg.tests;

import com.vit.TestingwithNg.base.BaseTest;
import com.vit.TestingwithNg.pages.LoginPage;
import com.vit.TestingwithNg.pages.ProductsPage;
import com.vit.TestingwithNg.listeners.ExtentReportListener;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ProductTest extends BaseTest {
    
    private ProductsPage productsPage;
    
    @BeforeMethod
    public void login() {
        LoginPage loginPage = new LoginPage();
        productsPage = loginPage.loginAsStandardUser();
    }
    
    @Test(description = "Add two products to cart with screenshot")
    public void testAddTwoProductsToCart() {
        ExtentReportListener.getTest().info("Adding first product to cart");
        productsPage.addFirstProductToCart();
        
        ExtentReportListener.getTest().info("Adding second product to cart");
        productsPage.addSecondProductToCart();
        
        int cartCount = productsPage.getCartItemCount();
        ExtentReportListener.getTest().info("Cart count: " + cartCount);
        
        Assert.assertEquals(cartCount, 2, "Cart should have 2 items");
        ExtentReportListener.getTest().pass("Two products added successfully");
    }
}