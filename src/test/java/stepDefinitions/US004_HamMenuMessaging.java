package stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import pages.HeaderMenu;
import pages.MessagingPage;
import utilities.GWD;

import java.time.Duration;

import static pages.ParentPage.click;

public class US004_HamMenuMessaging extends GWD {

    HeaderMenu header = new HeaderMenu(getDriver());
    MessagingPage messaging = new MessagingPage(getDriver());
    WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));

    @And("User hovers over the 'Messaging' page.")
    public void hoverOverMessagingLink() {
        try {
            WebDriverWait shortWait = new WebDriverWait(getDriver(), Duration.ofSeconds(3));
            WebElement closeButton = shortWait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".svg-inline--fa.fa-xmark.fa-fw")));
            closeButton.click();
        } catch (Exception e) {
        }

        Actions actions = new Actions(getDriver());
        wait.until(ExpectedConditions.elementToBeClickable(header.headerMessagingButton));
        ((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();", header.headerMessagingButton);
        actions.moveToElement(header.headerMessagingButton).perform();

    }

    @Then("User should see the 'New Message' page.")
    public void checkNewMessageLinkVisible() {
        wait.until(ExpectedConditions.elementToBeClickable(messaging.newMessageButton));

    }

    @And("User should see the 'Inbox' page.")
    public void checkInboxLinkVisible() {
        wait.until(ExpectedConditions.elementToBeClickable(messaging.inboxButton));

    }

    @And("User should see the 'Outbox' page.")
    public void checkOutboxLinkVisible() {
        wait.until(ExpectedConditions.elementToBeClickable(messaging.outboxLink));

    }

    @And("User should see the 'Trash' page.")
    public void checkTrashLinkVisible() {
        wait.until(ExpectedConditions.elementToBeClickable(messaging.outboxLink));

    }

    @When("User clicks the 'New Message' page.")
    public void clickNewMessageLink() {
        click(messaging.newMessageButton, 10);

    }

    @Then("User should be navigated to the 'New Message' page.")
    public void checkNavigatedToNewMessagePage() {
        wait.until(ExpectedConditions.urlContains("new"));
        Assert.assertTrue(getDriver().getCurrentUrl().contains("new"));

    }

    @When("User clicks the 'Inbox' page.")
    public void clickInboxLink() {
        wait.until(ExpectedConditions.elementToBeClickable(messaging.inboxButton));
        ((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();", messaging.inboxButton);

    }

    @Then("User should be navigated to the 'Inbox' page.")
    public void checkNavigatedToInboxPage() {
        wait.until(ExpectedConditions.urlContains("inbox"));
        Assert.assertTrue(getDriver().getCurrentUrl().contains("inbox"));

    }

    @When("User clicks the 'Outbox' page.")
    public void clickOutboxLink() {
        wait.until(ExpectedConditions.elementToBeClickable(messaging.outboxLink));
        ((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();", messaging.outboxLink);

    }

    @Then("User should be navigated to the 'Outbox' page.")
    public void checkNavigatedToOutboxPage() {
        wait.until(ExpectedConditions.urlContains("outbox"));
        Assert.assertTrue(getDriver().getCurrentUrl().contains("outbox"));

    }

    @When("User clicks the 'Trash' page.")
    public void clickTrashLink() {
        wait.until(ExpectedConditions.elementToBeClickable(messaging.moveToTrashButton));
        ((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();", messaging.moveToTrashButton);

    }

    @Then("User should be navigated to the 'Trash' page.")
    public void checkNavigatedToTrashPage() {
        wait.until(ExpectedConditions.urlContains("trash"));
        Assert.assertTrue(getDriver().getCurrentUrl().contains("trash"));

    }

}
