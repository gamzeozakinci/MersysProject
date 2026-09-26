package stepDefinitions;
import io.cucumber.java.en.When;
import pages.HeaderMenu;
import pages.NavigationPage;
import pages.ParentPage;
import utilities.GWD;

public class US003_TopNavigationMenu {

    NavigationPage np = new NavigationPage(GWD.getDriver());
    HeaderMenu hm = new HeaderMenu(GWD.getDriver());

    @When("User navigates to {string} page")
    public void userNavigatesToPage(String linkName) {
        switch (linkName) {
            case "Grading":
                ParentPage.click(hm.headerGradingButton, 10);
                break;
            case "Calendar":
                ParentPage.click(np.calendarLink,10);
                break;

            case "Courses":
                ParentPage.click(np.coursesLink, 10);
                break;

            case "Attendance":
                ParentPage.click(hm.headerAttendanceButton, 10);
                break;

            case "Assignments":
                ParentPage.click(hm.headerAssignmentButton, 10);
                break;

            case "Hamburger Menu":
                ParentPage.click(hm.hamburgerButton, 10);
                break;

            case "Chat Msg.":
                ParentPage.click(np.chatMsgLink, 10);
                break;

            case "Messages":
                ParentPage.click(np.messagesLink, 10);
                break;

            case "Announcements":
                ParentPage.click(np.announcementLink, 10);
                break;

            case "Profile":
                ParentPage.click(np.profileButton, 10);
                break;

        }
    }

}
