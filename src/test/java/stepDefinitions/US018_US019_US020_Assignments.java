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
import pages.ParentPage;
import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;
import static pages.ParentPage.hover;
import static pages.ParentPage.isPresent;
import static utilities.GWD.getDriver;
import java.nio.file.Paths;

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
    public void userVerifiesTotalNumberOfAssignedTasks(){
        wait.until(ExpectedConditions.visibilityOf(ap.assignmentsCountBadge));

        Assert.assertTrue(ap.assignmentsCountBadge.isDisplayed(), "The assignment count badge is not displayed!");
        String countText = ap.assignmentsCountBadge.getText();
        Assert.assertFalse(countText.isEmpty(), "The assignment count badge is empty!");
    }

    @When("User clicks on the {string} link on the home page")
    public void userClicksLinkOnHomepage(String linkName) {
        switch (linkName) {
            case "Assignments":
                wait.until(ExpectedConditions.visibilityOf(ap.assignmentsLink));

                JavascriptExecutor js = (JavascriptExecutor) getDriver();

                js.executeScript("arguments[0].click();", ap.assignmentsLink);
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
        int buttonCount = ap.discussionButtonsList.size();
        int randomIndex = (int) (Math.random() * buttonCount);
        ParentPage.click(ap.discussionButtonsList.get(randomIndex), 10);
        System.out.println("Clicked discussion icon " + (randomIndex + 1) + " of " + buttonCount + ".");
    }

    @Then("User verifies the chat area where they can view past discussions")
    public void userVerifiesDiscussionPage() {

        wait.until(ExpectedConditions.visibilityOf(ap.discussionChatArea));

        Assert.assertTrue(ap.discussionChatArea.isDisplayed(), "The discussion chat area is not displayed!");
    }

    @And("User types {string} into the text editor on the Assignment page")
    public void userTypesOnTextEditor(String text){
        ap.commentTextArea.sendKeys(text);

    }

    @When("User attaches a sample file for the assignment from the {string} section")
    public void userAttachesSampleFile(String attachText) throws AWTException {

        String filePath = Paths.get(System.getProperty("user.dir"), "src", "test", "resources", "features", "files", "blank.png").toString();
        StringSelection stringSelection = new StringSelection(filePath);
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);

        ParentPage.click(ap.attachFilesButton, 10);
        ParentPage.pause(2000);

        Robot robot = new Robot();

        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_CONTROL);

        ParentPage.pause(1000);

        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);

        ParentPage.pause(2000);
    }

    @And("User clicks the {string} button on the Assignment page")
    public void userClickSend(String text){
        ParentPage.click(ap.apSendButton,10);
    }

    @Then("User verifies that a {string} message is not displayed on the screen")
    public void userVerifiesNoSuccessDisplayed(String successText) {

        try {
            WebDriverWait shortWait = new WebDriverWait(getDriver(), Duration.ofSeconds(3));
            shortWait.until(ExpectedConditions.visibilityOf(ap.successMessage));

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
        System.out.println("Message sent; its time is displayed in the flow: " + ap.commentTimeList.get(lastMessageIndex).getText());
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

        checkIconIsReady(() -> ap.informationButtonsList, randomIndex, "Information");
        checkIconIsReady(() -> ap.submitButtonsList, randomIndex, "Submit");
        checkIconIsReady(() -> ap.markButtonsList, randomIndex, "Mark it");
    }

    private void checkIconIsReady(Supplier<List<WebElement>> iconList, int index, String iconName) {
        try {
            new WebDriverWait(getDriver(), Duration.ofSeconds(10)).until(driver -> {
                try {
                    List<WebElement> icons = new ArrayList<>(iconList.get());
                    return icons.size() > index && icons.get(index).isDisplayed() && icons.get(index).isEnabled();
                } catch (StaleElementReferenceException listRebuilt) {
                    return false;
                }
            });
        } catch (TimeoutException iconNotReady) {
            Assert.fail("The " + iconName + " icon is not displayed and clickable on the chosen assignment.");
        }
    }

    @When("User clicks anywhere except the icons on a random assignment")
    public void userClicksRandomAssignment() {

        wait.until(ExpectedConditions.visibilityOfAllElements(ap.assignmentRowsList));

        int randomIndex = (int) (Math.random() * ap.assignmentRowsList.size());

        ParentPage.click(ap.assignmentRowsList.get(randomIndex), 10);
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
            Assert.assertTrue(
                    ap.discussionButtonsList.get(0).isDisplayed(),
                    "The Discussion icon is not displayed!"
            );
        } else {
            System.out.println("No active discussion exists for these assignments.");
        }
    }
}

