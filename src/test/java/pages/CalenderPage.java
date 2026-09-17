package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class CalenderPage extends ParentPage{
    public CalenderPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "table[style='table-layout: fixed;']")
    public List<WebElement> courseNamesTab;

    @FindBy(xpath = "(//span[@class='mat-focus-indicator'])[17]")
    public WebElement previusPageButton;

    @FindBy(xpath = "(//div[@class='mat-elevation-z4'])[3]")
    public WebElement completedClassEvent;

    // Tabs inside the course details modal
    @FindBy(xpath = "(//div[@role='tab'])[4]")
    public WebElement informationTab;

    @FindBy(xpath = "(//div[@role='tab'])[5]")
    public WebElement topicTab;

    @FindBy(xpath = "(//div[@role='tab'])[6]")
    public WebElement attachmentsTab;

    @FindBy(xpath = "(//div[@role='tab'])[7]")
    public WebElement recentEventsTab;

    // US-23 - Weekly Schedule tab
    @FindBy(xpath = "//div[@role='tab'][.//span[normalize-space()='Weekly Schedule']]")
    public WebElement weeklyScheduleTab;

    // US-23 - Weekly Schedule üzerinde görüntülenen tarih aralığı
    @FindBy(css = "ms-course-schedule-board h4 strong")
    public WebElement weeklyDateRange;

    // US-23 - Course status letters (P, S, E, C)
    @FindBy(css = "ms-course-schedule-board span.status-letter")
    public List<WebElement> statusLetters;

    // US-23 - Course status meanings (Published, Started, Completed/Ended, Cancelled)
    @FindBy(css = "ms-course-schedule-board span.status-letter + span")
    public List<WebElement> statusMeanings;

    // US-23 - Calendar tab
    @FindBy(xpath = "//div[@role='tab'][.//span[normalize-space()='Calendar']]")
    public WebElement calendarTab;

    // US-23 - Previous week navigation button
    @FindBy(xpath = "//button[.//*[name()='svg' and @data-icon='chevron-left']]")
    public WebElement previousWeekButton;

    // US-23 - Today navigation button
    @FindBy(xpath = "//button[.//*[name()='svg' and @data-icon='calendar-day']]")
    public WebElement todayButton;

    // US-23 - Next week navigation button
    @FindBy(xpath = "//button[.//*[name()='svg' and @data-icon='chevron-right']]")
    public WebElement nextWeekButton;

    // US-23 - Weekly Schedule üzerindeki sorumlu olunan tıklanabilir ders kartları
    @FindBy(xpath = "//ms-course-schedule-board//div[contains(@class,'mat-elevation-z4') and contains(@style,'cursor: pointer')]")
    public List<WebElement> responsibleCourses;

    // US-23 - Weekly Schedule üzerindeki navigasyon ve işlem butonları
    @FindBy(css = "ms-course-schedule-board button.mat-mdc-icon-button")
    public List<WebElement> scheduleActionButtons;

    // US-25 - Weekly Schedule üzerindeki Completed (E) durumundaki ders kartları
    @FindBy(xpath = "//ms-course-schedule-board//div[contains(@class,'mat-elevation-z4')][.//span[contains(@class,'mat-badge-content') and normalize-space()='E']]")
    public List<WebElement> completedClasses;

    // US-25 - Completed class detay penceresindeki Recording butonu
    @FindBy(xpath = "//button[.//span[normalize-space()='Recording']]")
    public WebElement recordingButton;

    // US-25 - Recording ekranındaki Play butonu
    @FindBy(css = "button.vjs-big-play-button")
    public WebElement playButton;

    // US-25 - Recording ekranındaki video elementi
    @FindBy(css = "video.vjs-tech")
    public WebElement recordingVideo;

    // US-25 - Class recording player iframe
    @FindBy(css = "iframe[src*='scalelite.mersys.io/playback']")
    public WebElement recordingIframe;
}
