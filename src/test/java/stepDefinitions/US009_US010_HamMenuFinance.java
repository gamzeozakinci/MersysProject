package stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
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
import pages.LoginPage;
import utilities.GWD;

import java.time.Duration;

import static pages.ParentPage.click;

public class US009_US010_HamMenuFinance extends GWD {
    LoginPage login = new LoginPage(getDriver());

    @And("Logs into website.")
    public void userLogsIntoWebsite() {
        login.usernameBox.sendKeys("student10");
        login.passwordBox.sendKeys("pass.s10");
        login.loginButton.click();

    }

    WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
    HeaderMenu header = new HeaderMenu(getDriver());
    FinancePage finance = new FinancePage(getDriver());

    @Given("User Opens the website.")
    public void openWebsite() {
        GWD.getDriver().get("https://test.mersys.io/");
    }


    @When("User clicks hamburger menu.")
    public void ClicksHamburgerMenu() {
        click(header.hamburgerButton, 3);

    }

    @And("User clicks 'My finance' from 'Finance' option")
    public void ClicksFinance() {
        click(header.hamburgerButtonFinance, 3);
        click(header.hamburgerButtonMYFinance, 3);

    }

    @Then("User finds his\\/her name and clicks.")
    public void nameClicks() {
        wait.until(ExpectedConditions.elementToBeClickable(finance.chooseName));
        click(finance.chooseName, 5);

    }

    @And("User clicks 'Fee\\/Balance Detail'.")
    public void ClicksFeeDetail() {
        click(finance.feeBalanceDetail, 3);

    }

    @And("User should be able to see the details of payments.")
    public void checkPayments() {
        wait.until(ExpectedConditions.visibilityOf(finance.displayPayments));
        Assert.assertTrue(finance.displayPayments.isDisplayed());

    }

    @And("User clicks 'Stripe' to make a payment.")
    public void Stripe() {
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("input[type='radio'][value='STRIPE']")));
        ((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();", finance.stripe);

    }

    @And("User chooses 'Pay Amount Due 100.00$' to pay minimum amount.")
    public void payAmountDue() {
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//*[contains(@class, 'mdc-radio__background')])[4]")));
        ((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();", finance.amountDue);

    }

    @And("User enters cart info.")
    public void enterCartInfo() {
        WebElement stripeFrame = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.cssSelector("iframe[title='Güvenli ödeme giriş çerçevesi']")));
        getDriver().switchTo().frame(stripeFrame);

        wait.until(ExpectedConditions.elementToBeClickable(finance.cartNo));
        finance.cartNo.sendKeys("4242 4242 4242 4242");
        finance.expireDate.sendKeys("1230");
        finance.secureNumber.sendKeys("111");

        getDriver().switchTo().defaultContent();
        finance.pay.click();

    }

    @And("User clicks 'Stripe' to pay.")
    public void StripePay() {
        click(finance.pay, 3);
    }

}
