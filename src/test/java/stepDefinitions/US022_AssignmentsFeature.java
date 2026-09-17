package stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import pages.AssignmentsPage;
import utilities.GWD;

import java.time.Duration;
import java.util.List;
import java.util.function.Supplier;

import static pages.ParentPage.click;

public class US022_AssignmentsFeature extends GWD {

    AssignmentsPage assign = new AssignmentsPage(getDriver());
    WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));

    @Then("User should see the 'Search' button.")
    public void checkSearchButtonVisible() {
        wait.until(ExpectedConditions.visibilityOf(assign.searchButton));
        Assert.assertTrue(assign.searchButton.isDisplayed());

    }

    @When("User clicks the 'Search' button without applying any filter.")
    public void clickSearchButtonWithoutFilter() {
        click(assign.searchButton, 5);

    }

    @Then("User should see all assigned tasks listed.")
    public void checkAllAssignedTasksListed() {
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("div.assignment")));
        Assert.assertFalse(assign.assignmentRowsList.isEmpty());

    }

    @And("User filters the search by 'Course'.")
    public void filterSearchByCourse() {
        clickWithRetry(() -> assign.classFilterDropdown);
        wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(By.cssSelector("mat-option"), 1));
        clickWithRetry(() -> assign.filterOptionsList.get(1));
        closeOpenDropdown();

    }

    @And("User filters the search by 'Status'.")
    public void filterSearchByStatus() {
        clickWithRetry(() -> assign.statusFilterDropdown);
        wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(By.cssSelector("mat-option"), 1));
        clickWithRetry(() -> assign.filterOptionsList.get(1));
        closeOpenDropdown();

    }

    @And("User filters the search by 'Semester'.")
    public void filterSearchBySemester() {
        clickWithRetry(() -> assign.semesterFilterDropdown);
        wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(By.cssSelector("mat-option"), 1));
        clickWithRetry(() -> assign.filterOptionsList.get(1));
        closeOpenDropdown();

    }

    @And("User clicks the 'Search' button.")
    public void clickSearchButton() {
        clickWithRetry(() -> assign.searchButton);

    }

    @Then("User should see the filtered assignment results.")
    public void checkFilteredResultsVisible() {
        wait.until(ExpectedConditions.visibilityOf(assign.assignments));
        Assert.assertTrue(assign.assignments.isDisplayed());

    }

    @Then("User should see the 'Show By' dropdown menu.")
    public void checkShowByDropdownVisible() {
        wait.until(ExpectedConditions.visibilityOf(assign.showByDropdownButton));
        Assert.assertTrue(assign.showByDropdownButton.isDisplayed());

    }

    @When("User sorts the results by 'Course' from the 'Show By' dropdown.")
    public void sortResultsByCourse() {
        selectShowByOption("Show by Class");

    }

    @Then("User should see the results sorted by 'Course'.")
    public void checkResultsSortedByCourse() {
        wait.until(ExpectedConditions.textToBePresentInElement(assign.showByDropdownButton, "Class"));

    }

    @When("User sorts the results by 'Type' from the 'Show By' dropdown.")
    public void sortResultsByType() {
        selectShowByOption("Show by Type");

    }

    @Then("User should see the results sorted by 'Type'.")
    public void checkResultsSortedByType() {
        wait.until(ExpectedConditions.textToBePresentInElement(assign.showByDropdownButton, "Type"));

    }

    @When("User sorts the results by 'Date' from the 'Show By' dropdown.")
    public void sortResultsByDate() {
        selectShowByOption("Show by Date");

    }

    @Then("User should see the results sorted by 'Date'.")
    public void checkResultsSortedByDate() {
        wait.until(ExpectedConditions.textToBePresentInElement(assign.showByDropdownButton, "Date"));

    }

    @When("User sorts the results by 'Chart' from the 'Show By' dropdown.")
    public void sortResultsByChart() {
        selectShowByOption("Show by Chart");

    }

    @Then("User should see the results sorted by 'Chart'.")
    public void checkResultsSortedByChart() {
        wait.until(ExpectedConditions.textToBePresentInElement(assign.showByDropdownButton, "Chart"));

    }

    private void closeOpenDropdown() {
        new Actions(getDriver()).sendKeys(Keys.ESCAPE).perform();
        wait.until(d -> d.findElements(By.cssSelector("mat-select.mat-select-open")).isEmpty());

    }

    private void clickWithRetry(Supplier<WebElement> elementSupplier) {
        for (int i = 0; i < 3; i++) {
            try {
                new WebDriverWait(getDriver(), Duration.ofSeconds(3)).until(d -> {
                    List<WebElement> backdrops = d.findElements(By.cssSelector(".cdk-overlay-backdrop"));
                    return backdrops.stream().allMatch(b -> "0".equals(b.getCssValue("opacity")));
                });
            } catch (org.openqa.selenium.TimeoutException ignored) {
                // backdrop hasn't cleared in time - attempt the click anyway
            }

            try {
                WebElement element = elementSupplier.get();
                wait.until(ExpectedConditions.elementToBeClickable(element));
                element.click();
                return;
            } catch (ElementClickInterceptedException e) {
                if (i == 2) throw e;
            }
        }
    }

    private void selectShowByOption(String optionText) {
        click(assign.showByDropdownButton, 5);
        wait.until(ExpectedConditions.visibilityOfAllElements(assign.showByMenuItemsList));

        for (WebElement item : assign.showByMenuItemsList) {
            if (item.getText().trim().equals(optionText)) {
                item.click();
                return;
            }
        }
    }

}
