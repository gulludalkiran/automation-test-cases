package BeymenTest.pages;

import BeymenTest.utils.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MyCartPage {

    public MyCartPage(){
        PageFactory.initElements(Driver.get(), this);
    }

    @FindBy(xpath = "//span[@class='m-productPrice__salePrice']")
    public WebElement productPrice;

    @FindBy(xpath = "//div[@class='m-select -small']")
    public WebElement quantity;

    @FindBy(xpath = "//select[@id='quantitySelect0-key-0']//option[2]")
    public WebElement quantityValue;

    @FindBy(xpath = "//button[@id='removeCartItemBtn0-key-0']")
    public WebElement removeCartItemButton;

    @FindBy(xpath = "//a[@class='m-empty__messageBtn']")
    public WebElement emptyMessageButton;
}
