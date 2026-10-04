package stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import pages.HeaderMenu;
import pages.MessagingPage;

import java.time.Duration;

import static pages.ParentPage.click;
import static pages.ParentPage.hover;
import static pages.ParentPage.pause;
import static utilities.GWD.getDriver;

public class US006_DeleteMessage {

    HeaderMenu hm = new HeaderMenu(getDriver());
    MessagingPage mp = new MessagingPage(getDriver());
    WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));

    @Given("User clicks on the \"Outbox\" button")
    public void userClicksOnTheOutboxButton() {
        click(hm.hamburgerButton, 10);
        hover(hm.headerMessagingButton);
        click(hm.headerOutboxButton, 10);
    }

    @And("User selects a sent message")
    public void userSelectsASentMessage() {
        waitForListToSettle();

        int index = (int) (Math.random() * mp.allMessages.size());

        for (int attempt = 0; attempt < 5; attempt++) {
            click(mp.allMessages.get(index), 10);

            try {
                new WebDriverWait(getDriver(), Duration.ofSeconds(5))
                        .until(ExpectedConditions.elementToBeClickable(mp.moveToTrashButton));
                return;
            } catch (TimeoutException selectionDidNotRegister) {
            }
        }

        Assert.fail("Selecting a message never enabled the Move To Trash button.");
    }

    private void waitForListToSettle() {
        new WebDriverWait(getDriver(), Duration.ofSeconds(15)).until(driver -> {
            int before = mp.messageRows.size();
            pause(500);
            return before > 0 && before == mp.messageRows.size();
        });
    }

    @When("User clicks on the Move to Trash icon for a sent message")
    public void userClicksOnTheMoveToTrashIconForASentMessage() {
        for (int attempt = 0; attempt < 3; attempt++) {
            click(mp.moveToTrashButton, 10);

            try {
                new WebDriverWait(getDriver(), Duration.ofSeconds(3))
                        .until(ExpectedConditions.visibilityOf(mp.confirmationDialog));
                click(mp.confirmMessageDeleteButton, 10);
                return;
            } catch (TimeoutException dialogDidNotOpen) {
            }
        }

        Assert.fail("The move-to-trash confirmation dialog did not open.");
    }

    @Then("User should see a deletion confirmation pop-up on the screen")
    public void userShouldSeeADeletionConfirmationPopUpOnTheScreen() {
        wait.until(ExpectedConditions.visibilityOf(mp.messageDeletedConfirmation));

        Assert.assertTrue(mp.messageDeletedConfirmation.isDisplayed(), "No message shown");
    }

    @Then("User should see a {string} message on the screen")
    public void userShouldSeeAMessageOnTheScreen(String messageType) {
        wait.until(ExpectedConditions.visibilityOf(mp.successMessage));

        Assert.assertTrue(mp.successMessage.isDisplayed(),
                "No " + messageType + " toast appeared on the screen.");
    }
}
