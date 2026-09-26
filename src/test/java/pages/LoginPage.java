package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends ParentPage {

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = "input[formcontrolname='username']")
    public WebElement usernameBox;

    @FindBy(css = "input[formcontrolname='password']")
    public WebElement passwordBox;

    @FindBy(css = "button[aria-label='LOGIN']")
    public WebElement loginButton;

    @FindBy(css = "div[class='main-container']")
    public WebElement dashboardHeader;

    @FindBy(xpath = "//div[text()='Invalid username or password']")
    public WebElement errorMessage;

}
