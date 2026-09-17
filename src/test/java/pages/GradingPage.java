package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class GradingPage {
    public GradingPage(WebDriver driver) {
        PageFactory.initElements(driver, this);

    }

    @FindBy(css = "ms-standard-button[icon='print']")
    public WebElement printButton;

    @FindBy(css = "#print")
    public WebElement downloadButton;

    @FindBy(xpath = "//span[text()=' Reports ']")
    public WebElement reportsTab;

    @FindBy(css = "div[class='limit-word black']")
    public List<WebElement> letterGradesList;

    @FindBy(xpath = "//span[text()='Student Transcripts']")
    public WebElement studentTranscriptButton;

    //fake locater
    @FindBy(css = "div[class='limit-word secondary-text black']")
    public WebElement transcriptBySubjectButton;

}