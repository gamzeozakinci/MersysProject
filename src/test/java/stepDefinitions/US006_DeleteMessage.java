package stepDefinitions;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import pages.HeaderMenu;
import pages.MessagingPage;
import pages.ParentPage;
import java.time.Duration;

import static utilities.GWD.getDriver;

public class US006_DeleteMessage {

    HeaderMenu hm = new HeaderMenu(getDriver());
    MessagingPage mp = new MessagingPage(getDriver());
    WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));

    @Given("User clicks on the \"Outbox\" button")
    public void userClicksOnTheOutboxButton(){

        ParentPage.click(hm.hamburgerButton, 10);
        ParentPage.hover(hm.headerMessagingButton);
        ParentPage.click(hm.headerOutboxButton,10);

    }

    @And("User selects a sent message")
    public void userSelectsASentMessage() {
        ParentPage.pause(2000);
        int allMessages = mp.allMessages.size();

        if(allMessages  > 0) {
            int Index = (int) (Math.random() * allMessages );
            WebElement targetMessage = mp.allMessages.get(Index);

            JavascriptExecutor js = (JavascriptExecutor) getDriver();

            js.executeScript("arguments[0].scrollIntoView({behavior: 'instant', block: 'center', inline: 'nearest'});", targetMessage);

                js.executeScript("arguments[0].click();", targetMessage);

            System.out.println("Selected message: " + Index);
        } else {
            System.out.println("Error: No messages on the list!");
        }
    }

    @When("User clicks on the Move to Trash icon for a sent message")
    public void userClicksOnTheMoveToTrashIconForASentMessage() {
        ParentPage.click(mp.moveToTrashButton,10);
        ParentPage.click(mp.confirmMessageDeleteButton,10);
    }

    @Then("User should see a deletion confirmation pop-up on the screen")
    public void userShouldSeeADeletionConfirmationPopUpOnTheScreen() {
        wait.until(ExpectedConditions.visibilityOf(mp.messageDeletedConfirmation));

        Assert.assertTrue(mp.messageDeletedConfirmation.isDisplayed(), "No message shown");
    }
}
