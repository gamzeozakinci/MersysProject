package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class MessagingPage extends ParentPage {
    public MessagingPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = "tbody.mdc-data-table__content")
    public WebElement trash;

    @FindBy(css = "input.mat-start-date")
    public WebElement date;

    @FindBy(css = "input.mat-end-date")
    public WebElement date2;

    @FindBy(xpath = "//*[text()='Search']")
    public WebElement search;

    @FindBy(css = "div.ms-toast__headline")
    public WebElement successMessage;

    @FindBy(xpath = "//*[@icon='trash-restore']")
    public WebElement restoreButton;

    @FindBy(css = "button.error")
    public WebElement deleteButton;

    @FindBy(css = "mat-dialog-container")
    public WebElement confirmationDialog;

    @FindBy(xpath = "//button[.//*[normalize-space(text())='Delete']]")
    public WebElement dialogAnswer;

    @FindBy(css = "input[class='mdc-checkbox__native-control']")
    public List<WebElement> allMessages;

    @FindBy(xpath = "//span[text()='Move To Trash']")
    public WebElement moveToTrashButton;

    @FindBy(xpath = "//span[text()=' Yes ']")
    public WebElement confirmMessageDeleteButton;

    @FindBy(xpath = "//div[text()='Message successfully moved to trash!']")
    public WebElement messageDeletedConfirmation;
}
