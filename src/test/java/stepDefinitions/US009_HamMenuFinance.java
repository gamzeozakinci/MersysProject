package stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import pages.FinancePage;
import pages.HeaderMenu;
import utilities.GWD;

import java.time.Duration;

import static pages.ParentPage.click;

public class US009_HamMenuFinance extends GWD {

    WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
    HeaderMenu header = new HeaderMenu(getDriver());
    FinancePage finance = new FinancePage(getDriver());


    @When("User clicks hamburger menu")
    public void ClicksHamburgerMenu() {
        click(header.hamburgerButton, 2000);

    }

    @And("Clicks 'My finance' from 'Finance' option")
    public void ClicksFinance() {
        click(header.hamburgerButtonFinance, 2000);
        click(header.hamburgerButtonMYFinance, 2000);


    }

    @Then("User finds her/his name and clicks.")
    public void nameClicks() {
        click(finance.chooseName, 2000);
    }



}
