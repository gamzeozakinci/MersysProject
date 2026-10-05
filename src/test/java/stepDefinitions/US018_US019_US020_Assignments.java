package stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import pages.AssignmentsPage;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import static pages.ParentPage.click;
import static pages.ParentPage.hover;
import static pages.ParentPage.isPresent;
import static pages.ParentPage.pause;
import static utilities.GWD.getDriver;

public class US018_US019_US020_Assignments {

    AssignmentsPage ap = new AssignmentsPage(getDriver());
    WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));

    @Given("User hovers over the {string} link on the home page")
    public void userHoversOverLinkNameOnHomepage(String linkName) {
        wait.until(ExpectedConditions.visibilityOf(ap.assignmentsLink));

        for (int attempt = 0; attempt < 3; attempt++) {
            new Actions(getDriver()).moveToElement(ap.pageBody, 5, 5).perform();
            hover(ap.assignmentsLink);

            try {
                new WebDriverWait(getDriver(), Duration.ofSeconds(3))
                        .until(driver -> isPresent(ap.assignmentsCountBadge));
                return;
            } catch (TimeoutException tooltipDidNotOpen) {
            }
        }

        Assert.fail("The assignment count tooltip did not open after hovering the Assignments link.");
    }

    @Then("User verifies that the total number of assigned tasks is displayed")
    public void userVerifiesTotalNumberOfAssignedTasks() {
        wait.until(ExpectedConditions.visibilityOf(ap.assignmentsCountBadge));

        Assert.assertTrue(ap.assignmentsCountBadge.isDisplayed(), "The assignment count badge is not displayed!");
        Assert.assertFalse(ap.assignmentsCountBadge.getText().isEmpty(), "The assignment count badge is empty!");
    }

    @When("User clicks on the {string} link on the home page")
    public void userClicksLinkOnHomepage(String linkName) {
        switch (linkName) {
            case "Assignments":
                wait.until(ExpectedConditions.visibilityOf(ap.assignmentsLink));
                ((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();", ap.assignmentsLink);
                break;

            default:
                throw new IllegalArgumentException("No home page link defined for: " + linkName);
        }
    }

    @Then("User verifies that all assigned tasks are listed")
    public void userVerifiesThatAllAssignedTasksAreListed() {
        wait.until(ExpectedConditions.visibilityOf(ap.assignments));

        Assert.assertTrue(ap.assignments.isDisplayed(), "The assignments list did not open!");
    }

    @When("User clicks on the {string} icon of a random assignment in the list")
    public void userClickOnRandomAssignmentOnTheList(String iconName) {
        wait.until(ExpectedConditions.visibilityOfAllElements(ap.discussionButtonsList));

        int randomIndex = (int) (Math.random() * ap.discussionButtonsList.size());
        click(ap.discussionButtonsList.get(randomIndex), 10);
    }

    @Then("User verifies the chat area where they can view past discussions")
    public void userVerifiesDiscussionPage() {
        wait.until(ExpectedConditions.visibilityOf(ap.discussionChatArea));

        Assert.assertTrue(ap.discussionChatArea.isDisplayed(), "The discussion chat area is not displayed!");
    }

    @And("User types {string} into the text editor on the Assignment page")
    public void userTypesOnTextEditor(String text) {
        ap.commentTextArea.sendKeys(text);
    }

    @When("User attaches a sample file for the assignment from the {string} section")
    public void userAttachesSampleFile(String attachText) throws AWTException {
        String filePath = Paths.get(System.getProperty("user.dir"), "src", "test", "resources", "features", "files", "blank.png").toString();
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(new StringSelection(filePath), null);

        click(ap.attachFilesButton, 10);
        pause(2000);

        Robot robot = new Robot();

        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_CONTROL);

        pause(1000);

        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);

        pause(2000);
    }

    @And("User clicks the {string} button on the Assignment page")
    public void userClickSend(String text) {
        click(ap.apSendButton, 10);
    }

    @Then("User verifies that a {string} message is not displayed on the screen")
    public void userVerifiesNoSuccessDisplayed(String successText) {
        try {
            new WebDriverWait(getDriver(), Duration.ofSeconds(3))
                    .until(ExpectedConditions.visibilityOf(ap.successMessage));

            Assert.fail("A Success message was displayed, which violates the acceptance criteria!");
        } catch (TimeoutException | NoSuchElementException e) {
            System.out.println("Negative check passed: no Success message was displayed, as expected.");
        }
    }

    @Then("User verifies that the sent message, file name, and time information are displayed in the flow")
    public void userVerifiesMessageInformation() {
        wait.until(ExpectedConditions.visibilityOfAllElements(ap.commentTimeList));

        int lastMessageIndex = ap.commentTimeList.size() - 1;
        Assert.assertTrue(ap.commentTimeList.get(lastMessageIndex).isDisplayed(), "The time of the sent message is not displayed in the flow!");
    }

    @Then("User should see Information, Submit and Mark it icons on a random assignment")
    public void userShouldSeeQuickActionIconsOnRandomAssignment() {
        wait.until(driver -> !ap.informationButtonsList.isEmpty());
        wait.until(driver -> !ap.submitButtonsList.isEmpty());
        wait.until(driver -> !ap.markButtonsList.isEmpty());

        int minCount = Math.min(
                ap.informationButtonsList.size(),
                Math.min(ap.submitButtonsList.size(), ap.markButtonsList.size())
        );

        Assert.assertTrue(minCount > 0, "No assignment found to check!");

        int randomIndex = (int) (Math.random() * minCount);

        assertIconIsReady(ap.informationButtonsList, randomIndex, "Information");
        assertIconIsReady(ap.submitButtonsList, randomIndex, "Submit");
        assertIconIsReady(ap.markButtonsList, randomIndex, "Mark it");
    }

    private void assertIconIsReady(List<WebElement> icons, int index, String iconName) {
        try {
            wait.until(driver -> iconIsReady(icons, index));
        } catch (TimeoutException iconNotReady) {
            Assert.fail("The " + iconName + " icon is not displayed and clickable on the chosen assignment.");
        }
    }

    private boolean iconIsReady(List<WebElement> icons, int index) {
        try {
            List<WebElement> iconsNow = new ArrayList<>(icons);
            return iconsNow.size() > index && iconsNow.get(index).isDisplayed() && iconsNow.get(index).isEnabled();
        } catch (StaleElementReferenceException listRebuilt) {
            return false;
        }
    }

    @When("User clicks anywhere except the icons on a random assignment")
    public void userClicksRandomAssignment() {
        wait.until(ExpectedConditions.visibilityOfAllElements(ap.assignmentRowsList));

        int randomIndex = (int) (Math.random() * ap.assignmentRowsList.size());
        click(ap.assignmentRowsList.get(randomIndex), 10);
    }

    @Then("User should access the assignment details page")
    public void userShouldAccessAssignmentDetailsPage() {
        wait.until(ExpectedConditions.urlContains("/my-assignments/info/"));

        Assert.assertTrue(
                getDriver().getCurrentUrl().contains("/my-assignments/info/"),
                "Not redirected to the assignment details page!");
    }

    @Then("User should see Discussion icon if a discussion exists for the assignment")
    public void userShouldSeeDiscussionIconIfDiscussionExists() {
        if (!ap.discussionButtonsList.isEmpty()) {
            Assert.assertTrue(ap.discussionButtonsList.get(0).isDisplayed(), "The Discussion icon is not displayed!");
        }
    }
}
