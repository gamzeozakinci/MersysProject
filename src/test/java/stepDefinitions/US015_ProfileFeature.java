package stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import pages.SettingsPage;
import utilities.GWD;

import java.time.Duration;

public class US015_ProfileFeature extends GWD {

    SettingsPage settpage = new SettingsPage(getDriver());
    WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));


    @Given("User Opens the website.")
    public void openWebsite() {
        GWD.getDriver().get("https://test.mersys.io/");
    }

    @When("User clicks settings on profile dropdown menu.")
    public void clickSettingsOnProfileDropdownMenu() {

        settpage.profile.click();
        wait.until(ExpectedConditions.elementToBeClickable(settpage.settings));
        settpage.settings.click();

    }

    @Then("User confirms that 'purple' theme is applied.")
    public void confirmPurple() {
        Assert.assertTrue(settpage.confirmPurple.isDisplayed());
    }

    @And("User changes default theme to purple.")
    public void changeDefaultThemeToPurple() {
        settpage.themeDropdown.click();
        settpage.purple.click();

    }

    @Then("User confirms that 'dark purple' theme is applied.")
    public void confirmDarkPurple() {
        Assert.assertTrue(settpage.confirmDarkPurple.isDisplayed());
    }

    @And("User changes default theme to dark purple.")
    public void changeDefaultThemeToDarkPurple() {
        settpage.themeDropdown.click();
        settpage.darkpurple.click();

    }

    @Then("User confirms that 'indigo' theme is applied.")
    public void confirmIndigo() {
        Assert.assertTrue(settpage.confirmIndigo.isDisplayed());
    }

    @And("User changes default theme to indigo.")
    public void changeDefaultThemeToIndigo() {

        settpage.themeDropdown.click();
        settpage.indigo.click();

    }


    @And("User must see the success message when clicked 'save' button.")
    public void clickSaveButton() {
        settpage.saveButton.click();
        wait.until(ExpectedConditions.visibilityOf(settpage.saveConfirm));
        Assert.assertTrue(settpage.saveConfirm.isDisplayed());


    }

}
