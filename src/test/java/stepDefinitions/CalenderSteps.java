package stepDefinitions;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import pages.CalenderPage;
import utilities.GWD;

import java.time.Duration;
import static utilities.GWD.getDriver;

public class CalenderSteps {

     CalenderPage cp = new CalenderPage(getDriver());
     WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));;

    @Then("User is able to see class names")
    public void userIsAbleToSeeClassNames() {
        wait.until(ExpectedConditions.visibilityOfAllElements(cp.courseNamesTab));

        Assert.assertFalse(cp.courseNamesTab.isEmpty(), "No classes are displayed on the calendar!");
    }

    @And("User clicks on the previous week button")
    public void userClicksOnThePreviousWeekButton() {
        wait.until(ExpectedConditions.elementToBeClickable(cp.previusPageButton));
        cp.previusPageButton.click();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Success: Navigated to the previous week on the calendar.");
    }


    @And("User clicks on a completed class")
    public void userClicksOnACompletedClass() {
        wait.until(ExpectedConditions.elementToBeClickable(cp.completedClassEvent));

        cp.completedClassEvent.click();

        System.out.println("Success: Clicked on a completed class.");
    }

    @Then("User should see {string}, {string}, {string}, {string} tabs and confirm they are working")
    public void userShouldSeeTabsAndConfirmTheyAreWorking(String tab1, String tab2, String tab3, String tab4) {

        String[] expectedTabs = {tab1, tab2, tab3, tab4};

        for (String tabName : expectedTabs) {
            WebElement currentTab = null;

            switch (tabName) {
                case "Information":
                    currentTab = cp.informationTab;
                    break;
                case "Topic":
                    currentTab = cp.topicTab;
                    break;
                case "Attachments":
                    currentTab = cp.attachmentsTab;
                    break;
                case "Recent Events":
                    currentTab = cp.recentEventsTab;
                    break;
            }
            Assert.assertNotNull(currentTab, "Tab is not defined in the page class: " + tabName);

            wait.until(ExpectedConditions.visibilityOf(currentTab));
            Assert.assertTrue(currentTab.isDisplayed(), tabName + " tab is NOT visible!");

            Assert.assertTrue(currentTab.isEnabled(), tabName + " tab is NOT clickable!");

            currentTab.click();
            System.out.println("Verified: " + tabName + " tab is visible and working.");
        }
    }

    // US-23 - Weekly Schedule'ın varsayılan seçili olduğunu ve haftalık tarih aralığının görüntülendiğini doğrular
    @Then("User should see the current date and Weekly Course Plan by default")
    public void userShouldSeeTheCurrentDateAndWeeklyCoursePlanByDefault() {

        wait.until(ExpectedConditions.visibilityOf(cp.weeklyScheduleTab));
        Assert.assertTrue(cp.weeklyScheduleTab.isDisplayed(),
                "Weekly Schedule tab is not visible!");

        String selected = cp.weeklyScheduleTab.getAttribute("aria-selected");
        Assert.assertEquals(selected, "true",
                "Weekly Schedule is not selected by default!");

        wait.until(ExpectedConditions.visibilityOf(cp.weeklyDateRange));
        Assert.assertTrue(cp.weeklyDateRange.isDisplayed(),
                "Weekly date range is not visible!");

        Assert.assertFalse(cp.weeklyDateRange.getText().trim().isEmpty(),
                "Weekly date range is empty!");

        System.out.println("Weekly Schedule is selected by default.");
        System.out.println("Displayed date range: " + cp.weeklyDateRange.getText());
    }

    // US-23 - Weekly Course Plan üzerindeki P, S, E ve C durum ikonlarının görünürlüğünü doğrular
    @And("User should see course status icons {string}, {string}, {string}, {string}")
    public void userShouldSeeCourseStatusIcons(String p, String s, String e, String c) {

        wait.until(ExpectedConditions.visibilityOfAllElements(cp.statusLetters));

        String[] expectedStatuses = {p, s, e, c};

        for (String expectedStatus : expectedStatuses) {

            boolean statusFound = cp.statusLetters.stream()
                    .anyMatch(element ->
                            element.isDisplayed() &&
                                    element.getText().trim().equals(expectedStatus));

            Assert.assertTrue(statusFound,
                    expectedStatus + " status icon is not visible!");
        }

        System.out.println("P, S, E and C status icons are visible.");
    }

    // US-23 - Course status meanings (Published, Started, Ended, Cancelled) görünürlüğünü doğrular
    @And("User should see status meanings {string}, {string}, {string}, {string}")
    public void userShouldSeeStatusMeanings(String published, String started,
                                            String ended, String cancelled) {

        wait.until(ExpectedConditions.visibilityOfAllElements(cp.statusMeanings));

        String[] expectedMeanings = {published, started, ended, cancelled};

        for (String expectedMeaning : expectedMeanings) {

            boolean meaningFound = cp.statusMeanings.stream()
                    .anyMatch(element ->
                            element.isDisplayed() &&
                                    element.getText().trim().equals(expectedMeaning));

            Assert.assertTrue(meaningFound,
                    expectedMeaning + " status meaning is not visible!");
        }

        System.out.println("Published, Started, Ended and Cancelled status meanings are visible.");
    }

    // US-23 - Weekly Schedule ve Calendar sekmelerinin görünür ve tıklanabilir olduğunu doğrular
    @And("User should see and click {string} and {string} links")
    public void userShouldSeeAndClickWeeklyScheduleAndCalendarLinks(String weeklySchedule, String calendar) {

        wait.until(ExpectedConditions.visibilityOf(cp.weeklyScheduleTab));
        wait.until(ExpectedConditions.elementToBeClickable(cp.weeklyScheduleTab));

        wait.until(ExpectedConditions.visibilityOf(cp.calendarTab));
        wait.until(ExpectedConditions.elementToBeClickable(cp.calendarTab));

        Assert.assertTrue(cp.weeklyScheduleTab.isDisplayed(),
                weeklySchedule + " tab is not visible!");

        Assert.assertTrue(cp.calendarTab.isDisplayed(),
                calendar + " tab is not visible!");

        cp.calendarTab.click();
        wait.until(ExpectedConditions.attributeToBe(cp.calendarTab, "aria-selected", "true"));

        cp.weeklyScheduleTab.click();
        wait.until(ExpectedConditions.attributeToBe(cp.weeklyScheduleTab, "aria-selected", "true"));

        System.out.println("Weekly Schedule and Calendar tabs are visible and clickable.");
    }

    // US-23 - Previous, Today ve Next navigasyon butonlarının görünür,tıklanabilir ve çalışır olduğunu doğrular
    @And("User should see and click Previous, Today and Next navigation buttons")
    public void userShouldSeeAndClickPreviousTodayAndNextNavigationButtons() {

        wait.until(ExpectedConditions.visibilityOfAllElements(cp.scheduleActionButtons));

        Assert.assertTrue(cp.scheduleActionButtons.size() >= 3,
                "Previous, Today and Next navigation buttons are not available!");

        WebElement previousButton = cp.scheduleActionButtons.get(0);
        WebElement todayButton = cp.scheduleActionButtons.get(1);
        WebElement nextButton = cp.scheduleActionButtons.get(2);

        Assert.assertTrue(previousButton.isDisplayed(),
                "Previous navigation button is not visible!");
        Assert.assertTrue(todayButton.isDisplayed(),
                "Today navigation button is not visible!");
        Assert.assertTrue(nextButton.isDisplayed(),
                "Next navigation button is not visible!");

        wait.until(ExpectedConditions.elementToBeClickable(previousButton));
        previousButton.click();

        wait.until(ExpectedConditions.elementToBeClickable(todayButton));
        todayButton.click();

        wait.until(ExpectedConditions.elementToBeClickable(nextButton));
        nextButton.click();

        wait.until(ExpectedConditions.elementToBeClickable(todayButton));
        todayButton.click();

        System.out.println("Previous, Today and Next navigation buttons are visible and clickable.");

    }

    // US-23 - Weekly Schedule üzerinde sorumlu olunan derslerin görünür ve tıklanabilir olduğunu doğrular
    @And("User should see and click responsible courses")
    public void userShouldSeeAndClickResponsibleCourses() {

        wait.until(driver -> {
            try {
                if (cp.responsibleCourses.isEmpty()) {
                    return false;
                }

                WebElement course = cp.responsibleCourses.get(0);

                if (course.isDisplayed() && course.isEnabled()) {
                    course.click();
                    return true;
                }

                return false;

            } catch (org.openqa.selenium.StaleElementReferenceException e) {
                return false;
            }
        });

        System.out.println("A responsible course is visible and clickable.");
    }

    // US-25 - Completed (E) durumundaki dersi bulana kadar önceki haftalara gider ve bulunan derslerden rastgele birine tıklar
    @And("User clicks on a random completed class")
    public void userClicksOnARandomCompletedClass() {

        int maxWeeks = 10;

        for (int i = 0; i < maxWeeks; i++) {

            // Bu haftada Completed (E) ders var mı?
            if (!cp.completedClasses.isEmpty()) {

                int randomIndex =
                        new java.util.Random().nextInt(cp.completedClasses.size());

                WebElement randomCompletedClass =
                        cp.completedClasses.get(randomIndex);

                wait.until(ExpectedConditions.elementToBeClickable(randomCompletedClass));
                randomCompletedClass.click();

                System.out.println(
                        "Clicked on a random completed (E) class.");
                return;
            }

            // E ders yoksa bir önceki haftaya geç
            wait.until(ExpectedConditions.elementToBeClickable(cp.previusPageButton));
            cp.previusPageButton.click();

            // Yeni haftanın yüklenmesini kısa süre bekle
            try {
                Thread.sleep(1500);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        Assert.fail(
                "No completed (E) class was found within the previous "
                        + maxWeeks + " weeks!"
        );
    }

    // US-25 - Completed class detay penceresinde Recording butonunun görünür ve tıklanabilir olduğunu doğrular ve butona tıklar
    @Then("User should see and click the Recording button")
    public void userShouldSeeAndClickTheRecordingButton() {

        wait.until(ExpectedConditions.visibilityOf(cp.recordingButton));

        Assert.assertTrue(cp.recordingButton.isDisplayed(),
                "Recording button is not visible!");

        wait.until(ExpectedConditions.elementToBeClickable(cp.recordingButton));

        cp.recordingButton.click();

        System.out.println("Recording button is visible and clickable.");
    }

    // US-25 - Recording sayfasına erişildiğini ve player iframe'inin açıldığını doğrular
    @And("User should access the class recording")
    public void userShouldAccessTheClassRecording() {

        WebDriverWait wait = new WebDriverWait(GWD.getDriver(), Duration.ofSeconds(20));

        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(
                cp.recordingIframe
        ));

        wait.until(ExpectedConditions.visibilityOf(cp.playButton));

        System.out.println("Class recording page opened and video player is visible.");


    }

    // US-25 - Recording ekranındaki Play butonunun görünür ve tıklanabilir olduğunu doğrular ve tıklar
    @Then("User should see and click the Play button")
    public void userShouldSeeAndClickThePlayButton() {

        wait.until(ExpectedConditions.visibilityOf(cp.playButton));
        wait.until(ExpectedConditions.elementToBeClickable(cp.playButton));

        cp.playButton.click();

        System.out.println("Play button is visible and clicked.");

    }

    // US-25 - Play butonuna tıklandıktan sonra videonun gerçekten başladığını doğrular
    @And("User should be able to start watching the class video")
    public void userShouldBeAbleToStartWatchingTheClassVideo() {

        wait.until(ExpectedConditions.visibilityOf(cp.recordingVideo));

        Boolean isPaused = (Boolean) ((org.openqa.selenium.JavascriptExecutor) getDriver())
                .executeScript("return arguments[0].paused;", cp.recordingVideo);

        Assert.assertFalse(isPaused,
                "Class video did not start playing!");

        System.out.println("Class video started successfully.");
    }


}

