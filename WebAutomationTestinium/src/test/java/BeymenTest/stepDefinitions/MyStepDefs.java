package BeymenTest.stepDefinitions;

import BeymenTest.pages.HomePage;
import BeymenTest.pages.MyCartPage;
import BeymenTest.pages.ProductPage;
import BeymenTest.utils.Helper;
import logger.Log;
import org.junit.Assert;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.Random;

public class MyStepDefs {

    private Log logger = new Log();

    private HomePage homePage;
    private MyCartPage myCartPage;
    private ProductPage productPage;
    private int wait = 3;

    private String dataFile = "productData.txt";
    private String prevProductValue;

    public MyStepDefs() {
        this.homePage = new HomePage();
        this.myCartPage = new MyCartPage();
        this.productPage = new ProductPage();
    }

    public  void acceptCookie(){
        homePage.acceptCookie.click();
    }

    public void logoIsDisplayed() {
        Assert.assertTrue(homePage.logoName.isDisplayed());
        logger.info("Logo text is displayed.");
    }

    public void enteredProductName(String productName){
        homePage.searchBox.click();
        homePage.searchBox.sendKeys(productName);
        homePage.searchBox.sendKeys(Keys.ENTER);
        logger.info("Searched product: "+ productName);
        Helper.waitFor(wait);
    }

    public void deleteProductName(){
        homePage.searchBox.click();
        homePage.searchBox.sendKeys(Keys.CONTROL + "a");
        homePage.searchBox.sendKeys(Keys.DELETE);
        logger.info("Search box cleared.");
        Helper.waitFor(wait);
    }

    public void selectRandomProduct(){
        // Find and click on a random product
        List<WebElement> allProducts = homePage.productList;
        Random rand = new Random();
        int randomProduct = rand.nextInt(allProducts.size());
        allProducts.get(randomProduct).click();
        logger.info("Random product selected.");
    }

    public void selectRandomProductSize(){
        // Find and click on a random size
        List<WebElement> allSizes = productPage.sizes;
        Random rand = new Random();
        int randomSize = rand.nextInt(allSizes.size());
        allSizes.get(randomSize).click();
        logger.info("Random size selected.");
    }

    public void addToBasket(){
        prevProductValue = productPage.priceValue.getText();
        logger.info("Product Price: " + prevProductValue);
        String productName = productPage.productDetails.getText();
        logger.info("Product Name: " + productName);
        Helper.writeToFile("Product Name: " + productName + " # Product Price: " + prevProductValue, dataFile);
        Helper.scroll2Element(productPage.addToBasket);
        selectRandomProductSize();
        productPage.addToBasket.click();
        logger.info("Product added to basket.");
        Helper.waitFor(wait);
    }

    public void basketValueVerify(){
        String basketValue = productPage.basketValue.getText();
        Assert.assertEquals("(1)", basketValue);
        logger.info("Product count in the Basket: " + basketValue);
    }

    public void goToMyCart(){
        productPage.myCart.click();
    }

    public void basketPriceVerify(){
        Assert.assertEquals(prevProductValue, myCartPage.productPrice.getText());
        logger.info("Product price is equals with basket price");
    }

    public void selectQuantity(){
        Helper.waitFor(wait);
        myCartPage.quantity.click();
        Helper.waitFor(wait);
        try {
            myCartPage.quantityValue.click();
            Assert.assertEquals("2 adet" , myCartPage.quantityValue.getText());
            logger.info("2 pieces added in the basket.");
        } catch (Exception e) {
            logger.info("There is no stock to be able to select 2 pieces");
        }
    }

    public void removeCartItem(){
        Helper.waitFor(wait);
        myCartPage.removeCartItemButton.click();
        logger.info(myCartPage.emptyMessageButton.getText());
        Assert.assertTrue(myCartPage.emptyMessageButton.getText().contains("ALIŞVERIŞE DEVAM ET"));
        logger.info("There is no product in your cart.");
    }

}
