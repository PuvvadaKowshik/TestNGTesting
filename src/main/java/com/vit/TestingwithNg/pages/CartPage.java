package com.vit.TestingwithNg.pages;

import com.vit.TestingwithNg.base.BaseTest;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import java.util.List;

public class CartPage extends BaseTest {
    
    @FindBy(className = "cart_list")
    private WebElement cartList;
    
    @FindBy(className = "cart_item")
    private List<WebElement> cartItems;
    
    @FindBy(id = "checkout")
    private WebElement checkoutButton;
    
    public CartPage() {
        PageFactory.initElements(getDriver(), this);
    }
    
    public boolean isCartPageDisplayed() {
        return cartList.isDisplayed();
    }
    
    public int getCartItemCount() {
        return cartItems.size();
    }
    
    public CheckoutPage clickCheckout() {
        checkoutButton.click();
        return new CheckoutPage();
    }
}