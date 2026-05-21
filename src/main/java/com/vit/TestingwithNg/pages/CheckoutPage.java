package com.vit.TestingwithNg.pages;

import com.vit.TestingwithNg.base.BaseTest;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckoutPage extends BaseTest {
    
    @FindBy(id = "first-name")
    private WebElement firstNameInput;
    
    @FindBy(id = "last-name")
    private WebElement lastNameInput;
    
    @FindBy(id = "postal-code")
    private WebElement postalCodeInput;
    
    @FindBy(id = "continue")
    private WebElement continueButton;
    
    @FindBy(id = "finish")
    private WebElement finishButton;
    
    @FindBy(className = "complete-header")
    private WebElement successMessage;
    
    public CheckoutPage() {
        PageFactory.initElements(getDriver(), this);
    }
    
    public void enterFirstName(String firstName) {
        firstNameInput.sendKeys(firstName);
    }
    
    public void enterLastName(String lastName) {
        lastNameInput.sendKeys(lastName);
    }
    
    public void enterPostalCode(String postalCode) {
        postalCodeInput.sendKeys(postalCode);
    }
    
    public void clickContinue() {
        continueButton.click();
    }
    
    public void clickFinish() {
        finishButton.click();
    }
    
    public boolean isCheckoutComplete() {
        return successMessage.isDisplayed() && successMessage.getText().contains("Thank you");
    }
    
    public String getSuccessMessage() {
        return successMessage.getText();
    }
}