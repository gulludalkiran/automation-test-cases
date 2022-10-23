package BeymenTest;

import BeymenTest.stepDefinitions.MyStepDefs;
import BeymenTest.utils.Driver;
import BeymenTest.utils.Helper;
import logger.Log;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import java.time.Duration;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class BeymenTest {
    private MyStepDefs myStepDefs = new MyStepDefs();
    private WebDriver driver = Driver.get();
    Log logger = new Log();

    String path = "productList.xlsx";

    @Test
    @Order(1)
    public void openWebsite(){
        driver.get("https://www.beymen.com/");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        logger.info("Webpage is opened");
    }

    @Test
    @Order(2)
    public void logoTextVerify() {
        myStepDefs.acceptCookie();
        myStepDefs.logoIsDisplayed();
    }

    @Test
    @Order(3)
    public void sendTextProductFromExcel(){
        String productName = Helper.getFromExcel(path, "Sheet1", 0, 0).getStringCellValue();
        myStepDefs.enteredProductName(productName);
        myStepDefs.deleteProductName();
    }

    @Test
    @Order(4)
    public void sendSecondProductFromExcel(){
        String productName = Helper.getFromExcel(path, "Sheet1", 0, 1).getStringCellValue();
        myStepDefs.enteredProductName(productName);
        myStepDefs.selectRandomProduct();
    }

    @Test
    @Order(5)
    public void addProductToBasket() {
        myStepDefs.addToBasket();
        myStepDefs.basketValueVerify();
    }

    @Test
    @Order(6)
    public void goToMyCart(){
        myStepDefs.goToMyCart();
    }

    @Test
    @Order(7)
    public void verifyBasketPage(){
        myStepDefs.basketPriceVerify();
    }

    @Test
    @Order(8)
    public void verifyQuantity(){
        myStepDefs.selectQuantity();
    }

    @Test
    @Order(9)
    public void verifyEmptyBasket(){
        myStepDefs.removeCartItem();
        Driver.closeDriver();
    }

}
