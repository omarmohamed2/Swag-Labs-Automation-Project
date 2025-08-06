package Tests;


import Listeners.IInvokedMethodListenerClass;
import Listeners.ITestResultsListenerClass;
import Pages.P01_LoginPage;
import Pages.P02_LandingPage;
import Pages.P03_CartPage;
import Utilities.DataUtils;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.io.IOException;

import static DriveFactory.DriverFactory.getDriver;

@Listeners({IInvokedMethodListenerClass.class, ITestResultsListenerClass.class})

public class TC03_CartTest extends TestBase {

    private final String USERNAME = DataUtils.getJsonData("validLogin", "username");

    private final String PASSWORD = DataUtils.getJsonData("validLogin", "password");


    @Test
    public void comparingPricesTC() throws IOException {
        String totalPrice = new P01_LoginPage(getDriver()).enterUsername(USERNAME)
                .enterPasswrod(PASSWORD).clickOnLoginButton()
                .addRandomProducts(2, 6)
                .getTotalPriceOfSelectedProducts();

        new P02_LandingPage(getDriver()).clickOnCartIcon();

        Assert.assertTrue(new P03_CartPage(getDriver())
                .comparingPrices(totalPrice));
    }

}


