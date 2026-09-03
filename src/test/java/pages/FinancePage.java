package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.FindBy;

public class FinancePage {
    public FinancePage(WebDriver driver){
        PageFactory.initElements(driver,this);

    }

    @FindBy(css = "tbody.mdc-data-table__content")
    public WebElement chooseName;





}
