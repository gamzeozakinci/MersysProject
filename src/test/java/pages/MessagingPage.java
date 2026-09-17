package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class MessagingPage extends ParentPage {
    public MessagingPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    //US_007
    @FindBy(css = "tbody.mdc-data-table__content")
    public WebElement trash;

    @FindBy(css = "input.mat-start-date")
    public WebElement date;

    @FindBy(css = "input.mat-end-date")
    public WebElement date2;

    @FindBy(xpath = "//*[text()=' This year ']")
    public WebElement dateThisYear;

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

    @FindBy (xpath = "(//span[@class='mdc-button__label'])[6]")
    public WebElement hamburgerMenu;

    @FindBy(xpath = "//span[text()='Messaging']")
    public WebElement messagingLink;

    @FindBy(xpath = "//span[text()='Send Message']")
    public WebElement newMessageButton;

    @FindBy(css = "fa-icon[class='ng-fa-icon btn-fa btn-fa--table']")
    public WebElement receiversIcon;

    @FindBy(xpath = "(//input[contains(@class, 'mat-mdc-input-element')])[5]")
    public WebElement receiverSearchBox;

    @FindBy(xpath = "//span[text()=' Head Teacher ']")
    public WebElement receiverOption;

    @FindBy(xpath = "//span[text()='Add & Close']")
    public WebElement addAndCloseButton;

    @FindBy(css = "input[id='ms-text-field-0']")
    public WebElement subjectBox;

    @FindBy(css = "div[class='tox-edit-area']")
    public WebElement textEditorBox;

    @FindBy(xpath = "//span[text()='Send']")
    public WebElement sendButton;

    @FindBy(xpath = "//span[text()='Outbox']")
    public WebElement outboxLink;

    @FindBy(css = "svg[class='svg-inline--fa fa-xmark fa-fw']")
    public WebElement closeErrorButton;

    @FindBy(css = "input[class='mdc-checkbox__native-control']")
    public List<WebElement> allMessages;

    @FindBy(xpath = "//span[text()='Move To Trash']")
    public WebElement moveToTrashButton;

    @FindBy(xpath = "//span[text()=' Yes ']")
    public WebElement confirmMessageDeleteButton;

    @FindBy(xpath = "//div[text()='Message successfully moved to trash!']")
    public WebElement messageDeleteledConfirmation;

    @FindBy(xpath = "//span[text()='Attach Files...']")
    public WebElement attachFilesButton;

    @FindBy(css = "button[aria-label='Close']")
    public WebElement succesMsgCloseButton;

    @FindBy(css = "span[class='mat-mdc-select-min-line']")
    public WebElement allMessagesCount;

    @FindBy(xpath = "//*[text()=\"Trash\"]")
    public WebElement testdd;

    @FindBy(xpath = "//*[text()='Inbox']")
    public WebElement inboxButton;

}
