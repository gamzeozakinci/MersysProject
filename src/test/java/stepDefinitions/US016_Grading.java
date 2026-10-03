package stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import pages.GradingPage;

import java.time.Duration;

import static utilities.GWD.getDriver;

public class US016_Grading {

    GradingPage grading = new GradingPage(getDriver());
    WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));

    @Then("User verifies being successfully redirected to the {string} page")
    public void userVerifiesRedirectedToPage(String pageName) {
        wait.until(ExpectedConditions.urlContains("grading"));

        Assert.assertTrue(getDriver().getCurrentUrl().contains("grading"),
                "The " + pageName + " page did not open, the browser is on " + getDriver().getCurrentUrl());
    }

    @And("User verifies that the {string} button on the page is visible and clickable")
    public void userVerifiesTabIsVisibleAndClickable(String tabName) {
        WebElement tab;

        switch (tabName) {
            case "Class Grade":
                tab = grading.classGradeTab;
                break;
            case "Reports":
                tab = grading.reportsTab;
                break;
            default:
                throw new IllegalArgumentException("No Grading tab defined for: " + tabName);
        }

        wait.until(ExpectedConditions.visibilityOf(tab));
        wait.until(ExpectedConditions.elementToBeClickable(tab)).click();

        wait.until(ExpectedConditions.attributeToBe(tab, "aria-selected", "true"));
    }

    @And("User verifies that the course grades are successfully displayed in the list")
    public void userVerifiesCourseGradesDisplayed() {
        wait.until(driver -> !grading.gradeRows.isEmpty());

        Assert.assertFalse(grading.gradeRows.isEmpty(), "No class grades are listed on the Grading page.");
    }

    @And("User verifies that the {string} section is listed under Reports")
    public void userVerifiesReportSectionListed(String sectionName) {
        wait.until(ExpectedConditions.visibilityOf(grading.studentTranscriptsSection));

        Assert.assertTrue(grading.studentTranscriptsSection.isDisplayed(),
                "The " + sectionName + " section is not shown under Reports.");
    }
}
