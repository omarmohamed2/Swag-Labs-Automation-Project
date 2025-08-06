package Pages;

import Utilities.Utility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public class P01_LoginPage {
    

    private final By username = By.id("user-name");

    private final By passwrod = By.id("password");

    private final By loginbutton = By.id("login-button");

    private final WebDriver driver;

    public P01_LoginPage(WebDriver driver)
    {
        this.driver = driver;
    }

    public P01_LoginPage enterUsername(String usernameText)
    {
        Utility.sendData(driver, username, usernameText);
        return this;
    }

    public P01_LoginPage enterPasswrod(String passwordText)
    {
        Utility.sendData(driver, passwrod, passwordText);
        return this;
    }

    public P02_LandingPage clickOnLoginButton()
    {
        Utility.clickingOnElement(driver, loginbutton);
        return new P02_LandingPage(driver);
    }

    public boolean assertLoginTC(String expectedValue)
    {
        return driver.getCurrentUrl().equals(expectedValue);
    }


}
