package stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import pages.SettingsPage;
import utilities.GWD;
import io.cucumber.java.en.When;
import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.time.Duration;


public class US014_ProfilePicture extends GWD {

    SettingsPage settpage = new SettingsPage(getDriver());

    @And("User clicks on the profile picture")
    public void userClicksOnTheProfilePicture() {
        settpage.profilePicture.click();
    }

    @Then("Profile Photo window should be displayed")
    public void profilePhotoWindowShouldBeDisplayed() {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));

        wait.until(ExpectedConditions.visibilityOf(settpage.profilePhotoWindowTitle));

        Assert.assertTrue(settpage.profilePhotoWindowTitle.isDisplayed());
    }

    @When("User selects a profile picture")
    public void userSelectsAProfilePicture() throws Exception {

        settpage.fileSelectButton.click();

        Thread.sleep(1500);

        String filePath = System.getProperty("user.dir")
                + "\\src\\test\\resources\\features\\files\\blank.png";

        Toolkit.getDefaultToolkit().getSystemClipboard()
                .setContents(new StringSelection(filePath), null);

        Robot robot = new Robot();

        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_A);
        robot.keyRelease(KeyEvent.VK_A);
        robot.keyRelease(KeyEvent.VK_CONTROL);

        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_CONTROL);

        Thread.sleep(500);

        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);

        Thread.sleep(1500);
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
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));

        wait.until(ExpectedConditions.elementToBeClickable(settpage.saveButton));

        new Actions(getDriver())
                .moveToElement(settpage.saveButton)
                .click()
                .perform();


    }

    @Then("User should see {string} message")
    public void userShouldSeeMessage(String message) {

        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));

        WebElement toast = wait.until(
                ExpectedConditions.visibilityOf(settpage.saveConfirm)
        );

        Assert.assertEquals(toast.getText().trim(), message);

    }

    @And("User closes the Profile Photo window")
    public void userClosesTheProfilePhotoWindow() {
        settpage.closeButton.click();
    }

}


