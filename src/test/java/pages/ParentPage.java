package pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static utilities.GWD.getDriver;

public abstract class ParentPage {

    protected ParentPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public static void click(WebElement element, int timeout) {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(timeout));
        wait.until(ExpectedConditions.elementToBeClickable(element)).click();
    }

    public static void hover(WebElement element) {
        Actions actions = new Actions(getDriver());
        actions.moveToElement(element).perform();
    }

    public static void scrollToElement(WebElement element) {
        ((JavascriptExecutor) getDriver()).executeScript("arguments[0].scrollIntoView(true);", element);
    }

    public static boolean isPresent(WebElement element) {
        try {
            element.isEnabled();
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public static void pause(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public static void mySendKeys(WebElement element, String text) {
        new WebDriverWait(getDriver(), Duration.ofSeconds(20)).until(ExpectedConditions.visibilityOf(element));
        scrollToElement(element);
        element.sendKeys(text);
    }
}
