package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.FindBy;

public class FinancePage {

    // Also used by By-based waits in US009_US010, so each selector lives in one place.
    public static final String STRIPE_RADIO_CSS = "input[type='radio'][value='STRIPE']";
    public static final String AMOUNT_DUE_RADIO_XPATH = "(//*[contains(@class, 'mdc-radio__background')])[4]";

    public FinancePage(WebDriver driver) {
        PageFactory.initElements(driver, this);

    }

    @FindBy(css = "tbody.mdc-data-table__content")
    public WebElement chooseName;

    @FindBy(xpath = "//*[text()=\"Fee/Balance Detail\"]")
    public WebElement feeBalanceDetail;

    @FindBy(xpath = "(//div[contains(@class, 'table-container-wrapper')])[2]")
    public WebElement displayPayments;

    @FindBy(css = STRIPE_RADIO_CSS)
    public WebElement stripe;

    @FindBy(xpath = AMOUNT_DUE_RADIO_XPATH)
    public WebElement amountDue;

    @FindBy(css = "input[name='number']")
    public WebElement cardNumber;

    @FindBy(css = "input[name='expiry']")
    public WebElement expireDate;

    @FindBy(css = "input[name='cvc']")
    public WebElement secureNumber;

    @FindBy(css = "button.stripe-pay-button")
    public WebElement pay;

    @FindBy(css = "svg[class='svg-inline--fa fa-xmark fa-fw']")
    public WebElement errorMessage;

    @FindBy(id = "ms-currency-field-0")
    public WebElement customPayButton;

    @FindBy(css = "svg[data-icon='wallet']")
    public WebElement walletIcon;
}
