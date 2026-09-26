package stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import pages.HeaderMenu;
import utilities.GWD;

import java.time.Duration;

import static pages.ParentPage.click;

public class US004_HamMenuMessaging extends GWD {

    HeaderMenu header = new HeaderMenu(getDriver());
    WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));

    @And("User hovers over the \"Messaging\" page")
    public void hoverOverMessagingLink() {
        try {
            WebDriverWait shortWait = new WebDriverWait(getDriver(), Duration.ofSeconds(3));
            WebElement closeButton = shortWait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".svg-inline--fa.fa-xmark.fa-fw")));
            closeButton.click();
        } catch (TimeoutException | StaleElementReferenceException | ElementNotInteractableException e) {
            System.out.println("No error toast to close (" + e.getClass().getSimpleName() + ")");
        }

        Actions actions = new Actions(getDriver());
        wait.until(ExpectedConditions.elementToBeClickable(header.headerMessagingButton));
        ((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();", header.headerMessagingButton);
        actions.moveToElement(header.headerMessagingButton).perform();

    }

    @Then("User should see the \"New Message\" page")
    public void checkNewMessageLinkVisible() {
        wait.until(ExpectedConditions.elementToBeClickable(header.headerNewMessageButton));

    }

    @And("User should see the \"Inbox\" page")
    public void checkInboxLinkVisible() {
        wait.until(ExpectedConditions.elementToBeClickable(header.headerInboxButton));

    }

    @And("User should see the \"Outbox\" page")
    public void checkOutboxLinkVisible() {
        wait.until(ExpectedConditions.elementToBeClickable(header.headerOutboxButton));

    }

    @And("User should see the \"Trash\" page")
    public void checkTrashLinkVisible() {
        wait.until(ExpectedConditions.elementToBeClickable(header.headerTrashButton));

    }

    @When("User clicks the \"New Message\" page")
    public void clickNewMessageLink() {
        click(header.headerNewMessageButton, 10);

    }

    @Then("User should be navigated to the \"New Message\" page")
    public void checkNavigatedToNewMessagePage() {
        wait.until(ExpectedConditions.urlContains("new"));
        Assert.assertTrue(getDriver().getCurrentUrl().contains("new"));

    }

    @When("User clicks the \"Inbox\" page")
    public void clickInboxLink() {
        wait.until(ExpectedConditions.elementToBeClickable(header.headerInboxButton));
        ((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();", header.headerInboxButton);

    }

    @Then("User should be navigated to the \"Inbox\" page")
    public void checkNavigatedToInboxPage() {
        wait.until(ExpectedConditions.urlContains("inbox"));
        Assert.assertTrue(getDriver().getCurrentUrl().contains("inbox"));

    }

    @When("User clicks the \"Outbox\" page")
    public void clickOutboxLink() {
        wait.until(ExpectedConditions.elementToBeClickable(header.headerOutboxButton));
        ((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();", header.headerOutboxButton);

    }

    @Then("User should be navigated to the \"Outbox\" page")
    public void checkNavigatedToOutboxPage() {
        wait.until(ExpectedConditions.urlContains("outbox"));
        Assert.assertTrue(getDriver().getCurrentUrl().contains("outbox"));

    }

    @When("User clicks the \"Trash\" page")
    public void clickTrashLink() {
        wait.until(ExpectedConditions.elementToBeClickable(header.headerTrashButton));
        ((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();", header.headerTrashButton);

    }

    @Then("User should be navigated to the \"Trash\" page")
    public void checkNavigatedToTrashPage() {
        wait.until(ExpectedConditions.urlContains("trash"));
        Assert.assertTrue(getDriver().getCurrentUrl().contains("trash"));

    }

}
