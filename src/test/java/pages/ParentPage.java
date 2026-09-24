package pages;

import utilities.GWD;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ParentPage {

    public static void click(WebElement element, int timeout) {
        WebDriverWait wait = new WebDriverWait(GWD.getDriver(), Duration.ofSeconds(timeout));
        wait.until(ExpectedConditions.elementToBeClickable(element)).click();
    }

    public static void hover(WebElement element) {
        Actions actions = new Actions(GWD.getDriver());
        actions.moveToElement(element).perform();
    }

    public static void scrollToElement(WebElement element) {
        ((JavascriptExecutor) GWD.getDriver()).executeScript("arguments[0].scrollIntoView(true);", element);
    }

    /** Fixed pause, only for steps where there is no page state to wait for (native dialogs). */
    public static void pause(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public static void mySendKeys(WebElement element, String text) {
        new WebDriverWait(GWD.getDriver(), Duration.ofSeconds(20)).until(ExpectedConditions.visibilityOf(element));
        scrollToElement(element);
        element.sendKeys(text);
    }
}


