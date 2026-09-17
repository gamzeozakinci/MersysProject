package stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import pages.GradingPage;
import pages.HeaderMenu;
import utilities.GWD;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.time.Duration;

import static pages.ParentPage.click;


public class US017_GradingFeature extends GWD {

    HeaderMenu header = new HeaderMenu(getDriver());
    GradingPage grading = new GradingPage(getDriver());


    @When("User navigates to the 'Grading' page.")
    public void navigateToGradingPage() {
        click(header.headerGradingButton, 4);

    }

    @Then("User should see a 'Print' icon on the page.")
    public void checkPrintIconVisible() {
        Assert.assertTrue(grading.printButton.isDisplayed());

    }

    @When("User clicks the 'Print' icon.")
    public void clickPrintIcon() {
        click(grading.printButton, 4);

    }

    @Then("User should see the transcript document in PDF format.")
    public void checkTranscriptPdfVisible() {
        String originalWindow = getDriver().getWindowHandle();
        new WebDriverWait(getDriver(), Duration.ofSeconds(10))
                .until(d -> d.getWindowHandles().size() > 1);

        for (String handle : getDriver().getWindowHandles()) {
            if (!handle.equals(originalWindow)) {
                getDriver().switchTo().window(handle);
                break;
            }
        }

    }

    @And("User must be able click and download the document")
    public void checkDownloadDocument() throws InterruptedException, AWTException {
        File downloadDir = new File(System.getProperty("user.dir") + "\\src\\test\\resources\\features\\files");
        File[] beforeFiles = downloadDir.listFiles((dir, name) -> name.toLowerCase().endsWith(".pdf"));
        int countBefore = beforeFiles == null ? 0 : beforeFiles.length;

        for (int i = 0; i < 22; i++) {
            Thread.sleep(200);
            new Actions(getDriver()).sendKeys(Keys.TAB).perform();
        }
        new Actions(getDriver()).sendKeys(Keys.ENTER).perform();

        Thread.sleep(1500);
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(new StringSelection(downloadDir.getAbsolutePath()), null);

        Robot robot = new Robot();

        //Ctrl+L
        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_L);
        robot.keyRelease(KeyEvent.VK_L);
        robot.keyRelease(KeyEvent.VK_CONTROL);
        Thread.sleep(300);

        //Ctrl+V
        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_CONTROL);
        Thread.sleep(300);

        //enter
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
        Thread.sleep(500);

        //Alt+N
        robot.keyPress(KeyEvent.VK_ALT);
        robot.keyPress(KeyEvent.VK_N);
        robot.keyRelease(KeyEvent.VK_N);
        robot.keyRelease(KeyEvent.VK_ALT);
        Thread.sleep(300);

        //enter
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
        Thread.sleep(500);

        boolean fileCreated = false;
        long endTime = System.currentTimeMillis() + 30000;
        while (System.currentTimeMillis() < endTime) {
            File[] currentFiles = downloadDir.listFiles((dir, name) -> name.toLowerCase().endsWith(".pdf"));
            if (currentFiles != null && currentFiles.length > countBefore) {
                fileCreated = true;
                break;
            }
            Thread.sleep(500);
        }

        Assert.assertTrue(fileCreated, "PDF file was not created in " + downloadDir.getAbsolutePath());

    }
}

