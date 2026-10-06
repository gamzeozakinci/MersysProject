package stepDefinitions;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.TimeoutException;
import org.testng.Assert;
import pages.NavigationPage;

import static utilities.GWD.getDriver;

public class US002_CompanyLogo {

    NavigationPage np = new NavigationPage(getDriver());

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
        String url = "";

        for (int attempt = 0; attempt < 3; attempt++) {
            try {
                for (String window : getDriver().getWindowHandles()) {
                    getDriver().switchTo().window(window);
                }

                url = getDriver().getCurrentUrl();
                break;
            } catch (TimeoutException pageStillLoading) {
            }
        }

        Assert.assertTrue(url.contains("technostudy.com.tr"),
                "The Techno Study site did not open, the browser is on: " + url);
    }
}
