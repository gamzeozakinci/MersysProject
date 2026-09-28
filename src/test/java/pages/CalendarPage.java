package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class CalendarPage extends ParentPage {
    public CalendarPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = "table[style='table-layout: fixed;']")
    public List<WebElement> courseNamesTab;

    @FindBy(xpath = "//div[@role='tab'][.//span[normalize-space()='Information']]")
    public WebElement informationTab;

    @FindBy(xpath = "//div[@role='tab'][.//span[normalize-space()='Topic']]")
    public WebElement topicTab;

    @FindBy(xpath = "//div[@role='tab'][.//span[normalize-space()='Attachments']]")
    public WebElement attachmentsTab;

    @FindBy(xpath = "//div[@role='tab'][.//span[normalize-space()='Recent Events']]")
    public WebElement recentEventsTab;

    @FindBy(xpath = "//div[@role='tab'][.//span[normalize-space()='Weekly Schedule']]")
    public WebElement weeklyScheduleTab;

    @FindBy(css = "ms-course-schedule-board h4 strong")
    public WebElement weeklyDateRange;

    @FindBy(css = "ms-course-schedule-board span.status-letter")
    public List<WebElement> statusLetters;

    @FindBy(css = "ms-course-schedule-board span.status-letter + span")
    public List<WebElement> statusMeanings;

    @FindBy(xpath = "//div[@role='tab'][.//span[normalize-space()='Calendar']]")
    public WebElement calendarTab;

    @FindBy(xpath = "//ms-course-schedule-board//button[.//*[name()='svg' and @data-icon='chevron-left']]")
    public WebElement previousWeekButton;

    @FindBy(xpath = "//ms-course-schedule-board//div[contains(@class,'mat-elevation-z4') and contains(@style,'cursor: pointer')]")
    public List<WebElement> responsibleCourses;

    @FindBy(css = "ms-course-schedule-board button.mat-mdc-icon-button")
    public List<WebElement> scheduleActionButtons;

    @FindBy(xpath = "//ms-course-schedule-board//div[contains(@class,'mat-elevation-z4')][.//span[contains(@class,'mat-badge-content') and normalize-space()='E']]")
    public List<WebElement> completedClasses;

    @FindBy(xpath = "//button[.//span[normalize-space()='Recording']]")
    public WebElement recordingButton;

    @FindBy(css = "button.vjs-big-play-button")
    public WebElement playButton;

    @FindBy(css = "video.vjs-tech")
    public WebElement recordingVideo;

    @FindBy(css = "iframe[src*='scalelite.mersys.io/playback']")
    public WebElement recordingIframe;
}
