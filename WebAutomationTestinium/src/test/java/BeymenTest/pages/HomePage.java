package BeymenTest.pages;

import BeymenTest.utils.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class HomePage {

    public HomePage(){
        PageFactory.initElements(Driver.get(), this);
    }

    @FindBy(xpath = "//button[@id='onetrust-accept-btn-handler']")
    public WebElement acceptCookie;


    @FindBy(xpath = "//a[@title='Beymen']")
    public WebElement logoName;

    @FindBy(xpath = "//input[@class='default-input o-header__search--input']")
    public WebElement searchBox;

    @FindBys( {
        @FindBy(css = ".o-productList__itemWrapper")
    } )
    public List<WebElement> productList;




}
