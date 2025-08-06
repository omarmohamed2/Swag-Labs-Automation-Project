package Tests;

import Listeners.IInvokedMethodListenerClass;
import Listeners.ITestResultsListenerClass;
import Pages.P01_LoginPage;
import Utilities.DataUtils;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.io.IOException;

import static DriveFactory.DriverFactory.getDriver;


@Listeners({IInvokedMethodListenerClass.class, ITestResultsListenerClass.class})

public class TC01_LoginTest extends TestBase {

    private static final String USERNAME = DataUtils.getJsonData("validLogin", "username");

    private static final String PASSWORD = DataUtils.getJsonData("validLogin", "password");


    @Test
    public static void validLoginTC() throws IOException {
        new P01_LoginPage(getDriver()).enterUsername(USERNAME)
                .enterPasswrod(PASSWORD).clickOnLoginButton();

        Assert.assertEquals(getDriver().getCurrentUrl(), DataUtils.getPropertyValue("environment", "HOME_URL"));
    }


}
