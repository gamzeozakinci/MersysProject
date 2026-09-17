package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class NavigationPage extends ParentPage {

    public NavigationPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "ms-layout-menu-button[page='GRADING']")
    public WebElement gradingLink;

    @FindBy(css = "ms-layout-menu-button[page='CALENDAR']")
    public WebElement calenderLink;

    @FindBy(xpath = "(//student-toolbar-horizontal//img)[1]")
    public WebElement companyLogo;

    @FindBy(css = "ms-layout-menu-button[page='COURSES']")
    public WebElement coursesLink;

    @FindBy(xpath = "//ms-layout-menu-button[@page='ATTENDANCE']")
    public WebElement attendanceLink;

    @FindBy(xpath = "//ms-layout-menu-button[@page='ASSIGNMENT']")
    public WebElement assignmentsLink;

    @FindBy(css = "user-chat-bell button")
    public WebElement chatMsgLink;

    @FindBy(css = "user-message-bell button")
    public WebElement messagesLink;

    @FindBy(css = "user-announcement-bell button")
    public WebElement announcementLink;

    @FindBy(css = "button.user-button")
    public WebElement profileButton;


}
