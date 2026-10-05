package stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import pages.AssignmentsPage;
import pages.HeaderMenu;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.nio.file.Paths;
import java.time.Duration;

import static pages.ParentPage.click;
import static pages.ParentPage.isPresent;
import static pages.ParentPage.pause;
import static utilities.GWD.getDriver;

public class US021_Assignments {

    HeaderMenu hm = new HeaderMenu(getDriver());
    AssignmentsPage ap = new AssignmentsPage(getDriver());
    WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
    WebDriverWait fastWait = new WebDriverWait(getDriver(), Duration.ofSeconds(10), Duration.ofMillis(100));
    JavascriptExecutor js = (JavascriptExecutor) getDriver();

    @When("User opens the \"Assignments\" page")
    public void navigateToAssignmentsPage() {
        click(hm.headerAssignmentButton, 5);
    }

    @Then("User should see a \"Submit\" icon on every homework in the Homework list")
    public void checkSubmitIconVisible() {
        Assert.assertEquals(ap.numberOfHomeworks.size(), ap.submitButtonsList.size(),
                "Submission buttons on homeworks are missing.");
    }

    @And("User clicks the \"Submit\" icon on a homework")
    public void clickSubmitIcon() {
        click(ap.submitButtonsList.get(0), 3);
    }

    @Then("A pop-up text editor should open")
    public void checkTextEditorOpen() {
        wait.until(ExpectedConditions.visibilityOf(ap.submissionDialog));
        Assert.assertTrue(ap.submissionDialog.isDisplayed());
    }

    @And("User types text into the text editor")
    public void typeTextInEditor() {
        wait.until(driver -> Boolean.TRUE.equals(js.executeScript(
                "return typeof tinymce !== 'undefined' && tinymce.activeEditor != null;")));

        js.executeScript("tinymce.activeEditor.setContent(arguments[0]);", "Test");
    }

    @And("User pastes text into the text editor")
    public void pasteTextInEditor() {
        String current = (String) js.executeScript("return tinymce.activeEditor.getContent({format:'text'});");
        js.executeScript("tinymce.activeEditor.setContent(arguments[0]);", current + current);

        String content = (String) js.executeScript("return tinymce.activeEditor.getContent({format:'text'});");
        Assert.assertEquals(content, "TestTest");
    }

    @And("User inserts an image into the text editor")
    public void insertImageInEditor() {
        String filePath = Paths.get(System.getProperty("user.dir"), "src", "test", "resources", "features", "files", "Test_foto.jpg").toString();

        getDriver().switchTo().frame(ap.textEditorFrame);
        click(ap.editorBody, 3);
        getDriver().switchTo().defaultContent();

        click(ap.insertImageButton, 3);

        wait.until(driver -> isPresent(ap.imageFileInput));
        ap.imageFileInput.sendKeys(filePath);
    }

    @And("User inserts a table into the text editor")
    public void insertTableInEditor() {
        click(ap.insertTable, 3);
        click(ap.tableMenuItem, 3);
        click(ap.addTable, 3);
    }

    @And("User clicks \"Attach Files\" and adds a file to the homework")
    public void attachFileToHomework() throws AWTException {
        String filePath = Paths.get(System.getProperty("user.dir"), "src", "test", "resources", "features", "files", "EXCUSE_FILE.pdf").toString();

        click(ap.attachFiles, 3);
        click(ap.attachFromLocal, 3);

        pause(1500);
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(new StringSelection(filePath), null);

        Robot robot = new Robot();
        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
    }

    @And("User clicks \"Save As Draft\"")
    public void clickSaveAsDraft() {
        click(ap.saveAsDraft, 3);
    }

    @Then("User should see a \"Success\" message")
    public void checkSuccessMessage() {
        fastWait.until(ExpectedConditions.visibilityOf(ap.successMessageOnSubmission));
        Assert.assertTrue(ap.successMessageOnSubmission.isDisplayed());
    }

    @And("User clicks the \"Submit\" button")
    public void clickSendButton() {
        wait.until(ExpectedConditions.elementToBeClickable(ap.submitButton));
        js.executeScript("arguments[0].click();", ap.submitButton);
    }

    @Then("A confirmation pop-up should open")
    public void checkConfirmationPopupOpen() {
        Assert.assertTrue(wait.until(ExpectedConditions.visibilityOf(ap.yesButton)).isDisplayed());
    }

    @When("User confirms the submission")
    public void confirmSubmission() {
        click(ap.yesButton, 3);
    }

    @Then("The \"Send\" button should not be active")
    public void checkSendButtonNotActive() {
        Assert.assertFalse(ap.submitButton.isEnabled());
    }

    @When("User opens the detail page of a homework")
    public void openHomeworkDetailPage() {
        int rowCount = ap.assignmentRowsList.size();

        for (int row = 0; row < rowCount; row++) {
            openAssignmentRow(row);
            wait.until(ExpectedConditions.urlContains("/my-assignments/info/"));

            if (isPresent(ap.newSubmissionButton)) {
                return;
            }

            getDriver().navigate().back();
        }

        Assert.fail("None of the " + rowCount + " listed assignments offers a New Submission button.");
    }

    private void openAssignmentRow(int index) {
        for (int attempt = 0; attempt < 3; attempt++) {
            try {
                click(ap.assignmentRowsList.get(index), 10);
                return;
            } catch (StaleElementReferenceException listReRendered) {
            }
        }

        Assert.fail("The assignment list kept re-rendering; row " + (index + 1) + " could not be opened.");
    }

    @Then("User should see a \"New Submission\" button")
    public void checkNewSubmissionButtonVisible() {
        wait.until(ExpectedConditions.visibilityOf(ap.newSubmissionButton));
        Assert.assertTrue(ap.newSubmissionButton.isDisplayed());
    }

    @And("User clicks the \"New Submission\" button")
    public void clickNewSubmissionButton() {
        click(ap.newSubmissionButton, 3);
    }
}
