package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SettingsPage {
    public SettingsPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = ".username")
    public WebElement profile;

    @FindBy(xpath = "//*[text()=\"Settings\"]")
    public WebElement settings;

    @FindBy(css = "#mat-select-value-7")
    public WebElement themeDropdown;

    @FindBy(css = "#mat-select-value-3")
    public WebElement purple;

    @FindBy(css = "#mat-select-value-3")
    public WebElement darkpurple;

    @FindBy(css = "#mat-select-value-3")
    public WebElement indigo;

    @FindBy(xpath = "//link[contains(@href, 'purple-theme.css')]")
    public WebElement confirmPurple;

    @FindBy(xpath = "//link[contains(@href, 'dark-purple-theme.css')]")
    public WebElement confirmDarkPurple;

    @FindBy(xpath = "//link[contains(@href, 'indigo-theme.css')]")
    public WebElement confirmIndigo;

    @FindBy(css = "#ms-save-button-0")
    public WebElement saveButton;

    @FindBy(xpath = "//*[contains(text(), 'Profile succesfully updated')]")
    public WebElement saveConfirm;


}
