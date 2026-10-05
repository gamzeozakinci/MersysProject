package stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import pages.HeaderMenu;
import pages.MessagingPage;

import java.nio.file.Paths;
import java.time.Duration;

import static pages.ParentPage.click;
import static pages.ParentPage.isPresent;
import static utilities.GWD.getDriver;

public class US005_Messaging {

    HeaderMenu hm = new HeaderMenu(getDriver());
    MessagingPage mp = new MessagingPage(getDriver());
    WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
    JavascriptExecutor js = (JavascriptExecutor) getDriver();

    private String sentSubject;

    @When("User clicks on the New Message button")
    public void userClicksOnTheNewMessageButton() {
        click(hm.hamburgerButton, 10);
        click(hm.headerMessagingButton, 10);
        click(hm.headerNewMessageButton, 10);

        wait.until(ExpectedConditions.urlContains("/user-messages/new"));
    }

    @And("User closes the error message")
    public void userClosesTheErrorMessage() {
        for (WebElement closeButton : mp.toastCloseButtons) {
            try {
                closeButton.click();
            } catch (StaleElementReferenceException toastAlreadyGone) {
            }
        }

        try {
            new WebDriverWait(getDriver(), Duration.ofSeconds(3)).until(driver -> mp.toastBars.isEmpty());
        } catch (TimeoutException stillOnScreen) {
        }
    }

    @And("User clicks on the icon, searches for {string} and selects a receiver")
    public void userSearchesAndSelectsAReceiver(String searchTerm) {
        click(mp.receiverPickerButton, 10);

        wait.until(ExpectedConditions.visibilityOf(mp.receiverSearchBox));
        mp.receiverSearchBox.sendKeys(searchTerm, Keys.ENTER);

        wait.until(driver -> onlyShowsResultsFor(searchTerm));

        click(mp.receiverResults.get(0), 10);
        wait.until(driver -> !mp.receiverResultInputs.isEmpty() && mp.receiverResultInputs.get(0).isSelected());

        click(mp.addAndCloseButton, 10);

        wait.until(driver -> !mp.receiverChips.isEmpty());
    }

    private boolean onlyShowsResultsFor(String searchTerm) {
        try {
            if (mp.receiverRows.isEmpty()) {
                return false;
            }

            for (WebElement row : mp.receiverRows) {
                if (!row.getText().toLowerCase().contains(searchTerm.toLowerCase())) {
                    return false;
                }
            }

            return true;
        } catch (StaleElementReferenceException listRebuilt) {
            return false;
        }
    }

    @And("User enters {string} as the message subject")
    public void userEntersTheMessageSubject(String subject) {
        sentSubject = subject;

        wait.until(ExpectedConditions.visibilityOf(mp.subjectBox));
        mp.subjectBox.sendKeys(subject);
    }

    @And("User types {string} into the text editor")
    public void userTypesIntoTheTextEditor(String text) {
        wait.until(driver -> Boolean.TRUE.equals(js.executeScript(
                "return typeof tinymce !== 'undefined' && tinymce.activeEditor != null;")));

        js.executeScript("tinymce.activeEditor.setContent(arguments[0]);", text);
    }

    @And("User attaches a sample file from the {string} section")
    public void userAttachesASampleFile(String sectionName) {
        String filePath = Paths.get(System.getProperty("user.dir"),
                "src", "test", "resources", "features", "files", "blank.png").toString();

        wait.until(ExpectedConditions.elementToBeClickable(mp.attachFilesButton));
        js.executeScript("arguments[0].click();", mp.attachFilesButton);

        wait.until(driver -> isPresent(mp.fileInput));
        mp.fileInput.sendKeys(filePath);

        new Actions(getDriver()).sendKeys(Keys.ESCAPE).perform();

        wait.until(driver -> isPresent(mp.attachedFileName));
    }

    @And("User clicks the \"Send\" button")
    public void userClicksTheSendButton() {
        click(mp.sendButton, 10);
    }

    @Then("User navigates to the {string} page from the hamburger menu and verifies that the sent message is in the list")
    public void userVerifiesTheSentMessageIsListed(String mailbox) {
        click(hm.hamburgerButton, 10);
        click(hm.headerMessagingButton, 10);
        click(hm.headerOutboxButton, 10);

        mp.showMessagesFromAllDates();

        boolean listed = false;

        for (WebElement row : mp.messageRows) {
            if (row.getText().contains(sentSubject)) {
                listed = true;
            }
        }

        Assert.assertTrue(listed, "\"" + sentSubject + "\" is not listed in the " + mailbox + ".");
    }
}
