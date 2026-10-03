package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class GradingPage extends ParentPage {

    public GradingPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = "ms-standard-button[icon='print']")
    public WebElement printButton;

    @FindBy(xpath = "//div[@role='tab'][.//span[normalize-space()='Class Grade']]")
    public WebElement classGradeTab;

    @FindBy(xpath = "//div[@role='tab'][.//span[normalize-space()='Reports']]")
    public WebElement reportsTab;

    @FindBy(css = "tr.mat-mdc-row")
    public List<WebElement> gradeRows;

    @FindBy(xpath = "//span[normalize-space()='Student Transcripts']")
    public WebElement studentTranscriptsSection;
}
