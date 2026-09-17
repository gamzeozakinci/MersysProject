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
import utilities.GWD;
import java.time.Duration;
import static utilities.GWD.getDriver;

public class DeleteMessageSteps {

    HeaderMenu hm = new HeaderMenu(GWD.getDriver());
    MessagingPage mp = new MessagingPage(GWD.getDriver());
    WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));

    @Given("User clicks on the \"Outbox\" button")
    public void userClicksOnTheOutboxButton(){

        ParentPage.click(hm.hamburgerButton, 10);
        ParentPage.hover(mp.messagingLink);
        ParentPage.click(mp.outboxLink,10);

    }

    @And("User selects a sent message")
    public void userSelectsASentMessage() {
        try { Thread.sleep(2000); } catch (InterruptedException e) {}
        int allMessages = mp.allMessages.size();

        if(allMessages  > 0) {
            int Index = (int) (Math.random() * allMessages );
            WebElement targetMessage = mp.allMessages.get(Index);

            JavascriptExecutor js = (JavascriptExecutor) GWD.getDriver();

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
        wait.until(ExpectedConditions.visibilityOf(mp.messageDeleteledConfirmation));

        Assert.assertTrue(mp.messageDeleteledConfirmation.isDisplayed(), "No message shown");
    }
}
