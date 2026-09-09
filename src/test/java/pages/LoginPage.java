package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends ParentPage {

    public LoginPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "input[formcontrolname='username']")
    public WebElement usernameBox;

    @FindBy(css = "input[formcontrolname='password']")
    public WebElement passwordBox;

    @FindBy(xpath = "//span[text()=' GİRİŞ YAP ']")
    public WebElement loginButton;
}
