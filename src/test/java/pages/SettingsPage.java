package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SettingsPage extends ParentPage {
    public SettingsPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = ".username")
    public WebElement profile;

    @FindBy(xpath = "//*[text()=\"Settings\"]")
    public WebElement settings;

    @FindBy(xpath= "(//*[contains(@class, 'mat-mdc-form-field-flex')])[7]")
    public WebElement themeDropdown;

    @FindBy(css = "#mat-option-3")
    public WebElement purple;

    @FindBy(css = "#mat-option-5")
    public WebElement darkpurple;

    @FindBy(css = "#mat-option-6")
    public WebElement indigo;

    @FindBy(xpath = "//link[starts-with(@href, 'purple-theme.css') or contains(@href, '/purple-theme.css')]")
    public WebElement purpleThemeLink;

    @FindBy(xpath = "//link[contains(@href, 'dark-purple-theme.css')]")
    public WebElement darkPurpleThemeLink;

    @FindBy(xpath = "//link[contains(@href, 'indigo-theme.css')]")
    public WebElement indigoThemeLink;

    @FindBy(css = "#ms-save-button-0")
    public WebElement saveButton;

    @FindBy(xpath = "//div[contains(@class,'ms-toast__headline') and normalize-space()='Profile successfully updated']")
    public WebElement saveConfirm;

    @FindBy(css = "img.profile-image")
    public WebElement profilePicture;

    @FindBy(xpath = "//h3[contains(@id,'mat-mdc-dialog-title')]")
    public WebElement profilePhotoWindowTitle;

    @FindBy(xpath = "//span[contains(text(),'KB')]")
    public WebElement uploadedImageSize;

    @FindBy(xpath = "//button[.//span[normalize-space()='Upload']]")
    public WebElement uploadButton;

    @FindBy(xpath = "//mat-form-field[contains(@class,'mat-mdc-form-field-type-file-input')]//button")
    public WebElement fileSelectButton;

    @FindBy(xpath = "//user-upload-dialog//button[.//span[normalize-space()='Close']]")
    public WebElement closeButton;

}
