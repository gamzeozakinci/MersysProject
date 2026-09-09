package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class AttendancePage {
    public AttendancePage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//*[text()=' ATTENDANCE EXCUSES ']")
    public WebElement attendanceExcuses;

    @FindBy(xpath = "//*[@tooltip='ATTENDANCE_EXCUSE.TITLE.ADD']")
    public WebElement addAttendance;

    @FindBy(css = "textarea.mat-mdc-form-field-textarea-control")
    public WebElement enterDescription;

    @FindBy(xpath = "//button[contains(., 'Attach Files')]")
    public WebElement attachFilesButton;

    @FindBy(xpath = "//*[normalize-space()='From Local']")
    public WebElement fromLocalOption;

    @FindBy(css = "div.attachment")
    public WebElement attachmentConfirmation;

    @FindBy(xpath = "//button[.//span[text()='Send']]")
    public WebElement send;

    @FindBy(xpath = "//*[text()='Attendance Excuse successfully sent']")
    public WebElement confirmExcuse;

}
