package stepDefinitions;

import io.cucumber.java.PendingException;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;
import pages.NavigationPage;
import utilities.GWD;

public class US002_CompanyLogo {

    NavigationPage np = new NavigationPage(GWD.getDriver());

    @Then("User should see the company logo")
    public void userShouldSeeTheCompanyLogo() {
        Assert.assertTrue(np.companyLogo.isDisplayed());
    }


    @When("User clicks the company logo")
    public void userClicksTheCompanyLogo() {
        np.companyLogo.click();
    }

    @Then("User should be redirected to Techno Study website")
    public void userShouldBeRedirectedToTechnoStudyWebsite() {
        for (String window : GWD.getDriver().getWindowHandles()) {
            GWD.getDriver().switchTo().window(window);
        }

        Assert.assertTrue(GWD.getDriver().getCurrentUrl().contains("technostudy.com.tr"));
    }
}
