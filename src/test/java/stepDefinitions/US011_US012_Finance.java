package stepDefinitions;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.FinancePage;
import pages.HeaderMenu;
import pages.ParentPage;

import java.time.Duration;

import static utilities.GWD.getDriver;

public class US011_US012_Finance {

    FinancePage fp = new FinancePage(getDriver());
    HeaderMenu hp = new HeaderMenu(getDriver());
    WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));


    @Given("User goes to finance page through hamburger menu")
    public void usersGoesToFinancePageThroughHamburgerMenu() {
        ParentPage.click(hp.hamburgerButton,10);
        ParentPage.click(hp.hamburgerButtonFinance,10);
        ParentPage.click(hp.hamburgerButtonMyFinance,10);
    }

    @Then("User clicks on student name")
    public void userClicksOnStudentName() {
        ParentPage.click(fp.chooseName,10);
        ParentPage.click(fp.errorMessage, 10);
    }

    @When("User clicks on Stripe payment button")
    public void userClicksOnStripePaymentButton() {
        ((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();", fp.stripe);
    }

    @And("User clicks on payment fee")
    public void userClicksOnPaymentFee() {
        ParentPage.click(fp.customPayButton,10);
        ParentPage.mySendKeys(fp.customPayButton,"235");
        ParentPage.click(fp.walletIcon,10);
    }

    @And("User fills the card details")
    public void userFillsTheCardDetails() {
        wait.until(ExpectedConditions.elementToBeClickable(fp.cardNumber));
        ParentPage.mySendKeys(fp.cardNumber,"4242 4242 4242 4242");
        ParentPage.mySendKeys(fp.expireDate,"1229");
        ParentPage.mySendKeys(fp.secureNumber,"123");
        ParentPage.click(fp.pay,10);
    }
}
