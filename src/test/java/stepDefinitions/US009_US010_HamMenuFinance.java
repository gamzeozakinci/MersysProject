package stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import pages.FinancePage;
import pages.HeaderMenu;

import java.time.Duration;

import static pages.ParentPage.click;
import static utilities.GWD.getDriver;

public class US009_US010_HamMenuFinance {

    WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
    HeaderMenu header = new HeaderMenu(getDriver());
    FinancePage finance = new FinancePage(getDriver());

    @When("User clicks hamburger menu")
    public void ClicksHamburgerMenu() {
        wait.until(ExpectedConditions.elementToBeClickable(header.hamburgerButton));
        ((JavascriptExecutor) getDriver()).executeScript("arguments[0].closest('button').click();", header.hamburgerButton);

    }

    @And("User clicks \"My finance\" from \"Finance\" option")
    public void ClicksFinance() {
        click(header.hamburgerButtonFinance, 3);
        click(header.hamburgerButtonMyFinance, 3);

    }

    @Then("User finds his\\/her name and clicks")
    public void nameClicks() {
        wait.until(ExpectedConditions.elementToBeClickable(finance.chooseName));
        click(finance.chooseName, 5);

    }

    @And("User clicks \"Fee\\/Balance Detail\"")
    public void ClicksFeeDetail() {
        click(finance.feeBalanceDetail, 3);

    }

    @And("User should be able to see the details of payments")
    public void checkPayments() {
        wait.until(ExpectedConditions.visibilityOf(finance.displayPayments));
        Assert.assertTrue(finance.displayPayments.isDisplayed());

    }

    @And("User clicks \"Stripe\" to make a payment")
    public void Stripe() {
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(FinancePage.STRIPE_RADIO_CSS)));
        ((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();", finance.stripe);

    }

    @And("User chooses \"Pay Amount Due 100.00$\" to pay minimum amount")
    public void payAmountDue() {
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(FinancePage.AMOUNT_DUE_RADIO_XPATH)));
        ((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();", finance.amountDue);

    }

    @And("User enters card info")
    public void enterCardInfo() {
        WebElement stripeFrame = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.cssSelector("iframe[title='Güvenli ödeme giriş çerçevesi']")));
        getDriver().switchTo().frame(stripeFrame);

        wait.until(ExpectedConditions.elementToBeClickable(finance.cardNumber));
        finance.cardNumber.sendKeys("4242 4242 4242 4242");
        finance.expireDate.sendKeys("1230");
        finance.secureNumber.sendKeys("111");

        getDriver().switchTo().defaultContent();

    }

    @And("User clicks \"Stripe\" to pay")
    public void StripePay() {
        click(finance.pay, 3);

    }

    @Then("User should be able to access Finance page")
    public void verifyFinancePage() {
        wait.until(ExpectedConditions.visibilityOf(finance.chooseName));
        Assert.assertTrue(finance.chooseName.isDisplayed());
    }

}
