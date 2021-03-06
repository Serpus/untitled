package baseTest;

import org.junit.Rule;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;

public class BaseTest {
    private String ggrUrl = "http://localhost:4445";
    private WebDriver driver;
    private Map<String, Object> prefs = new HashMap<String, Object>();
    @Rule
    public TestWatcher watcher = new TestWatcher() {
        @Override
        protected void succeeded(Description description) {
            super.succeeded(description);
        }

        @Override
        protected void starting(Description description) {
            super.starting(description);
            prefs.put("download.default_directory", System.getProperty("user.dir"));
            ChromeOptions option = new ChromeOptions();
            option.setExperimentalOption("prefs", prefs);
            System.out.println(option.getExperimentalOption("prefs"));
            DesiredCapabilities desire = DesiredCapabilities.chrome();
            desire.setCapability(ChromeOptions.CAPABILITY, option);
            URL url = null;
            try {
                url = new URL(ggrUrl +  "/wd/hub");
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
            driver = new RemoteWebDriver(url, desire);
        }

        @Override
        protected void finished(Description description) {
            super.finished(description);
            driver.quit();
            if (driver != null) driver.quit();
        }

        @Override
        protected void failed(final Throwable e, final Description description) {
        }
    };

    public void openBrowser(final String url) {
        getDriver().get(url);
        getDriver().manage().window().maximize();
    }

    public WebDriver getDriver() {
        return driver;
    }
}
