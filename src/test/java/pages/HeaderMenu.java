package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HeaderMenu extends ParentPage {
    public HeaderMenu(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = ".svg-inline--fa.fa-bars.fa-fw")
    public WebElement hamburgerButton;

    @FindBy(xpath = "//*[text()=\"Finance\"]")
    public WebElement hamburgerButtonFinance;

    @FindBy(xpath = "//*[text()=\"My Finance\"]")
    public WebElement hamburgerButtonMyFinance;

    @FindBy(xpath = "//*[@caption=\"NAV.ATTENDANCE.TITLE\"]")
    public WebElement headerAttendanceButton;

    @FindBy(xpath = "//*[@caption=\"MY_PAGE.TAB_TITLE.ASSIGNMENTS\"]")
    public WebElement headerAssignmentButton;

    @FindBy(xpath = "//button[.//*[normalize-space(text())='Messaging']]")
    public WebElement headerMessagingButton;

    @FindBy(xpath = "//button[.//*[normalize-space(text())='Send Message']]")
    public WebElement headerNewMessageButton;

    @FindBy(xpath = "//*[text()='Inbox']")
    public WebElement headerInboxButton;

    @FindBy(xpath = "//span[text()='Outbox']")
    public WebElement headerOutboxButton;

    @FindBy(xpath = "//button[.//*[normalize-space(text())='Trash']]")
    public WebElement headerTrashButton;

    @FindBy(xpath = "//*[@caption=\"NAV.GRADING.TITLE\"]")
    public WebElement headerGradingButton;

}
