package stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import pages.LoginPage;
import utilities.ConfigReader;

import java.time.Duration;

import static pages.ParentPage.click;
import static pages.ParentPage.mySendKeys;
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
        logIn(ConfigReader.getRequiredProperty("student_username"),
                ConfigReader.getRequiredProperty("student_password"));
    }

    @Then("User should be successfully logged in and redirected to the homepage")
    public void userShouldBeSuccessfullyLoggedInAndRedirectedToTheHomepage() {
        wait.until(ExpectedConditions.visibilityOf(lp.dashboardHeader));
        Assert.assertTrue(lp.dashboardHeader.isDisplayed(), "ERROR: User is not redirected to the homepage!");
    }

    @When("User enters invalid username or invalid password")
    public void userEntersInvalidUsernameOrInvalidPassword() {
        logIn("Invalid.student", "Invalid.pass");
    }

    @Then("User should see an error message regarding invalid credentials")
    public void userShouldSeeAnErrorMessageRegardingInvalidCredentials() {
        wait.until(ExpectedConditions.visibilityOf(lp.errorMessage));
        Assert.assertTrue(lp.errorMessage.isDisplayed(), "ERROR: Validation message is missing!");
    }

    private void logIn(String username, String password) {
        click(lp.usernameBox, 10);
        mySendKeys(lp.usernameBox, username);
        click(lp.passwordBox, 10);
        mySendKeys(lp.passwordBox, password);
        click(lp.loginButton, 10);
    }
}
