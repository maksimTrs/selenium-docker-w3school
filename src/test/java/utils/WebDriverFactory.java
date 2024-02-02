package utils;

import com.demo.constants.Constants;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class WebDriverFactory {


    private static final ThreadLocal<WebDriver> threadLocalDriver = new ThreadLocal<>();

    public static WebDriver getDriver(String browser) {
        if (threadLocalDriver.get() == null) {
            threadLocalDriver.set(initializeDriver(browser));
        }
        return threadLocalDriver.get();
    }

    private static WebDriver initializeDriver(String browser) {
        WebDriver driver;

        if (Constants.CHROME_BROWSER.equalsIgnoreCase(browser)) {
            driver = createChromeDriver();
        } else if (Constants.CHROME_REMOTE_BROWSER.equalsIgnoreCase(browser)) {
            driver = createRemoteChromeDriver();
        } else {
            throw new IllegalArgumentException("Invalid browser: " + browser);
        }

        return driver;
    }

    private static WebDriver createChromeDriver() {
        ChromeOptions options = initChromeOptions();
        return new ChromeDriver(options);
    }

    private static WebDriver createRemoteChromeDriver() {
        try {
            // Create capabilities (you may customize this based on your requirements)
            ChromeOptions options = initChromeOptions();

            // Pass capabilities to the RemoteWebDriver constructor
            return new RemoteWebDriver(new URL(Constants.REMOTE_URL), options);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    private static ChromeOptions initChromeOptions() {
        ChromeOptions options = new ChromeOptions();
        options.setPageLoadStrategy(PageLoadStrategy.EAGER);
        options.addArguments("--incognito");
        options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});

        return options;
    }

}