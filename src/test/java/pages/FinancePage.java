package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class FinancePage extends ParentPage {

    public FinancePage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = "tbody.mdc-data-table__content")
    public WebElement chooseName;

    @FindBy(css = "tbody.mdc-data-table__content td.cdk-column-totalRevenue")
    public WebElement totalRevenue;

    @FindBy(xpath = "//*[text()=\"Fee/Balance Detail\"]")
    public WebElement feeBalanceDetail;

    @FindBy(xpath = "(//div[contains(@class, 'table-container-wrapper')])[2]")
    public WebElement displayPayments;

    @FindBy(css = "input[type='radio'][value='STRIPE']")
    public WebElement stripe;

    @FindBy(xpath = "(//*[contains(@class, 'mdc-radio__background')])[4]")
    public WebElement amountDue;

    @FindBy(css = "iframe[name^='__privateStripeFrame']")
    public List<WebElement> stripeFrames;

    @FindBy(css = "input[name='number']")
    public WebElement cardNumber;

    @FindBy(css = "input[name='expiry']")
    public WebElement expireDate;

    @FindBy(css = "input[name='cvc']")
    public WebElement secureNumber;

    @FindBy(css = "button.stripe-pay-button")
    public WebElement pay;

    @FindBy(css = "svg[class='svg-inline--fa fa-xmark fa-fw']")
    public WebElement closeErrorButton;

    @FindBy(id = "ms-currency-field-0")
    public WebElement customPayButton;

    @FindBy(css = "svg[data-icon='wallet']")
    public WebElement walletIcon;

    @FindBy(xpath = "//button[contains(., 'Excel') or contains(., 'PDF')]")
    public WebElement downloadButton;
}
