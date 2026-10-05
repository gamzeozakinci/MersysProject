package stepDefinitions;

import io.cucumber.java.en.When;
import org.openqa.selenium.WebElement;
import pages.HeaderMenu;
import pages.NavigationPage;

import static pages.ParentPage.click;
import static utilities.GWD.getDriver;

public class US003_TopNavigationMenu {

    NavigationPage np = new NavigationPage(getDriver());
    HeaderMenu hm = new HeaderMenu(getDriver());

    @When("User navigates to {string} page")
    public void userNavigatesToPage(String linkName) {
        WebElement link;

        switch (linkName) {
            case "Grading":
                link = hm.headerGradingButton;
                break;
            case "Calendar":
                link = np.calendarLink;
                break;
            case "Courses":
                link = np.coursesLink;
                break;
            case "Attendance":
                link = hm.headerAttendanceButton;
                break;
            case "Assignments":
                link = hm.headerAssignmentButton;
                break;
            case "Hamburger Menu":
                link = hm.hamburgerButton;
                break;
            case "Chat Msg.":
                link = np.chatMsgLink;
                break;
            case "Messages":
                link = np.messagesLink;
                break;
            case "Announcements":
                link = np.announcementLink;
                break;
            case "Profile":
                link = np.profileButton;
                break;
            default:
                throw new IllegalArgumentException("No top menu link defined for: " + linkName);
        }

        click(link, 10);
    }
}
