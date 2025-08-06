package Pages;

import Utilities.LogsUtils;
import Utilities.Utility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.bidi.log.Log;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;
import java.util.Set;

import static Utilities.Utility.generalWait;

public class P02_LandingPage {

    static float totalPrice = 0;

    private final By addToCartButtonForAllProducts = By.xpath("//button[@class]");

    private final By numberOfProductsOnCartIcon = By.className("shopping_cart_badge");

    private final By numberOfSelectedProducts = By.xpath("//button[.='Remove']");

    private static List<WebElement> allProducts;

    private static List<WebElement> selectedProducts;

    private final By pricesOfSelectedProductsLocator = By.xpath("//button[.=\"Remove\"] //preceding-sibling::div[@class='inventory_item_price']");

    private final By cartIcon = By.className("shopping_cart_link");

    private final WebDriver driver;

    public P02_LandingPage(WebDriver driver) {
        this.driver = driver;
    }

    public By getNumberOfSelectedProductsOnCart()
    {
        return numberOfProductsOnCartIcon;
    }

    public P02_LandingPage addAllProductsToCart() {
        allProducts = driver.findElements(addToCartButtonForAllProducts);
        LogsUtils.info("number of all products:" + allProducts.size());
        for (int i = 1; i <= allProducts.size(); i++) {
            By addToCartButtonForAllProducts = By.xpath("(//button[@class])[" + i + "]"); // Dynamic Locator
            Utility.clickingOnElement(driver, addToCartButtonForAllProducts);
        }
        return this;
    }

    public String getNumberOfProductsOnCartIcon() {
        try {
            LogsUtils.info("number of products on cart: " + Utility.getText(driver, numberOfProductsOnCartIcon));
            return Utility.getText(driver, numberOfProductsOnCartIcon);
        } catch (Exception e) {
            LogsUtils.error(e.getMessage());
            return "0";
        }
    }

    public String getNumberOfSelectedProducts() {
        try {
            selectedProducts = driver.findElements(numberOfSelectedProducts);
            LogsUtils.info("selected products : " + (selectedProducts.size()));
            return String.valueOf(selectedProducts.size());
        } catch (Exception e) {
            LogsUtils.error(e.getMessage());
            return "0";
        }
    }

    public P02_LandingPage addRandomProducts(int numberOfProductsNeeded, int totalNumberOfProducts) {
        Set<Integer> randomNumbers = Utility.generateUniqueNumber(numberOfProductsNeeded, totalNumberOfProducts);
        for (int random : randomNumbers) {
            LogsUtils.info("randomNumber" + random);
            By addToCartButtonForAllProducts = By
                    .xpath("(//button[@class])[" + random + "]");
            Utility.clickingOnElement(driver, addToCartButtonForAllProducts);
        }
        return this;
    }

    public boolean ComparingNumberOfSelectedProductsWithCart() {
        return getNumberOfProductsOnCartIcon().equals(getNumberOfSelectedProducts());
    }

    public P03_CartPage clickOnCartIcon() {
        Utility.clickingOnElement(driver, cartIcon);
        return new P03_CartPage(driver);
    }

    public void addRandomProducts() {
    }

    public String getTotalPriceOfSelectedProducts() {
        try {
            List<WebElement> pricesOfSelectedProducts = driver
                    .findElements(pricesOfSelectedProductsLocator);
            for (int i =1; i<=pricesOfSelectedProducts.size(); i++) {
                By elements = By.xpath("(//button[.=\"Remove\"] //preceding-sibling::div[@class='inventory_item_price'])["+ i +"]");
                Utility.getText(driver, elements);
                String fullText = Utility.getText(driver, elements);
                LogsUtils.info("Total Price " + totalPrice);
                totalPrice += Float.parseFloat(fullText.replace("$",""));
            }
            LogsUtils.info("Total Price " + totalPrice);
            return String.valueOf(totalPrice);
        }
        catch (Exception e)
        {
            LogsUtils.error(e.getMessage());
            return "0";
        }
    }



}
