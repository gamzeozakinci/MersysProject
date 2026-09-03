package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HeaderMenu {
    public HeaderMenu(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = ".svg-inline--fa.fa-bars.fa-fw")
    public WebElement hamburgerButton;

    @FindBy(xpath = "//*[text()=\"Finance\"]")
    public WebElement hamburgerButtonFinance;

    @FindBy(xpath = "//*[text()=\"My Finance\"]")
    public WebElement hamburgerButtonMYFinance;

    //denem push



}
