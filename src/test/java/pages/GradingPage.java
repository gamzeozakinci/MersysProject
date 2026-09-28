package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class GradingPage extends ParentPage {
    public GradingPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = "ms-standard-button[icon='print']")
    public WebElement printButton;
}
