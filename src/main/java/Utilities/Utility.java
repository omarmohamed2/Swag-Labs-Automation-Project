package Utilities;

import com.assertthat.selenium_shutterbug.core.Capture;
import com.assertthat.selenium_shutterbug.core.Shutterbug;
import io.qameta.allure.Allure;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.*;

public class Utility {
    private static final String SCREENSHOTS_PATH = "test-outputs/Screenshots/";

    public static void clickingOnElement(WebDriver driver, By locator)
    {
        new WebDriverWait(driver, Duration.ofSeconds(20))
                .until(ExpectedConditions.elementToBeClickable(locator));
        driver.findElement(locator).click();
    }

    public static void sendData(WebDriver driver, By locator, String data)
    {
        new WebDriverWait(driver, Duration.ofSeconds(20))
                .until(ExpectedConditions.visibilityOfElementLocated(locator));
        driver.findElement(locator).sendKeys(data);
    }

    public static String getText(WebDriver driver, By locator)
    {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(locator));
        return driver.findElement(locator).getText();
    }

    public static WebDriverWait generalWait(WebDriver driver)
    {
        return new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    public static void scrolling(WebDriver driver,By locator)
    {
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();",findWebElement(driver,locator));
    }

    public static WebElement findWebElement(WebDriver driver,By locator)
    {
        return driver.findElement(locator);
    }

    public static void selectingFromDropDown (WebDriver driver,By locator,String option)
    {
        new Select(findWebElement(driver,locator)).selectByVisibleText(option);
    }
    public static String getTimeStamp()
    {
        return new SimpleDateFormat("yyyy-MM-dd-h-m-ssa").format(new Date());
    }

    public static void takeScreenshot(WebDriver driver, String screenshotName) throws IOException {
        try {
            File screenshotSrc = ((TakesScreenshot) driver)
                    .getScreenshotAs(OutputType.FILE);

            // save screenshot to file if needed
            File screenshotFile = new File(SCREENSHOTS_PATH + screenshotName +"-"+getTimeStamp() +".png");
            FileUtils.copyFile(screenshotSrc, screenshotFile);

            // attach the screenshot to allure
            Allure.addAttachment(screenshotName, Files
                    .newInputStream(Path.of(screenshotFile.getPath())));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void takefullscreenshot(WebDriver driver, By locator) throws Exception {
        Shutterbug.shootPage(driver, Capture.FULL_SCROLL)
                .highlight(findWebElement(driver, locator)).
                save(SCREENSHOTS_PATH);
    }

    public static int generateRandomNumber(int upperBound) //0 >> upper-1  >5
    {
        return new Random().nextInt(upperBound) +1 ;
    }

    //set >> unique >> 1,2,3,4,3 > condition

    public static Set<Integer> generateUniqueNumber(int numberOfProductsNeeded, int totalNumberOfProducts)
    {
        Set<Integer> generateNumbers = new HashSet<>();
        while (generateNumbers.size() < numberOfProductsNeeded)
        {
            int randomNumber = generateRandomNumber(totalNumberOfProducts);
            generateNumbers.add(randomNumber);
        }
        return generateNumbers;
    }   // 5 >> 50



    public static boolean VerifyURL(WebDriver driver, String expectedURL) {
        try {
            generalWait(driver).until(ExpectedConditions.urlToBe(expectedURL));
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public static Set<Cookie> getAllCookies(WebDriver driver)
    {
        return driver.manage().getCookies();
    }

    public static void restoreSeesion (WebDriver driver, Set<Cookie> cookies)
    {
        for (Cookie cookie:cookies)
            driver.manage().addCookie(cookie);
    }

    public static File getLastestFile(String folderPath) {
        File folder = new File(folderPath);

        if (!folder.exists() || !folder.isDirectory()) {
            System.err.println("Directory does not exist or is not a directory: " + folderPath);
            return null;
        }

        File[] files = folder.listFiles();
        if (files == null || files.length == 0) {
            System.err.println("No files found in directory: " + folderPath);
            return null;
        }

        Arrays.sort(files, Comparator.comparingLong(File::lastModified).reversed());
        return files[0];
    }



}
