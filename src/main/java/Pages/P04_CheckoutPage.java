package Pages;

import Utilities.Utility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static Utilities.Utility.generalWait;

public class P04_CheckoutPage {

    private final WebDriver driver;

    private final By firstName = By.id("first-name");

    private final By lastName = By.id("last-name");

    private final By zipcode = By.id("postal-code");

    private final By continueButton = By.id("continue");


    public P04_CheckoutPage(WebDriver driver)
    {
        this.driver = driver;
    }

    public P04_CheckoutPage fillingInformationFrom(String fname, String lname, String zip)
    {
        Utility.sendData(driver, firstName, fname);
        Utility.sendData(driver, lastName, lname);
        Utility.sendData(driver, zipcode, zip);
        return this;
    }

    public P05_OverViewPage clickOnContinueButton()
    {
        Utility.clickingOnElement(driver, continueButton);
        return new P05_OverViewPage(driver);

    }








}
