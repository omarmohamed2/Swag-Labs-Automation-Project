package Tests;


import Listeners.IInvokedMethodListenerClass;
import Listeners.ITestResultsListenerClass;
import Pages.*;
import Utilities.DataUtils;
import Utilities.LogsUtils;
import Utilities.Utility;
import com.github.javafaker.Faker;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.io.IOException;

import static DriveFactory.DriverFactory.getDriver;

@Listeners({IInvokedMethodListenerClass.class, ITestResultsListenerClass.class})

public class TC06_FinishingOrderTest extends TestBase {

    private final String USERNAME = DataUtils.getJsonData("validLogin", "username");

    private final String PASSWORD = DataUtils.getJsonData("validLogin", "password");

    private final String FIRSTNAME = DataUtils.getJsonData("information", "fname") + "-" + Utility.getTimeStamp();

    private final String LASTNAME = DataUtils.getJsonData("information", "lname") + "-" + Utility.getTimeStamp();

    private final String ZIPCODE = new Faker().number().digits(5);


    @Test
    public void FinishOrderTC() throws IOException {

        //TODO:Login Steps
        new P01_LoginPage(getDriver()).enterUsername(USERNAME)
                .enterPasswrod(PASSWORD).clickOnLoginButton();

        //TODO:Adding Products Steps

        new P02_LandingPage(getDriver()).addAllProductsToCart()
                .clickOnCartIcon();

        //TODO:Go to checkout page steps

        new P03_CartPage(getDriver()).clickOnCheckoutButton();

        //TODO Filling Information Steps

        new P04_CheckoutPage(getDriver()).fillingInformationFrom(FIRSTNAME, LASTNAME, ZIPCODE)
                .clickOnContinueButton();

        LogsUtils.info(FIRSTNAME + " " + LASTNAME + " " + ZIPCODE);

        //TODO Go to Finishing Order Page Steps

        new P05_OverViewPage(getDriver()).clickOnFinishButton();


        Assert.assertTrue(new P06_FinishingOrder(getDriver()).checkVisibilityOfThanksMessage());

    }

}


