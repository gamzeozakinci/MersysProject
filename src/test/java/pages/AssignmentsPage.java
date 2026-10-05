package pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

import static utilities.GWD.getDriver;

public class AssignmentsPage extends ParentPage {

    public AssignmentsPage(WebDriver driver) {
        super(driver);
    }

    public void widenDueDateFilter() {
        retypeDate(dueDateStart, "01.01.2025");
        retypeDate(dueDateEnd, "31.12.2027");
        click(searchButton, 10);

        new WebDriverWait(getDriver(), Duration.ofSeconds(15))
                .withMessage("The due date filter was applied but no homework is listed.")
                .until(driver -> !submitButtonsList.isEmpty());
    }

    private void retypeDate(WebElement field, String date) {
        field.sendKeys(Keys.chord(Keys.CONTROL, "a"), date);

        new WebDriverWait(getDriver(), Duration.ofSeconds(5))
                .withMessage("The date field kept its own value instead of accepting " + date)
                .until(ExpectedConditions.attributeToBe(field, "value", date));
    }

    @FindBy(tagName = "body")
    public WebElement pageBody;

    @FindBy(xpath = "//button[.//*[normalize-space(text())='Assignments']]")
    public WebElement assignmentsLink;

    @FindBy(css = "div[class='mat-mdc-tooltip-surface mdc-tooltip__surface']")
    public WebElement assignmentsCountBadge;

    @FindBy(css = "div.assignment")
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

    @FindBy(xpath = "//*[normalize-space(text())='New Submission']")
    public WebElement newSubmissionButton;

    @FindBy(css = "iframe.tox-edit-area__iframe")
    public WebElement textEditorFrame;

    @FindBy(id = "tinymce")
    public WebElement editorBody;

    @FindBy(css = "button[aria-label='Insert image']")
    public WebElement insertImageButton;

    @FindBy(css = "input[type='file'][accept='image/*']")
    public WebElement imageFileInput;

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

    @FindBy(css = "input[formcontrolname='startDate']")
    public WebElement dueDateStart;

    @FindBy(css = "input[formcontrolname='endDate']")
    public WebElement dueDateEnd;

    @FindBy(xpath = "(//mat-select)[1]")
    public WebElement classFilterDropdown;

    @FindBy(xpath = "(//mat-select)[2]")
    public WebElement statusFilterDropdown;

    @FindBy(xpath = "(//mat-select)[3]")
    public WebElement semesterFilterDropdown;

    @FindBy(css = "mat-option")
    public List<WebElement> filterOptionsList;

    @FindBy(css = "mat-select.mat-select-open")
    public List<WebElement> openDropdowns;

    @FindBy(css = ".cdk-overlay-backdrop")
    public List<WebElement> overlayBackdrops;

    @FindBy(css = "ms-drop-down button")
    public WebElement showByDropdownButton;

    @FindBy(css = ".mat-mdc-menu-item")
    public List<WebElement> showByMenuItemsList;

}
