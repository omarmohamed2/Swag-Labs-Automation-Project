package Tests;


import Listeners.IInvokedMethodListenerClass;
import Listeners.ITestResultsListenerClass;
import Pages.P02_LandingPage;
import Utilities.DataUtils;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Set;

import static DriveFactory.DriverFactory.getDriver;
import static Utilities.Utility.VerifyURL;

;

@Listeners({IInvokedMethodListenerClass.class, ITestResultsListenerClass.class})
public class TC02_LandingTest extends TestBase {

    private final String USERNAME = DataUtils.getJsonData("validLogin", "username");
    private final String PASSWORD = DataUtils.getJsonData("validLogin", "password");
    private Set<Cookie> cookies;
    private WebDriver getDriver;


    @Test
    public void CheckingNumberOfSelectedProductsTC() throws IOException {
        TC01_LoginTest.validLoginTC();
        new P02_LandingPage(getDriver()).addAllProductsToCart();

        Assert.assertTrue(new P02_LandingPage(getDriver())
                .ComparingNumberOfSelectedProductsWithCart());
    }

    @Test
    public void addingRndomProductsToCartTC() throws IOException {
        TC01_LoginTest.validLoginTC();
        new P02_LandingPage(getDriver()).addRandomProducts();

        Assert.assertTrue(new P02_LandingPage(getDriver())
                .ComparingNumberOfSelectedProductsWithCart());
    }

    public void clickOnCartIcon() throws IOException {

        new P02_LandingPage(getDriver()).clickOnCartIcon();

        Assert.assertTrue(VerifyURL(getDriver()
                , (DataUtils.getPropertyValue("environment", "CART_URL"))));
    }
}

//    @AfterClass
//    public void deleteSession()
//    {
//        cookies.clear();
//    }
//}
