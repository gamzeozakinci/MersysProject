package stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import pages.FinancePage;
import pages.HeaderMenu;

import java.time.Duration;

import static pages.ParentPage.click;
import static pages.ParentPage.isPresent;
import static utilities.GWD.getDriver;

public class US009_US010_HamMenuFinance {

    WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
    HeaderMenu hm = new HeaderMenu(getDriver());
    FinancePage fp = new FinancePage(getDriver());
    JavascriptExecutor js = (JavascriptExecutor) getDriver();

    @When("User clicks hamburger menu")
    public void clicksHamburgerMenu() {
        wait.until(ExpectedConditions.elementToBeClickable(hm.hamburgerButton));
        js.executeScript("arguments[0].closest('button').click();", hm.hamburgerButton);
    }

    @And("User clicks \"My finance\" from \"Finance\" option")
    public void clicksFinance() {
        click(hm.hamburgerButtonFinance, 3);
        click(hm.hamburgerButtonMyFinance, 3);
    }

    @Then("User finds his\\/her name and clicks")
    public void nameClicks() {
        click(fp.chooseName, 10);
    }

    @And("User clicks \"Fee\\/Balance Detail\"")
    public void clicksFeeDetail() {
        click(fp.feeBalanceDetail, 10);
    }

    @And("User should be able to see the details of payments")
    public void checkPayments() {
        wait.until(ExpectedConditions.visibilityOf(fp.displayPayments));
        Assert.assertTrue(fp.displayPayments.isDisplayed());
    }

    @And("User clicks \"Stripe\" to make a payment")
    public void clicksStripe() {
        wait.until(driver -> isPresent(fp.stripe));
        js.executeScript("arguments[0].click();", fp.stripe);
    }

    @And("User chooses \"Pay Amount Due 100.00$\" to pay minimum amount")
    public void payAmountDue() {
        wait.until(driver -> isPresent(fp.amountDue));
        js.executeScript("arguments[0].click();", fp.amountDue);
    }

    @And("User enters card info")
    public void enterCardInfo() {
        new WebDriverWait(getDriver(), Duration.ofSeconds(20))
                .withMessage("The Stripe card form never appeared")
                .until(driver -> switchToCardFrame());

        wait.until(ExpectedConditions.elementToBeClickable(fp.cardNumber));
        fp.cardNumber.sendKeys("4242 4242 4242 4242");
        fp.expireDate.sendKeys("1230");
        fp.secureNumber.sendKeys("111");

        getDriver().switchTo().defaultContent();
    }

    private boolean switchToCardFrame() {
        for (WebElement frame : fp.stripeFrames) {
            getDriver().switchTo().defaultContent();
            getDriver().switchTo().frame(frame);

            if (isPresent(fp.cardNumber)) {
                return true;
            }
        }

        getDriver().switchTo().defaultContent();
        return false;
    }

    @And("User clicks \"Stripe\" to pay")
    public void clicksStripePay() {
        click(fp.pay, 3);
    }

    @Then("User should be able to access Finance page")
    public void verifyFinancePage() {
        wait.until(ExpectedConditions.visibilityOf(fp.chooseName));
        Assert.assertTrue(fp.chooseName.isDisplayed());
    }
}
