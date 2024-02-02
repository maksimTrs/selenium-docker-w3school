package testsuite;

import com.demo.constants.PageConstants;
import io.qameta.allure.Attachment;
import listeners.TestListener;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

import static com.demo.constants.PageConstants.REMOTE_URL;

@Listeners(TestListener.class)
public abstract class BaseTest {

    private static ThreadLocal<RemoteWebDriver> remoteThreadLocalDriver = new ThreadLocal<>();
    protected WebDriver driver;

    public WebDriver initializeDriver(String browser) {        //	chrome_remote  | chrome

        System.out.println("###################   " + browser + "   ###################");

        if (PageConstants.CHROME_BROWSER.equals(browser)) {

            ChromeOptions options = new ChromeOptions();
            options.setPageLoadStrategy(PageLoadStrategy.EAGER);
            options.addArguments("--incognito");
            options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});
            //options.addArguments("--headless","--whitelisted-ips","--no-sandbox","--disable-extensions");
            driver = new ChromeDriver(options);

        } else if (PageConstants.CHROME_REMOTE_BROWSER.equals(browser)) {

            ChromeOptions options = new ChromeOptions();
            options.setPageLoadStrategy(PageLoadStrategy.EAGER);
            options.addArguments("--incognito");
            options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});

            try {
                remoteThreadLocalDriver.set(new RemoteWebDriver(new URL(REMOTE_URL), options));
                driver = remoteThreadLocalDriver.get();
            } catch (MalformedURLException e) {
                throw new RuntimeException(e);
            }

        } else if (PageConstants.FIREFOX_BROWSER.equals(browser)) {

            FirefoxOptions options = new FirefoxOptions();
            options.setPageLoadStrategy(PageLoadStrategy.EAGER);
            //options.addArguments("--headless","--whitelisted-ips","--no-sandbox","--disable-extensions");
            driver = new FirefoxDriver(options);

        }

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(5000));

        return driver;
    }

    @Parameters({"browser"})
    @BeforeMethod
    public void beforeTest(@Optional("chrome_remote") String browser, ITestContext testContext) {

        driver = initializeDriver(browser);
        testContext.setAttribute("driver", driver);
    }

    @AfterMethod(alwaysRun = true)
    public void afterTest(ITestResult iTestResult) {
        if (!iTestResult.isSuccess()) {
            takeScreenShot();
        }
        driver.quit();
        remoteThreadLocalDriver.remove();
    }


    @Attachment(value = "Screenshot", type = "image/png")
    public byte[] takeScreenShot() {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }

}
