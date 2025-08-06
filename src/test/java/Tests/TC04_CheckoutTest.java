package Tests;


import Listeners.IInvokedMethodListenerClass;
import Listeners.ITestResultsListenerClass;
import Pages.P01_LoginPage;
import Utilities.DataUtils;
import Utilities.LogsUtils;
import Utilities.Utility;
import com.github.javafaker.Faker;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.io.IOException;

import static DriveFactory.DriverFactory.getDriver;
import static Utilities.DataUtils.getPropertyValue;

@Listeners({IInvokedMethodListenerClass.class, ITestResultsListenerClass.class})


public class TC04_CheckoutTest extends TestBase {

    private final String USERNAME = DataUtils.getJsonData("validLogin", "username");

    private final String PASSWORD = DataUtils.getJsonData("validLogin", "password");

    private final String FIRSTNAME = DataUtils.getJsonData("information", "fname") + "-" + Utility.getTimeStamp();

    private final String LASTNAME = DataUtils.getJsonData("information", "lname") + "-" + Utility.getTimeStamp();

    private final String ZIPCODE = new Faker().number().digits(5);


    @Test
    public void checkoutStepOneTC() throws IOException {
        new P01_LoginPage(getDriver()).enterUsername(USERNAME)
                .enterPasswrod(PASSWORD).clickOnLoginButton()
                .addRandomProducts(2, 6)
                .clickOnCartIcon().clickOnCheckoutButton()
                .fillingInformationFrom(FIRSTNAME, LASTNAME, ZIPCODE)
                .clickOnContinueButton();


        LogsUtils.info(FIRSTNAME + " " + LASTNAME + " " + ZIPCODE);
        Assert.assertEquals(getDriver().getCurrentUrl(), getPropertyValue("environment", "CHECKOUT_URL"));
    }

}

