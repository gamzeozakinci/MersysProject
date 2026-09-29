package stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import pages.HeaderMenu;
import pages.MessagingPage;

import java.time.Duration;

import static pages.ParentPage.click;
import static utilities.GWD.getDriver;

public class US007_HamMenuMessaging {

    HeaderMenu header = new HeaderMenu(getDriver());
    MessagingPage messaging = new MessagingPage(getDriver());
    WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));

    @And("User sets the dates to see messages")
    public void setDateOnTrash() {
        messaging.showMessagesFromAllDates();
    }


    @And("User clicks \"Messaging\" and then \"Trash\"")
    public void clicksMessagingThenTrash() {
        click(header.headerMessagingButton, 5);
        click(header.headerTrashButton, 5);

    }

    @Then("User should see the list of deleted messages")
    public void checkDeletedMessagesListVisible() {
        wait.until(ExpectedConditions.visibilityOf(messaging.trash));

    }

    @And("User should see a \"Restore\" icon on a deleted message")
    public void checkRestoreIconVisible() {
        wait.until(ExpectedConditions.visibilityOf(messaging.restoreButton));

    }

    @When("User clicks the \"Restore\" icon on a message")
    public void clickRestoreIcon() {
        for (int attempt = 0; attempt < 3; attempt++) {
            try {
                click(messaging.restoreButton, 10);
                return;
            } catch (StaleElementReferenceException | TimeoutException retry) {
                if (attempt == 2) {
                    Assert.fail("The Restore icon never became clickable.");
                }
            }
        }

    }

    @Then("User should see a \"Success\" message confirming the message was restored")
    public void checkRestoreSuccessMessage() {
        wait.until(ExpectedConditions.visibilityOf(messaging.successMessage));

    }

    @And("User should see a \"Delete\" icon on a deleted message")
    public void checkDeleteIconVisible() {
        wait.until(ExpectedConditions.visibilityOf(messaging.deleteButton));

    }

    @When("User clicks the \"Delete\" icon on a message")
    public void clickDeleteIcon() {
        for (int attempt = 0; attempt < 3; attempt++) {
            try {
                click(messaging.deleteButton, 4);

                new WebDriverWait(getDriver(), Duration.ofSeconds(3))
                        .until(ExpectedConditions.visibilityOf(messaging.confirmationDialog));
                return;
            } catch (StaleElementReferenceException | TimeoutException retry) {
                if (attempt == 2) {
                    Assert.fail("The permanent delete confirmation dialog did not open.");
                }
            }
        }

    }

    @Then("A confirmation pop-up should open before permanent deletion")
    public void checkPermanentDeleteConfirmationOpen() {
        wait.until(ExpectedConditions.visibilityOf(messaging.confirmationDialog));

    }

    @When("User confirms the permanent deletion")
    public void confirmPermanentDeletion() {
        click(messaging.dialogAnswer, 4);

    }

    @Then("User should see a \"Success\" message confirming the message was permanently deleted")
    public void checkPermanentDeleteSuccessMessage() {
        wait.until(ExpectedConditions.visibilityOf(messaging.successMessage));

    }

}
