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

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.time.Duration;

import static pages.ParentPage.click;
import static pages.ParentPage.pause;
import static utilities.GWD.getDriver;

public class US017_Grading {

    HeaderMenu hm = new HeaderMenu(getDriver());
    GradingPage grading = new GradingPage(getDriver());

    @When("User opens the \"Grading\" page")
    public void navigateToGradingPage() {
        click(hm.headerGradingButton, 4);
    }

    @Then("User should see a \"Print\" icon on the page")
    public void checkPrintIconVisible() {
        Assert.assertTrue(grading.printButton.isDisplayed());
    }

    @When("User clicks the \"Print\" icon")
    public void clickPrintIcon() {
        click(grading.printButton, 4);
    }

    @Then("User should see the transcript document in PDF format")
    public void checkTranscriptPdfVisible() {
        String originalWindow = getDriver().getWindowHandle();
        new WebDriverWait(getDriver(), Duration.ofSeconds(10))
                .until(driver -> driver.getWindowHandles().size() > 1);

        for (String handle : getDriver().getWindowHandles()) {
            if (!handle.equals(originalWindow)) {
                getDriver().switchTo().window(handle);
                break;
            }
        }
    }

    @And("User must be able to click and download the document")
    public void checkDownloadDocument() throws AWTException {
        File downloadDir = new File(System.getProperty("user.dir"), "target" + File.separator + "downloads");
        downloadDir.mkdirs();
        int pdfsBefore = countPdfs(downloadDir);

        for (int i = 0; i < 22; i++) {
            pause(200);
            new Actions(getDriver()).sendKeys(Keys.TAB).perform();
        }
        new Actions(getDriver()).sendKeys(Keys.ENTER).perform();

        pause(1500);
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(new StringSelection(downloadDir.getAbsolutePath()), null);

        Robot robot = new Robot();

        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_L);
        robot.keyRelease(KeyEvent.VK_L);
        robot.keyRelease(KeyEvent.VK_CONTROL);
        pause(300);

        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_CONTROL);
        pause(300);

        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
        pause(500);

        robot.keyPress(KeyEvent.VK_ALT);
        robot.keyPress(KeyEvent.VK_N);
        robot.keyRelease(KeyEvent.VK_N);
        robot.keyRelease(KeyEvent.VK_ALT);
        pause(300);

        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
        pause(500);

        new WebDriverWait(getDriver(), Duration.ofSeconds(30))
                .withMessage("PDF file was not created in " + downloadDir.getAbsolutePath())
                .until(driver -> countPdfs(downloadDir) > pdfsBefore);
    }

    private int countPdfs(File folder) {
        int count = 0;
        File[] files = folder.listFiles();

        if (files != null) {
            for (File file : files) {
                if (file.getName().toLowerCase().endsWith(".pdf")) {
                    count++;
                }
            }
        }

        return count;
    }
}
