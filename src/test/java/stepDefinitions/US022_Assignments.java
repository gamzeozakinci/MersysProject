package stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.Keys;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import pages.AssignmentsPage;

import java.time.Duration;

import static pages.ParentPage.click;
import static utilities.GWD.getDriver;

public class US022_Assignments {

    AssignmentsPage ap = new AssignmentsPage(getDriver());
    WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));

    @Then("User should see the \"Search\" button")
    public void checkSearchButtonVisible() {
        wait.until(ExpectedConditions.visibilityOf(ap.searchButton));
        Assert.assertTrue(ap.searchButton.isDisplayed());
    }

    @When("User clicks the \"Search\" button without applying any filter")
    public void clickSearchButtonWithoutFilter() {
        click(ap.searchButton, 5);
    }

    @Then("User should see all assigned tasks listed")
    public void checkAllAssignedTasksListed() {
        wait.until(driver -> !ap.assignmentRowsList.isEmpty());
        Assert.assertFalse(ap.assignmentRowsList.isEmpty());
    }

    @And("User filters the search by {string}")
    public void filterSearchBy(String filterName) {
        WebElement dropdown;

        switch (filterName) {
            case "Course":
                dropdown = ap.classFilterDropdown;
                break;
            case "Status":
                dropdown = ap.statusFilterDropdown;
                break;
            case "Semester":
                dropdown = ap.semesterFilterDropdown;
                break;
            default:
                throw new IllegalArgumentException("No filter defined for: " + filterName);
        }

        clickWithRetry(dropdown);
        wait.until(driver -> ap.filterOptionsList.size() > 1);
        clickWithRetry(ap.filterOptionsList.get(1));
        closeOpenDropdown();
    }

    @And("User clicks the \"Search\" button")
    public void clickSearchButton() {
        clickWithRetry(ap.searchButton);
    }

    @Then("User should see the filtered assignment results")
    public void checkFilteredResultsVisible() {
        wait.until(ExpectedConditions.visibilityOf(ap.assignments));
        Assert.assertTrue(ap.assignments.isDisplayed());
    }

    @Then("User should see the \"Show By\" dropdown menu")
    public void checkShowByDropdownVisible() {
        wait.until(ExpectedConditions.visibilityOf(ap.showByDropdownButton));
        Assert.assertTrue(ap.showByDropdownButton.isDisplayed());
    }

    @When("User sorts the results by {string} from the \"Show By\" dropdown")
    public void sortResultsBy(String sortName) {
        selectShowByOption("Show by " + showByWord(sortName));
    }

    @Then("User should see the results sorted by {string}")
    public void checkResultsSortedBy(String sortName) {
        wait.until(ExpectedConditions.textToBePresentInElement(ap.showByDropdownButton, showByWord(sortName)));
    }

    private String showByWord(String sortName) {
        return sortName.equals("Course") ? "Class" : sortName;
    }

    private void closeOpenDropdown() {
        new Actions(getDriver()).sendKeys(Keys.ESCAPE).perform();
        wait.until(driver -> ap.openDropdowns.isEmpty());
    }

    private void clickWithRetry(WebElement element) {
        for (int attempt = 1; attempt <= 3; attempt++) {
            waitForOverlaysToFade();

            try {
                wait.until(ExpectedConditions.elementToBeClickable(element));
                element.click();
                return;
            } catch (ElementClickInterceptedException clickBlocked) {
                if (attempt == 3) {
                    throw clickBlocked;
                }
            }
        }
    }

    private void waitForOverlaysToFade() {
        try {
            new WebDriverWait(getDriver(), Duration.ofSeconds(3)).until(driver -> noOverlayIsVisible());
        } catch (TimeoutException overlayStillThere) {
        }
    }

    private boolean noOverlayIsVisible() {
        for (WebElement overlay : ap.overlayBackdrops) {
            if (!overlay.getCssValue("opacity").equals("0")) {
                return false;
            }
        }

        return true;
    }

    private void selectShowByOption(String optionText) {
        click(ap.showByDropdownButton, 5);
        wait.until(ExpectedConditions.visibilityOfAllElements(ap.showByMenuItemsList));

        for (WebElement item : ap.showByMenuItemsList) {
            if (item.getText().trim().equals(optionText)) {
                item.click();
                return;
            }
        }
    }
}
