package com.vit.TestingwithNg.tests;

import com.vit.TestingwithNg.base.BaseTest;
import com.vit.TestingwithNg.pages.CartPage;
import com.vit.TestingwithNg.pages.LoginPage;
import com.vit.TestingwithNg.pages.ProductsPage;
import com.vit.TestingwithNg.listeners.ExtentReportListener;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CartTest extends BaseTest {
    
    private ProductsPage productsPage;
    private CartPage cartPage;
    
    @BeforeMethod
    public void setup() {
        LoginPage loginPage = new LoginPage();
        productsPage = loginPage.loginAsStandardUser();
        productsPage.addFirstProductToCart();
        productsPage.addSecondProductToCart();
        cartPage = productsPage.clickCartIcon();
    }
    
    @Test(description = "Verify cart has two items with screenshot")
    public void testCartHasTwoItems() {
        ExtentReportListener.getTest().info("Verifying cart page display");
        Assert.assertTrue(cartPage.isCartPageDisplayed(), "Cart page should be displayed");
        
        int itemCount = cartPage.getCartItemCount();
        ExtentReportListener.getTest().info("Items in cart: " + itemCount);
        
        Assert.assertEquals(itemCount, 2, "Cart should have 2 items");
        ExtentReportListener.getTest().pass("Cart verified with 2 items");
    }
}