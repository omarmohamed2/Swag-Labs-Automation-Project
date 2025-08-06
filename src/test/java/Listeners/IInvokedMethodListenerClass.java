package Listeners;

import Pages.P02_LandingPage;
import Utilities.LogsUtils;
import Utilities.Utility;
import io.qameta.allure.Allure;
import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ITestContext;
import org.testng.ITestResult;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.NoSuchElementException;

import static DriveFactory.DriverFactory.getDriver;

public class IInvokedMethodListenerClass implements IInvokedMethodListener {
    public void beforeInvocation(IInvokedMethod method, ITestResult testResult, ITestContext context)
    {

    }

    public void afterInvocation(IInvokedMethod method, ITestResult testResult, ITestContext context) {
        File logFile = Utility.getLastestFile(LogsUtils.LOGS_PATH);

        if (logFile != null) {
            try {
                Allure.addAttachment("logs.log", Files.readString(Path.of(logFile.getPath())));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            LogsUtils.error("No log file found to attach from: " + LogsUtils.LOGS_PATH);
        }

        if (testResult.getStatus() == ITestResult.FAILURE) {
            LogsUtils.info("Test case " + testResult.getName() + " failed");
            try {
                Utility.takeScreenshot(getDriver(), testResult.getName());
                try {
                Utility.takefullscreenshot(
                        getDriver(),
                        new P02_LandingPage(getDriver()).getNumberOfSelectedProductsOnCart()
                );
            } catch (NoSuchElementException e) {
                    LogsUtils.error("shopping_cart_badge not found, skipping full screenshot.");
                }
            } catch (Exception e) {
                LogsUtils.error("Exception while taking screenshots: " + e.getMessage());
            }

        }
    }

}
