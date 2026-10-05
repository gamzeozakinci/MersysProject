package stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import pages.SettingsPage;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.nio.file.Paths;
import java.time.Duration;

import static pages.ParentPage.pause;
import static utilities.GWD.getDriver;

public class US014_ProfilePicture {

    SettingsPage settpage = new SettingsPage(getDriver());
    WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));

    @And("User clicks on the profile picture")
    public void userClicksOnTheProfilePicture() {
        settpage.profilePicture.click();
    }

    @Then("Profile Photo window should be displayed")
    public void profilePhotoWindowShouldBeDisplayed() {
        wait.until(ExpectedConditions.visibilityOf(settpage.profilePhotoWindowTitle));

        Assert.assertTrue(settpage.profilePhotoWindowTitle.isDisplayed());
    }

    @When("User selects a profile picture")
    public void userSelectsAProfilePicture() throws AWTException {
        settpage.fileSelectButton.click();

        pause(1500);

        String filePath = Paths.get(System.getProperty("user.dir"), "src", "test", "resources", "features", "files", "blank.png").toString();

        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(new StringSelection(filePath), null);

        Robot robot = new Robot();

        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_A);
        robot.keyRelease(KeyEvent.VK_A);
        robot.keyRelease(KeyEvent.VK_CONTROL);

        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_CONTROL);

        pause(500);

        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);

        pause(1500);
    }

    @Then("User should see the uploaded image size")
    public void userShouldSeeTheUploadedImageSize() {
        Assert.assertTrue(settpage.uploadedImageSize.isDisplayed());
    }

    @And("User clicks the Upload button")
    public void userClicksTheUploadButton() {
        settpage.uploadButton.click();
    }

    @And("User clicks the Save button")
    public void userClicksTheSaveButton() {
        wait.until(ExpectedConditions.elementToBeClickable(settpage.saveButton));

        new Actions(getDriver())
                .moveToElement(settpage.saveButton)
                .click()
                .perform();
    }

    @Then("User should see {string} message")
    public void userShouldSeeMessage(String message) {
        WebElement toast = wait.until(ExpectedConditions.visibilityOf(settpage.saveConfirm));

        Assert.assertEquals(toast.getText().trim(), message);
    }

    @And("User closes the Profile Photo window")
    public void userClosesTheProfilePhotoWindow() {
        settpage.closeButton.click();
    }
}
