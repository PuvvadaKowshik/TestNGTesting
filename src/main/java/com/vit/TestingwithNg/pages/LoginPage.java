package com.vit.TestingwithNg.pages;

import com.vit.TestingwithNg.base.BaseTest;
import com.vit.TestingwithNg.utils.ConfigReader;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends BaseTest {
    
    @FindBy(id = "user-name")
    private WebElement usernameInput;
    
    @FindBy(id = "password")
    private WebElement passwordInput;
    
    @FindBy(id = "login-button")
    private WebElement loginButton;
    
    @FindBy(css = "[data-test='error']")
    private WebElement errorMessage;
    
    public LoginPage() {
        PageFactory.initElements(getDriver(), this);
    }
    
    public ProductsPage loginAsStandardUser() {
        getWaitUtil().waitForVisibility(usernameInput);
        usernameInput.clear();
        usernameInput.sendKeys(ConfigReader.getProperty("username.standard"));
        passwordInput.clear();
        passwordInput.sendKeys(ConfigReader.getProperty("password"));
        getWaitUtil().waitForClickable(loginButton);
        loginButton.click();
        return new ProductsPage();
    }
    
    public void loginWithInvalidCredentials() {
        getWaitUtil().waitForVisibility(usernameInput);
        usernameInput.clear();
        usernameInput.sendKeys("invalid_user");
        passwordInput.clear();
        passwordInput.sendKeys("wrong_password");
        getWaitUtil().waitForClickable(loginButton);
        loginButton.click();
    }
    
    public boolean isErrorMessageDisplayed() {
        return getWaitUtil().isElementVisible(errorMessage);
    }
    
    public String getErrorMessageText() {
        return errorMessage.getText();
    }
}