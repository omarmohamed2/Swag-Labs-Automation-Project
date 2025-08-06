package DriveFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class DriverFactory {

    private static final ThreadLocal<WebDriver> driverThreadLocal = new ThreadLocal<>();

    public static void setupDriver(String browser)
    {
        switch (browser.toLowerCase())
        {
            case "edge": {
                EdgeOptions options = new EdgeOptions();
                options.addArguments("--start-maximized");
                driverThreadLocal.set(new EdgeDriver(options));
                break;
            }

            case "firefox": {
                FirefoxOptions options = new FirefoxOptions();
                options.addArguments("--start-maximized");
                driverThreadLocal.set(new FirefoxDriver());
                break;
            }

            default: {
                ChromeOptions options = new ChromeOptions();
                options.addArguments("--start-maximized");
                driverThreadLocal.set(new ChromeDriver(options));
            }

        }
    }

    public static WebDriver getDriver()
    {
        return driverThreadLocal.get();
    }



    public static void quitDriver()
    {
        getDriver().quit();
        driverThreadLocal.remove();
    }
}
