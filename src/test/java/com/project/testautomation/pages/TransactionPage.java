package com.project.testautomation.pages;

import com.project.automation.baseclass.BaseUIPage;
import net.serenitybdd.annotations.DefaultUrl;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

/**
 * Page Object representing the Transaction Page.
 * Contains web elements and interactions related to transactions.
 * In serenity, we keep page objects seperate.
 */
public class TransactionPage extends BaseUIPage {

    @FindBy(id="txnButton") public WebElement startANewTransactionButton;
    @FindBy(id = "userId")      public WebElementFacade userIdInput;
    @FindBy(id = "amount")      public WebElementFacade amountInput;
    @FindBy(id = "type")        public WebElementFacade typeSelect;
    @FindBy(id = "recipientId") public WebElementFacade recipientIdInput;
    @FindBy(id = "createTxn")   public WebElementFacade createTxnBtn;
    @FindBy(id = "txnMsg")      public WebElementFacade txnMsg;

    @FindBy(id = "lookupUserId") public WebElementFacade lookupUserId;
    @FindBy(id = "fetchTxns")    public WebElementFacade fetchTxnsBtn;
    @FindBy(css = "#txnRows tr") public List<WebElementFacade> txnRows;

    public TransactionPage(WebDriver driver) {
        super(driver);
    }
}
