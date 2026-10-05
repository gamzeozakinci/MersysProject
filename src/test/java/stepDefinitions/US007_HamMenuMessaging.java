package stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import pages.HeaderMenu;
import pages.MessagingPage;

import java.time.Duration;

import static pages.ParentPage.click;
import static utilities.GWD.getDriver;

public class US007_HamMenuMessaging {

    HeaderMenu hm = new HeaderMenu(getDriver());
    MessagingPage mp = new MessagingPage(getDriver());
    WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));

    @And("User sets the dates to see messages")
    public void setDateOnTrash() {
        mp.showMessagesFromAllDates();
    }

    @And("User clicks \"Messaging\" and then \"Trash\"")
    public void clicksMessagingThenTrash() {
        click(hm.headerMessagingButton, 5);
        click(hm.headerTrashButton, 5);
    }

    @Then("User should see the list of deleted messages")
    public void checkDeletedMessagesListVisible() {
        wait.until(ExpectedConditions.visibilityOf(mp.trash));
    }

    @And("User should see a \"Restore\" icon on a deleted message")
    public void checkRestoreIconVisible() {
        wait.until(ExpectedConditions.visibilityOf(mp.restoreButton));
    }

    @When("User clicks the \"Restore\" icon on a message")
    public void clickRestoreIcon() {
        for (int attempt = 0; attempt < 3; attempt++) {
            try {
                click(mp.restoreButton, 10);
                return;
            } catch (StaleElementReferenceException | TimeoutException retry) {
            }
        }

        Assert.fail("The Restore icon never became clickable.");
    }

    @Then("User should see a \"Success\" message confirming the message was restored")
    public void checkRestoreSuccessMessage() {
        wait.until(ExpectedConditions.visibilityOf(mp.successMessage));
    }

    @And("User should see a \"Delete\" icon on a deleted message")
    public void checkDeleteIconVisible() {
        wait.until(ExpectedConditions.visibilityOf(mp.deleteButton));
    }

    @When("User clicks the \"Delete\" icon on a message")
    public void clickDeleteIcon() {
        for (int attempt = 0; attempt < 3; attempt++) {
            try {
                click(mp.deleteButton, 4);
                new WebDriverWait(getDriver(), Duration.ofSeconds(3))
                        .until(ExpectedConditions.visibilityOf(mp.confirmationDialog));
                return;
            } catch (StaleElementReferenceException | TimeoutException retry) {
            }
        }

        Assert.fail("The permanent delete confirmation dialog did not open.");
    }

    @Then("A confirmation pop-up should open before permanent deletion")
    public void checkPermanentDeleteConfirmationOpen() {
        wait.until(ExpectedConditions.visibilityOf(mp.confirmationDialog));
    }

    @When("User confirms the permanent deletion")
    public void confirmPermanentDeletion() {
        click(mp.dialogAnswer, 4);
    }

    @Then("User should see a \"Success\" message confirming the message was permanently deleted")
    public void checkPermanentDeleteSuccessMessage() {
        wait.until(ExpectedConditions.visibilityOf(mp.successMessage));
    }
}
