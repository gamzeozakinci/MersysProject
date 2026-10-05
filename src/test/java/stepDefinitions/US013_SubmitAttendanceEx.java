package stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import pages.AttendancePage;
import pages.HeaderMenu;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.nio.file.Paths;
import java.time.Duration;

import static pages.ParentPage.click;
import static pages.ParentPage.pause;
import static utilities.GWD.getDriver;

public class US013_SubmitAttendanceEx {

    HeaderMenu hm = new HeaderMenu(getDriver());
    AttendancePage attPage = new AttendancePage(getDriver());
    WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));

    @When("User clicks \"Attendance\" mainpage header")
    public void userClicksAttendance() {
        click(hm.headerAttendanceButton, 3);
    }

    @And("User opens \"Attendance Excuses\" screen and clicks \"Add Excuse\"")
    public void attendanceExcuses() {
        click(attPage.attendanceExcuses, 3);
        click(attPage.addAttendance, 3);
    }

    @Then("User adds a description for the excuse")
    public void addDescriptionExcuse() {
        attPage.enterDescription.sendKeys("Sick day.");
    }

    @And("User adds a file to support the excuse and clicks \"send\"")
    public void fileToSupportExcuse() throws AWTException {
        String filePath = Paths.get(System.getProperty("user.dir"), "src", "test", "resources", "features", "files", "EXCUSE_FILE.pdf").toString();

        attPage.attachFilesButton.click();
        wait.until(ExpectedConditions.elementToBeClickable(attPage.fromLocalOption));
        attPage.fromLocalOption.click();

        pause(1500);
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(new StringSelection(filePath), null);

        Robot robot = new Robot();
        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);

        wait.until(ExpectedConditions.visibilityOf(attPage.attachmentConfirmation));
        Assert.assertTrue(attPage.attachmentConfirmation.isDisplayed(), "File was not attached to the excuse.");

        wait.until(ExpectedConditions.elementToBeClickable(attPage.send));
        attPage.send.click();
    }

    @And("User must be able to see the confirm message")
    public void confirmExcuse() {
        wait.until(ExpectedConditions.elementToBeClickable(attPage.confirmExcuse));
        Assert.assertTrue(attPage.confirmExcuse.isDisplayed());
    }
}
