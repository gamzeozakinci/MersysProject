package stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import pages.LoginPage;
import pages.ParentPage;
import utilities.ConfigReader;

import java.time.Duration;

import static utilities.GWD.getDriver;

public class US001_Login {

    LoginPage lp = new LoginPage(getDriver());
    WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(20));

    @Given("User navigates to the {string} page")
    public void userNavigatesWebsite(String url) {
        getDriver().get(url);
    }

    @Given("User logs in with valid credentials")
    public void userLogsInWithValidCredentials() {
        ParentPage.click(lp.usernameBox,10);
        ParentPage.mySendKeys(lp.usernameBox, ConfigReader.getRequiredProperty("student_username"));
        ParentPage.click(lp.passwordBox,10);
        ParentPage.mySendKeys(lp.passwordBox, ConfigReader.getRequiredProperty("student_password"));
        ParentPage.click(lp.loginButton,10);
    }

    @Then("User should be successfully logged in and redirected to the homepage")
    public void userShouldBeSuccessfullyLoggedInAndRedirectedToTheHomepage() {
        wait.until(ExpectedConditions.visibilityOf(lp.dashboardHeader));
        Assert.assertTrue(lp.dashboardHeader.isDisplayed(), "ERROR: User is not redirected to the homepage!");
        System.out.println("Success: User successfully logged in.");
    }

    @When("User enters invalid username or invalid password")
    public void userEntersInvalidUsernameOrInvalidPassword() {
        ParentPage.click(lp.usernameBox,10);
        ParentPage.mySendKeys(lp.usernameBox, "Invalid.student");
        ParentPage.click(lp.passwordBox,10);
        ParentPage.mySendKeys(lp.passwordBox, "Invalid.pass");
        ParentPage.click(lp.loginButton,10);
    }

    @Then("User should see an error message regarding invalid credentials")
    public void userShouldSeeAnErrorMessageRegardingInvalidCredentials() {
        wait.until(ExpectedConditions.visibilityOf(lp.errorMessage));
        Assert.assertTrue(lp.errorMessage.isDisplayed(), "ERROR: Validation message is missing!");

        System.out.println("Success: Invalid login attempt was caught successfully.");
    }
}
