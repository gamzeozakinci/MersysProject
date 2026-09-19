package pages;

import org.openqa.selenium.By;
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

    @FindBy(xpath= "(//*[contains(@class, 'mat-mdc-form-field-flex')])[7]")
    public WebElement themeDropdown;

    @FindBy(css = "#mat-option-3")
    public WebElement purple;

    @FindBy(css = "#mat-option-5")
    public WebElement darkpurple;

    @FindBy(css = "#mat-option-6")
    public WebElement indigo;

    public static final By confirmPurple = By.xpath("//link[contains(@href, 'purple-theme.css')]");

    public static final By confirmDarkPurple = By.xpath("//link[contains(@href, 'dark-purple-theme.css')]");

    public static final By confirmIndigo = By.xpath("//link[contains(@href, 'indigo-theme.css')]");

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
