package base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class Base {
    private WebDriver driver;
    public WebDriver getDriver() {
        return driver;
    }

    public Base(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    protected boolean waitVisiblityElement(WebElement element) {
        WebDriverWait driverWait = new WebDriverWait(driver, 10);
        try {
            driverWait.until(ExpectedConditions.visibilityOf(element));
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    protected boolean waitVisiblityElement(List<WebElement> element) {
        WebDriverWait driverWait = new WebDriverWait(driver, 20);
        try {
            for (WebElement x: element)
            driverWait.until(ExpectedConditions.visibilityOf(x));
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public void click(final WebElement element) {
        waitVisiblityElement(element);
        element.click();
    }
    public void clickHiddenElement(final WebElement element) {
        element.click();
    }

    public void setText(final WebElement element, final String str) {
        waitVisiblityElement(element);
        element.sendKeys(str);
    }

    public void removeText(final WebElement element, final String str) {
        waitVisiblityElement(element);
        element.clear();
        click(element);
        setText(element, str);
    }

    public void setTextHiddenElement(final WebElement element, final String str) {
        element.sendKeys(str);
    }

    public void sleep(final int seconds) {
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
