package Pages;

import Utilities.LogsUtils;
import Utilities.Utility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class P05_OverViewPage {

    private final WebDriver driver;

    private final By subTotal = By.className("summary_subtotal_label");

    private final By tax = By.className("summary_tax_label");

    private final By total = By.xpath("//div[contains(@class,'summary_total_label')]");

    private final By finishButton = By.id("finish");

    public P05_OverViewPage(WebDriver driver)
    {
        this.driver = driver;
    }

    public Float getSubTotal ()
    {
        return Float.parseFloat(Utility.getText(driver, subTotal)
                .replace("Item total: $", ""));
    }

    public Float getTax ()
    {
        return Float.parseFloat(Utility.getText(driver, tax)
                .replace("Tax: $", ""));
    }

    public Float getTotal ()
    {
        LogsUtils.info("Actual Total Price: " + Utility.getText(driver, total)
                .replace("Total: $", ""));

        return Float.parseFloat(Utility.getText(driver, total)
                .replace("Total: $", ""));
    }

    public String calculateTotalPrice()
    {
        LogsUtils.info("Calculated Total Price: " + (getSubTotal() + getTax()));
        return String.valueOf(getSubTotal() + getTax() );
    }

    public boolean comparingPrices()
    {
        return calculateTotalPrice().equals(String.valueOf(getTotal()));
    }

    public P06_FinishingOrder clickOnFinishButton()
    {
        Utility.clickingOnElement(driver, finishButton);
        return new P06_FinishingOrder(driver);
    }
}
