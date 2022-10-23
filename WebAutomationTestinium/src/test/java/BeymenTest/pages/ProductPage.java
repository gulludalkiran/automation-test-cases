package BeymenTest.pages;

import BeymenTest.utils.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class ProductPage {

    public ProductPage(){
        PageFactory.initElements(Driver.get(), this);
    }

    @FindBy(xpath = "//button[@id='addBasket']")
    public WebElement addToBasket;

    @FindBy(id = "priceNew")
    public WebElement priceValue;

    @FindBys({
        @FindBy(xpath = "//span[contains(@class, 'm-variation__item') and not(contains(@class, '-disabled'))]")
    })
    public List<WebElement> sizes;

    @FindBy(xpath = "//span[@class='o-header__userInfo--count']")
    public WebElement basketValue;

    @FindBy(xpath = "(//span[@class='o-header__userInfo--text'])[3]")
    public WebElement myCart;

    @FindBy(xpath = "//span[@class='o-productDetail__description']")
    public WebElement productDetails;

}
