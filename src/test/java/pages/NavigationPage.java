package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class NavigationPage extends ParentPage {

    public NavigationPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = "ms-layout-menu-button[page='CALENDAR']")
    public WebElement calendarLink;

    @FindBy(xpath = "(//student-toolbar-horizontal//img)[1]")
    public WebElement companyLogo;

    @FindBy(css = "ms-layout-menu-button[page='COURSES']")
    public WebElement coursesLink;

    @FindBy(css = "user-chat-bell button")
    public WebElement chatMsgLink;

    @FindBy(css = "user-message-bell button")
    public WebElement messagesLink;

    @FindBy(css = "user-announcement-bell button")
    public WebElement announcementLink;

    @FindBy(css = "button.user-button")
    public WebElement profileButton;

}
