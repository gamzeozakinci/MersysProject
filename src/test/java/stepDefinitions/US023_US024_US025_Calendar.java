package stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import pages.CalendarPage;

import java.time.Duration;
import java.util.List;
import java.util.Random;

import static pages.ParentPage.click;
import static pages.ParentPage.pause;
import static utilities.GWD.getDriver;

public class US023_US024_US025_Calendar {

    CalendarPage cp = new CalendarPage(getDriver());
    Wait<WebDriver> wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10))
            .ignoring(StaleElementReferenceException.class);

    @Then("User is able to see class names")
    public void userIsAbleToSeeClassNames() {
        wait.until(ExpectedConditions.visibilityOfAllElements(cp.courseNamesTab));

        Assert.assertFalse(cp.courseNamesTab.isEmpty(), "No classes are displayed on the calendar!");
    }

    @And("User clicks on the previous week button")
    public void userClicksOnThePreviousWeekButton() {
        click(cp.previousWeekButton, 10);
        pause(2000);
    }

    @And("User clicks on a completed class")
    public void userClicksOnACompletedClass() {
        clickCompletedClass();
    }

    @And("User clicks on a random completed class")
    public void userClicksOnARandomCompletedClass() {
        clickCompletedClass();
    }

    private void clickCompletedClass() {
        int maxWeeks = 10;

        for (int week = 0; week < maxWeeks; week++) {
            if (!cp.completedClasses.isEmpty()) {
                int randomIndex = new Random().nextInt(cp.completedClasses.size());
                click(cp.completedClasses.get(randomIndex), 10);
                return;
            }

            click(cp.previousWeekButton, 10);
            pause(1500);
        }

        Assert.fail("No completed (E) class was found within the previous " + maxWeeks + " weeks!");
    }

    @Then("User should see {string}, {string}, {string}, {string} tabs and confirm they are working")
    public void userShouldSeeTabsAndConfirmTheyAreWorking(String tab1, String tab2, String tab3, String tab4) {
        for (String tabName : new String[]{tab1, tab2, tab3, tab4}) {
            WebElement tab = findTab(tabName);

            wait.until(ExpectedConditions.visibilityOf(tab));
            Assert.assertTrue(tab.isEnabled(), tabName + " tab is NOT clickable!");

            tab.click();
        }
    }

    private WebElement findTab(String tabName) {
        switch (tabName) {
            case "Information":
                return cp.informationTab;
            case "Topic":
                return cp.topicTab;
            case "Attachments":
                return cp.attachmentsTab;
            case "Recent Events":
                return cp.recentEventsTab;
            default:
                throw new IllegalArgumentException("Tab is not defined in the page class: " + tabName);
        }
    }

    @Then("User should see the current date and Weekly Course Plan by default")
    public void userShouldSeeTheCurrentDateAndWeeklyCoursePlanByDefault() {
        wait.until(ExpectedConditions.visibilityOf(cp.weeklyScheduleTab));
        Assert.assertEquals(cp.weeklyScheduleTab.getAttribute("aria-selected"), "true",
                "Weekly Schedule is not selected by default!");

        wait.until(ExpectedConditions.visibilityOf(cp.weeklyDateRange));
        Assert.assertFalse(cp.weeklyDateRange.getText().trim().isEmpty(),
                "Weekly date range is empty!");
    }

    @And("User should see course status icons {string}, {string}, {string}, {string}")
    public void userShouldSeeCourseStatusIcons(String p, String s, String e, String c) {
        wait.until(ExpectedConditions.visibilityOfAllElements(cp.statusLetters));

        checkTextsAreShown(cp.statusLetters, "status icon", p, s, e, c);
    }

    @And("User should see status meanings {string}, {string}, {string}, {string}")
    public void userShouldSeeStatusMeanings(String published, String started, String ended, String cancelled) {
        wait.until(ExpectedConditions.visibilityOfAllElements(cp.statusMeanings));

        checkTextsAreShown(cp.statusMeanings, "status meaning", published, started, ended, cancelled);
    }

    private void checkTextsAreShown(List<WebElement> elements, String what, String... expectedTexts) {
        for (String expected : expectedTexts) {
            boolean found = false;

            for (WebElement element : elements) {
                if (element.isDisplayed() && element.getText().trim().equals(expected)) {
                    found = true;
                }
            }

            Assert.assertTrue(found, expected + " " + what + " is not visible!");
        }
    }

    @And("User should see and click {string} and {string} links")
    public void userShouldSeeAndClickWeeklyScheduleAndCalendarLinks(String weeklySchedule, String calendar) {
        click(cp.calendarTab, 10);
        wait.until(ExpectedConditions.attributeToBe(cp.calendarTab, "aria-selected", "true"));

        click(cp.weeklyScheduleTab, 10);
        wait.until(ExpectedConditions.attributeToBe(cp.weeklyScheduleTab, "aria-selected", "true"));
    }

    @And("User should see and click Previous, Today and Next navigation buttons")
    public void userShouldSeeAndClickPreviousTodayAndNextNavigationButtons() {
        wait.until(ExpectedConditions.visibilityOfAllElements(cp.scheduleActionButtons));

        Assert.assertTrue(cp.scheduleActionButtons.size() >= 3,
                "Previous, Today and Next navigation buttons are not available!");

        WebElement previousButton = cp.scheduleActionButtons.get(0);
        WebElement todayButton = cp.scheduleActionButtons.get(1);
        WebElement nextButton = cp.scheduleActionButtons.get(2);

        click(previousButton, 10);
        click(todayButton, 10);
        click(nextButton, 10);
        click(todayButton, 10);
    }

    @And("User should see and click responsible courses")
    public void userShouldSeeAndClickResponsibleCourses() {
        for (int attempt = 0; attempt < 3; attempt++) {
            try {
                wait.until(driver -> !cp.responsibleCourses.isEmpty());
                click(cp.responsibleCourses.get(0), 10);
                return;
            } catch (StaleElementReferenceException | TimeoutException calendarRedrawn) {
            }
        }

        Assert.fail("The responsible course kept changing, so it could not be clicked.");
    }

    @Then("User should see and click the Recording button")
    public void userShouldSeeAndClickTheRecordingButton() {
        click(cp.recordingButton, 10);
    }

    @And("User should access the class recording")
    public void userShouldAccessTheClassRecording() {
        WebDriverWait longWait = new WebDriverWait(getDriver(), Duration.ofSeconds(20));

        longWait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(cp.recordingIframe));
        longWait.until(ExpectedConditions.visibilityOf(cp.playButton));
    }

    @Then("User should see and click the Play button")
    public void userShouldSeeAndClickThePlayButton() {
        click(cp.playButton, 10);
    }

    @And("User should be able to start watching the class video")
    public void userShouldBeAbleToStartWatchingTheClassVideo() {
        wait.until(ExpectedConditions.visibilityOf(cp.recordingVideo));

        Boolean isPaused = (Boolean) ((JavascriptExecutor) getDriver())
                .executeScript("return arguments[0].paused;", cp.recordingVideo);

        Assert.assertFalse(isPaused, "Class video did not start playing!");
    }
}
