package stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
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
import pages.ParentPage;

import java.nio.file.Paths;
import java.time.Duration;

import static pages.ParentPage.click;
import static utilities.GWD.getDriver;

public class US005_Messaging {

    HeaderMenu hm = new HeaderMenu(getDriver());
    MessagingPage mp = new MessagingPage(getDriver());
    WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));

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
        dismissErrorToasts();
    }

    private void dismissErrorToasts() {
        for (WebElement closeButton : mp.toastCloseButtons) {
            try {
                closeButton.click();
            } catch (StaleElementReferenceException toastAlreadyGone) {
            }
        }

        try {
            new WebDriverWait(getDriver(), Duration.ofSeconds(3)).until(
                    ExpectedConditions.numberOfElementsToBe(By.cssSelector(".hot-toast-bar-base-wrapper"), 0));
        } catch (TimeoutException stillOnScreen) {
        }
    }

    private void clickPastToasts(WebElement element) {
        for (int attempt = 0; attempt < 3; attempt++) {
            dismissErrorToasts();
            try {
                click(element, 10);
                return;
            } catch (ElementClickInterceptedException toastInTheWay) {
                if (attempt == 2) throw toastInTheWay;
            }
        }
    }

    @And("User clicks on the icon, searches for {string} and selects a receiver")
    public void userSearchesAndSelectsAReceiver(String searchTerm) {
        click(mp.receiverPickerButton, 10);

        wait.until(ExpectedConditions.visibilityOf(mp.receiverSearchBox));
        mp.receiverSearchBox.sendKeys(searchTerm, Keys.ENTER);

        wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(
                By.cssSelector(MessagingPage.RECEIVER_CHECKBOX_CSS), 0));

        WebElement firstResult = mp.receiverResults.get(0);
        click(firstResult, 10);
        wait.until(driver -> firstResult.findElement(By.tagName("input")).isSelected());

        click(mp.addAndCloseButton, 10);

        wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(By.cssSelector("mat-chip-row"), 0));
        Assert.assertFalse(mp.receiverChips.isEmpty(), "No receiver was added to the message.");
    }

    @And("User enters {string} as the message subject")
    public void userEntersTheMessageSubject(String subject) {
        sentSubject = subject;

        wait.until(ExpectedConditions.visibilityOf(mp.subjectBox));
        mp.subjectBox.sendKeys(subject);
    }

    @And("User types {string} into the text editor")
    public void userTypesIntoTheTextEditor(String text) {
        JavascriptExecutor js = (JavascriptExecutor) getDriver();

        wait.until(driver -> Boolean.TRUE.equals(js.executeScript(
                "return typeof tinymce !== 'undefined' && tinymce.activeEditor != null;")));

        js.executeScript("tinymce.activeEditor.setContent(arguments[0]);", text);
    }

    @And("User attaches a sample file from the {string} section")
    public void userAttachesASampleFile(String sectionName) {
        String filePath = Paths.get(System.getProperty("user.dir"),
                "src", "test", "resources", "features", "files", "blank.png").toString();

        clickPastToasts(mp.attachFilesButton);

        WebElement fileInput = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.cssSelector("input[type='file']")));
        fileInput.sendKeys(filePath);

        new Actions(getDriver()).sendKeys(Keys.ESCAPE).perform();

        wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//*[contains(text(),'blank.png')]")));
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

        boolean listed = mp.messageRows.stream()
                .anyMatch(row -> row.getText().contains(sentSubject));

        Assert.assertTrue(listed, "\"" + sentSubject + "\" is not listed in the " + mailbox + ".");
    }
}
