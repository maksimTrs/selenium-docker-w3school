package testsuite.common;

import com.demo.constants.Constants;
import io.qameta.allure.Attachment;
import listeners.TestListener;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.*;
import utils.WebDriverFactory;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

import static com.demo.constants.Constants.REMOTE_URL;

@Listeners(TestListener.class)
public abstract class BaseTest {

    private static final Logger LOG = LogManager.getLogger(BaseTest.class);
  //  private static final ThreadLocal<RemoteWebDriver> remoteThreadLocalDriver = new ThreadLocal<>();
    protected WebDriver driver;


    @Parameters({"BROWSER"})  // "CHROME_REMOTE" | "CHROME"
    @BeforeMethod
    public void beforeTest(@Optional("CHROME") String browser, ITestContext testContext) {

        //driver = initializeDriver(browser);
        driver = WebDriverFactory.getDriver(browser);
        testContext.setAttribute("driver", driver);

        LOG.info("###################   Browser " + browser + " is started   ###################");
    }

    @AfterMethod(alwaysRun = true)
    public void afterTest(ITestResult iTestResult) {
        if (!iTestResult.isSuccess()) {
            takeScreenShot();
            LOG.info("###################   Was added  screenshot for the test: "
                    + iTestResult.getTestName().toUpperCase() + " ###################");
        }
        driver.quit();
      //  remoteThreadLocalDriver.remove();
    }


/*    public WebDriver initializeDriver(String browser) {

        if (Constants.CHROME_BROWSER.equalsIgnoreCase(browser)) {

            ChromeOptions options = new ChromeOptions();
            options.setPageLoadStrategy(PageLoadStrategy.EAGER);
            options.addArguments("--incognito");
            options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});
            //options.addArguments("--headless","--whitelisted-ips","--no-sandbox","--disable-extensions");
            driver = new ChromeDriver(options);

        } else if (Constants.CHROME_REMOTE_BROWSER.equalsIgnoreCase(browser)) {

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
        }
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(3000));
        driver.manage().timeouts().scriptTimeout(Duration.ofMillis(1000));

        return driver;
    }*/


    @Attachment(value = "Screenshot", type = "image/png")
    public byte[] takeScreenShot() {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }

}
