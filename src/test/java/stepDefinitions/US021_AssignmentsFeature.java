package stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import pages.AssignmentsPage;
import pages.HeaderMenu;
import utilities.GWD;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.time.Duration;

import static pages.ParentPage.click;

public class US021_AssignmentsFeature extends GWD {

    HeaderMenu header = new HeaderMenu(getDriver());
    AssignmentsPage assign = new AssignmentsPage(getDriver());
    WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
    WebDriverWait fastWait = new WebDriverWait(getDriver(), Duration.ofSeconds(10), Duration.ofMillis(100));

    @When("User navigates to the 'Assignments' page.")
    public void navigateToAssignmentsPage() {
        click(header.headerAssignmentButton, 5);

    }

    @Then("User should see a 'Submit' icon on every homework in the Homework list.")
    public void checkSubmitIconVisible() {

        Assert.assertEquals(assign.numberOfHomeworks.size(), assign.submissionButtons.size(),
                "Submission buttons on homeworks are missing.");

    }

    @And("User clicks the 'Submit' icon on a homework.")
    public void clickSubmitIcon() {
        click(assign.submissionButtons.get(0), 3);

    }

    @Then("A pop-up text editor should open.")
    public void checkTextEditorOpen() {
        wait.until(ExpectedConditions.visibilityOf(assign.submissionDialog));
        Assert.assertTrue(assign.submissionDialog.isDisplayed());

    }

    @And("User types text into the text editor.")
    public void typeTextInEditor() {
        JavascriptExecutor js = (JavascriptExecutor) getDriver();

        wait.until(driver -> Boolean.TRUE.equals(js.executeScript(
                "return typeof tinymce !== 'undefined' && tinymce.activeEditor != null;")));

        js.executeScript("tinymce.activeEditor.setContent(arguments[0]);", "Test");

    }

    @And("User pastes text into the text editor.")
    public void pasteTextInEditor() {
        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        String current = (String) js.executeScript("return tinymce.activeEditor.getContent({format:'text'});");
        js.executeScript("tinymce.activeEditor.setContent(arguments[0]);", current + current);

        String content = (String) js.executeScript("return tinymce.activeEditor.getContent({format:'text'});");
        Assert.assertEquals(content, "TestTest");

    }

    @And("User inserts an image into the text editor.")
    public void insertImageInEditor() {
        String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\features\\files\\Test_foto.jpg";

        getDriver().switchTo().frame(assign.textEditorFrame);
        click(getDriver().findElement(By.id("tinymce")), 3);
        getDriver().switchTo().defaultContent();

        click(assign.insertImageButton, 3);

        WebElement fileInput = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.cssSelector("input[type='file'][accept='image/*']")));
        fileInput.sendKeys(filePath);

    }

    @And("User inserts a table into the text editor.")
    public void insertTableInEditor() {
        click(assign.insertTable, 3);
        click(assign.tableMenuItem, 3);
        click(assign.addTable, 3);

    }

    @And("User clicks 'Attach Files' and adds a file to the homework.")
    public void attachFileToHomework() throws InterruptedException, AWTException {
        String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\features\\files\\EXCUSE_FILE.pdf";

        click(assign.attachFiles, 3);
        click(assign.attachFromLocal, 3);

        Thread.sleep(1500);
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(new StringSelection(filePath), null);

        Robot robot = new Robot();
        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);

    }

    @And("User clicks 'Save As Draft'.")
    public void clickSaveAsDraft() {
        click(assign.saveAsDraft, 3);

    }

    @Then("User should see a 'Success' message.")
    public void checkSuccessMessage() {
        fastWait.until(ExpectedConditions.visibilityOf(assign.successMessageOnSubmission));
        Assert.assertTrue(assign.successMessageOnSubmission.isDisplayed());

    }

    @And("User clicks the 'Submit' button.")
    public void clickSendButton() {
        wait.until(ExpectedConditions.elementToBeClickable(assign.submitButton));
        ((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();", assign.submitButton);

    }

    @Then("A confirmation pop-up should open.")
    public void checkConfirmationPopupOpen() {
        wait.until(ExpectedConditions.visibilityOf(assign.yesButton)).click();

    }

    @When("User confirms the submission.")
    public void confirmSubmission() {
        fastWait.until(ExpectedConditions.visibilityOf(assign.successMessageOnSubmission));
        Assert.assertTrue(assign.successMessageOnSubmission.isDisplayed());

    }

    @Then("The 'Send' button should not be active.")
    public void checkSendButtonNotActive() {
        Assert.assertFalse(assign.submitButton.isEnabled());

    }

    @When("User opens the detail page of a homework.")
    public void openHomeworkDetailPage() {
        click(assign.firstHomeworkButton, 3);

    }

    @Then("User should see a 'New Submission' button.")
    public void checkNewSubmissionButtonVisible() {
        wait.until(ExpectedConditions.visibilityOf(assign.submissonButton));
        Assert.assertTrue(assign.submissonButton.isDisplayed());

    }

    @And("User clicks the 'New Submission' button.")
    public void clickNewSubmissionButton() {
        click(assign.submissonButton, 3);

    }

}
