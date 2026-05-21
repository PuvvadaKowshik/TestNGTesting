package com.vit.TestingwithNg.pages;

import com.vit.TestingwithNg.base.BaseTest;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import java.util.List;

public class ProductsPage extends BaseTest {
    
    @FindBy(className = "inventory_list")
    private WebElement inventoryList;
    
    @FindBy(className = "shopping_cart_badge")
    private WebElement cartBadge;
    
    @FindBy(className = "shopping_cart_link")
    private WebElement cartIcon;
    
    @FindBy(css = "button.btn_inventory")
    private List<WebElement> addToCartButtons;
    
    public ProductsPage() {
        PageFactory.initElements(getDriver(), this);
    }
    
    public boolean isProductsPageDisplayed() {
        return inventoryList.isDisplayed();
    }
    
    public void addFirstProductToCart() {
        addToCartButtons.get(0).click();
    }
    
    public void addSecondProductToCart() {
        addToCartButtons.get(1).click();
    }
    
    public int getCartItemCount() {
        return Integer.parseInt(cartBadge.getText());
    }
    
    public CartPage clickCartIcon() {
        cartIcon.click();
        return new CartPage();
    }
}