package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class AssignmentsPage extends ParentPage {

    public AssignmentsPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "(//span[@class='mat-focus-indicator'])[4]")
    public WebElement assignmentsLink;

    @FindBy(css = "div[class='mat-mdc-tooltip-surface mdc-tooltip__surface']")
    public WebElement assignmentsCountBadge;

    @FindBy(css = "div[class='assignment']")
    public WebElement assignments;

    @FindBy(css = "ms-icon-button[icon='comments-alt']")
    public List<WebElement> discussionButtonsList;

    @FindBy(css = "div[class='comments']")
    public WebElement discussionChatArea;

    @FindBy(xpath = "(//span[@class='mat-focus-indicator'])[45]")
    public WebElement attachFilesButton;

    @FindBy(css = "textarea[formcontrolname='commentText']")
    public WebElement commentTextArea;

    @FindBy(xpath = "(//span[@class='mat-focus-indicator'])[47]")
    public WebElement apSendButton;

    @FindBy(xpath = "//*[contains(translate(text(), 'SUCCESS', 'success'), 'success')]")
    public WebElement successMessage;

    @FindBy(css = "div[class='comment-time secondary-text']")
    public List<WebElement> commentTimeList;

    @FindBy(css = "ms-icon-button[icon='file-import']")
    public List<WebElement> submissionButtons;

    @FindBy(xpath = "//span[contains(text(), '100')]")
    public List<WebElement> numberOfHomeworks;

    @FindBy(css = "ms-dialog")
    public WebElement submissionDialog;

    @FindBy(css = "div.ms-toast__headline")
    public WebElement successMessageOnSubmission;

    @FindBy(xpath = "//*[text()='Attach Files...']")
    public WebElement attachFiles;

    @FindBy(xpath = "//*[text()=' From Local ']")
    public WebElement attachFromLocal;

    @FindBy(xpath = "//button[.//*[text()='Save As Draft']]")
    public WebElement saveAsDraft;

    @FindBy(xpath = "//button[.//*[text()='Submit']]")
    public WebElement submitButton;

    @FindBy(xpath = "//button[.//*[normalize-space(text())='Yes']]")
    public WebElement yesButton;

    @FindBy(css = ".assignment")
    public WebElement firstHomeworkButton;

    @FindBy(xpath = "//*[text()='New Submission']")
    public WebElement submissonButton;

    @FindBy(css = "iframe.tox-edit-area__iframe")
    public WebElement textEditorFrame;

    @FindBy(css = "button[aria-label='Insert image']")
    public WebElement insertImageButton;

    @FindBy(xpath = "//button[contains(@class,'tox-mbtn')][normalize-space()='Table']")
    public WebElement insertTable;

    @FindBy(xpath = "//div[@role='menuitem'][@title='Table']")
    public WebElement tableMenuItem;

    @FindBy(xpath = "//div[@role='button'][@aria-label='2 columns, 2 rows']")
    public WebElement addTable;

    @FindBy(css = "ms-icon-button[icon='info']")
    public List<WebElement> informationButtonsList;

    @FindBy(css = "ms-icon-button[icon='file-import']")
    public List<WebElement> submitButtonsList;

    @FindBy(css = "ms-icon-button[icon='star']")
    public List<WebElement> markButtonsList;

    @FindBy(css = "div.assignment")
    public List<WebElement> assignmentRowsList;

    @FindBy(xpath = "//button[.//*[normalize-space(text())='Search']]")
    public WebElement searchButton;

    @FindBy(xpath = "(//mat-select)[1]")
    public WebElement classFilterDropdown;

    @FindBy(xpath = "(//mat-select)[2]")
    public WebElement statusFilterDropdown;

    @FindBy(xpath = "(//mat-select)[3]")
    public WebElement semesterFilterDropdown;

    @FindBy(css = "mat-option")
    public List<WebElement> filterOptionsList;

    @FindBy(css = "ms-drop-down button")
    public WebElement showByDropdownButton;

    @FindBy(css = ".mat-mdc-menu-item")
    public List<WebElement> showByMenuItemsList;

}
