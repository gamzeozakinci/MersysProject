package stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.HeaderMenu;
import pages.MessagingPage;
import utilities.GWD;

import java.time.Duration;

import static pages.ParentPage.click;

public class US007_HamMenuMessaging extends GWD {

    HeaderMenu header = new HeaderMenu(getDriver());
    MessagingPage messaging = new MessagingPage(getDriver());
    WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));

    @And("Sets the dates to see messages.")
    public void setDateOnTrash() {
        Actions actions = new Actions(getDriver());
        actions.click(messaging.date).click(messaging.date).click(messaging.date).perform();
        messaging.date.sendKeys("01012025");

        actions.click(messaging.date2).click(messaging.date2).click(messaging.date2).perform();
        messaging.date2.sendKeys("01012027");

        click(messaging.search, 4);

    }


    @And("User clicks 'Messaging' and then 'Trash'.")
    public void clicksMessagingThenTrash() {
        click(header.headerMessagingButton, 5);
        click(header.headerTrashButton, 5);

    }

    @Then("User should see the list of deleted messages.")
    public void checkDeletedMessagesListVisible() {
        wait.until(ExpectedConditions.visibilityOf(messaging.trash));

    }

    @And("User should see a 'Restore' icon on a deleted message.")
    public void checkRestoreIconVisible() {
        wait.until(ExpectedConditions.visibilityOf(messaging.restoreButton));

    }

    @When("User clicks the 'Restore' icon on a message.")
    public void clickRestoreIcon() {
        click(messaging.restoreButton, 4);

    }

    @Then("User should see a 'Success' message confirming the message was restored.")
    public void checkRestoreSuccessMessage() {
        wait.until(ExpectedConditions.visibilityOf(messaging.successMessage));

    }

    @And("User should see a 'Delete' icon on a deleted message.")
    public void checkDeleteIconVisible() {
        wait.until(ExpectedConditions.visibilityOf(messaging.deleteButton));

    }

    @When("User clicks the 'Delete' icon on a message.")
    public void clickDeleteIcon() {
        for (int i = 0; i < 3; i++) {
            try {
                click(messaging.deleteButton, 4);
                return;
            } catch (StaleElementReferenceException e) {
                if (i == 2) throw e;
            }
        }

    }

    @Then("A confirmation pop-up should open before permanent deletion.")
    public void checkPermanentDeleteConfirmationOpen() {
        wait.until(ExpectedConditions.visibilityOf(messaging.confirmationDialog));

    }

    @When("User confirms the permanent deletion.")
    public void confirmPermanentDeletion() {
        click(messaging.dialogAnswer, 4);

    }

    @Then("User should see a 'Success' message confirming the message was permanently deleted.")
    public void checkPermanentDeleteSuccessMessage() {
        wait.until(ExpectedConditions.visibilityOf(messaging.successMessage));

    }

}
