package pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

import static utilities.GWD.getDriver;

public class MessagingPage extends ParentPage {

    public MessagingPage(WebDriver driver) {
        super(driver);
    }

    public void showMessagesFromAllDates() {
        retypeDate(date, "01.01.2025");
        retypeDate(date2, "31.12.2027");
        click(search, 10);

        new WebDriverWait(getDriver(), Duration.ofSeconds(15)).withMessage(
                        "No messages are listed even after widening the date filter.")
                .until(d -> !messageRows.isEmpty());
    }

    private void retypeDate(WebElement field, String value) {
        field.sendKeys(Keys.chord(Keys.CONTROL, "a"), value);

        String expectedDigits = value.replaceAll("\\D", "");
        new WebDriverWait(getDriver(), Duration.ofSeconds(5)).withMessage(
                        "The date field kept its own value instead of accepting " + value)
                .until(d -> expectedDigits.equals(field.getAttribute("value").replaceAll("\\D", "")));
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

    @FindBy(css = "tbody.mdc-data-table__content mat-checkbox")
    public List<WebElement> allMessages;

    @FindBy(css = "ms-confirm-button[caption='USER_MESSAGES.TITLE.MOVE_TO_TRASH'] button")
    public WebElement moveToTrashButton;

    @FindBy(xpath = "//button[.//*[normalize-space(text())='Yes']]")
    public WebElement confirmMessageDeleteButton;

    @FindBy(xpath = "//div[text()='Message successfully moved to trash!']")
    public WebElement messageDeletedConfirmation;

    @FindBy(css = ".svg-inline--fa.fa-xmark.fa-fw")
    public WebElement errorToastCloseIcon;

    @FindBy(css = "button.ms-toast__close")
    public List<WebElement> toastCloseButtons;

    @FindBy(css = ".hot-toast-bar-base-wrapper")
    public List<WebElement> toastBars;

    @FindBy(xpath = "//button[.//*[name()='svg' and @data-icon='users-medical']]")
    public WebElement receiverPickerButton;

    @FindBy(xpath = "//mat-dialog-container//mat-form-field[.//mat-label[normalize-space()='Name, Username or E-mail']]//input")
    public WebElement receiverSearchBox;

    @FindBy(css = "mat-dialog-container tbody tr")
    public List<WebElement> receiverRows;

    @FindBy(css = "mat-dialog-container tbody mat-checkbox")
    public List<WebElement> receiverResults;

    @FindBy(css = "mat-dialog-container tbody mat-checkbox input")
    public List<WebElement> receiverResultInputs;

    @FindBy(xpath = "//button[normalize-space()='Add & Close']")
    public WebElement addAndCloseButton;

    @FindBy(css = "mat-chip-row")
    public List<WebElement> receiverChips;

    @FindBy(xpath = "//mat-form-field[.//mat-label[normalize-space()='Subject']]//input")
    public WebElement subjectBox;

    @FindBy(xpath = "//button[.//*[contains(normalize-space(text()),'Attach Files')]]")
    public WebElement attachFilesButton;

    @FindBy(css = "input[type='file']")
    public WebElement fileInput;

    @FindBy(xpath = "//*[contains(text(),'blank.png')]")
    public WebElement attachedFileName;

    @FindBy(xpath = "//button[.//*[normalize-space(text())='Send']]")
    public WebElement sendButton;

    @FindBy(css = "tbody.mdc-data-table__content tr")
    public List<WebElement> messageRows;
}
