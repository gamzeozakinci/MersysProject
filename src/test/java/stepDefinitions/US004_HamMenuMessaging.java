package stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.HeaderMenu;
import pages.MessagingPage;

import java.time.Duration;

import static pages.ParentPage.click;
import static pages.ParentPage.hover;
import static pages.ParentPage.isPresent;
import static utilities.GWD.getDriver;

public class US004_HamMenuMessaging {

    HeaderMenu hm = new HeaderMenu(getDriver());
    MessagingPage mp = new MessagingPage(getDriver());
    WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));

    @And("User hovers over the \"Messaging\" page")
    public void hoverOverMessagingLink() {
        closeErrorToast();
        wait.until(ExpectedConditions.elementToBeClickable(hm.headerMessagingButton));
        ((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();", hm.headerMessagingButton);
        hover(hm.headerMessagingButton);
    }

    private void closeErrorToast() {
        try {
            new WebDriverWait(getDriver(), Duration.ofSeconds(3))
                    .until(driver -> isPresent(mp.errorToastCloseIcon));
            mp.errorToastCloseIcon.click();
        } catch (TimeoutException | StaleElementReferenceException | ElementNotInteractableException e) {
            System.out.println("No error toast to close");
        }
    }

    @Then("User should see the \"New Message\" page")
    public void checkNewMessageLinkVisible() {
        wait.until(ExpectedConditions.elementToBeClickable(hm.headerNewMessageButton));
    }

    @And("User should see the \"Inbox\" page")
    public void checkInboxLinkVisible() {
        wait.until(ExpectedConditions.elementToBeClickable(hm.headerInboxButton));
    }

    @And("User should see the \"Outbox\" page")
    public void checkOutboxLinkVisible() {
        wait.until(ExpectedConditions.elementToBeClickable(hm.headerOutboxButton));
    }

    @And("User should see the \"Trash\" page")
    public void checkTrashLinkVisible() {
        wait.until(ExpectedConditions.elementToBeClickable(hm.headerTrashButton));
    }

    @When("User clicks the \"New Message\" page")
    public void clickNewMessageLink() {
        click(hm.headerNewMessageButton, 10);
    }

    @When("User clicks the \"Inbox\" page")
    public void clickInboxLink() {
        click(hm.headerInboxButton, 10);
    }

    @When("User clicks the \"Outbox\" page")
    public void clickOutboxLink() {
        click(hm.headerOutboxButton, 10);
    }

    @When("User clicks the \"Trash\" page")
    public void clickTrashLink() {
        click(hm.headerTrashButton, 10);
    }

    @Then("User should be navigated to the \"New Message\" page")
    public void checkNavigatedToNewMessagePage() {
        wait.until(ExpectedConditions.urlContains("new"));
    }

    @Then("User should be navigated to the \"Inbox\" page")
    public void checkNavigatedToInboxPage() {
        wait.until(ExpectedConditions.urlContains("inbox"));
    }

    @Then("User should be navigated to the \"Outbox\" page")
    public void checkNavigatedToOutboxPage() {
        wait.until(ExpectedConditions.urlContains("outbox"));
    }

    @Then("User should be navigated to the \"Trash\" page")
    public void checkNavigatedToTrashPage() {
        wait.until(ExpectedConditions.urlContains("trash"));
    }
}
