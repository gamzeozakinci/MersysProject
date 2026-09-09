package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.FindBy;

public class FinancePage {
    public FinancePage(WebDriver driver) {
        PageFactory.initElements(driver, this);

    }

    @FindBy(css = "tbody.mdc-data-table__content")
    public WebElement chooseName;

    @FindBy(xpath = "//*[text()=\"Fee/Balance Detail\"]")
    public WebElement feeBalanceDetail;

    @FindBy(xpath = "(//div[contains(@class, 'table-container-wrapper')])[2]")
    public WebElement displayPayments;

    @FindBy(css = "input[type='radio'][value='STRIPE']")
    public WebElement stripe;

    @FindBy(xpath = "(//*[contains(@class, 'mdc-radio__background')])[4]")
    public WebElement amountDue;

    @FindBy(css = "input[name='number']")
    public WebElement cartNo;

    @FindBy(css = "input[name='expiry']")
    public WebElement expireDate;

    @FindBy(css = "input[name='cvc']")
    public WebElement secureNumber;

    @FindBy(css = "button.stripe-pay-button")
    public WebElement pay;




}
