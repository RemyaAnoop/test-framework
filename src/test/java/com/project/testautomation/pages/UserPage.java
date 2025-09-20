package com.project.testautomation.pages;

import com.project.automation.baseclass.BaseUIPage;
import net.serenitybdd.annotations.DefaultUrl;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;
import org.openqa.selenium.WebDriver;

/**
 * Page Object representing the User Page.
 * Contains web elements and interactions related to user management.
 * In serenity, we keep page objects separate.
 */
@DefaultUrl("page.account.url")
public class UserPage extends BaseUIPage {

    @FindBy(id = "name")
    public WebElementFacade nameInput;
    @FindBy(id = "email")
    public WebElementFacade emailInput;
    @FindBy(id = "accountType")
    public WebElementFacade accountTypeSelect;
    @FindBy(id = "createUser")
    public WebElementFacade createUserBtn;
    @FindBy(id = "userMsg")
    public WebElementFacade userMsg;
    @FindBy(id = "nameError")
    public WebElementFacade nameError;
    @FindBy(id = "emailError")
    public WebElementFacade emailError;

    public UserPage(WebDriver driver) {
        super(driver);
    }

    /**
     * Navigate to the user page
     */
    public void navigateToUserPage() {
        // Since this page has @DefaultUrl annotation, we can use open() to navigate to it
        open();
    }
}
