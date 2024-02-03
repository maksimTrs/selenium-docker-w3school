package testsuite.common;

import com.demo.constants.Constants;
import io.qameta.allure.Attachment;
import listeners.TestListener;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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


import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.*;
import java.time.Duration;

import static com.demo.constants.Constants.REMOTE_URL;

@Listeners(TestListener.class)
public abstract class BaseTest {
    private static final Logger LOG = LogManager.getLogger(BaseTest.class);

    private static ThreadLocal<RemoteWebDriver> remoteThreadLocalDriver = new ThreadLocal<>();
    protected WebDriver driver;


    @BeforeSuite
    public static void executePreConditions() {

        try {
            FileUtils.deleteDirectory(new File("./allure-results"));
            LOG.info("###################   Folder <allure>  was  deleted   ###################");
        } catch (NoSuchFileException | DirectoryNotEmptyException x) {
            System.err.format(x.getMessage());
        } catch (IOException ix) {
            ix.printStackTrace();
        }
    }

    @Parameters({"BROWSER"})
    @BeforeMethod
    public void beforeTest(@Optional("CHROME_REMOTE") String browser, ITestContext testContext) {

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

    public WebDriver initializeDriver(String browser) {        //	chrome_remote  | chrome

        System.out.println("###################   " + browser + "   ###################");

        if (Constants.CHROME_BROWSER.equals(browser)) {

            ChromeOptions options = new ChromeOptions();
            options.setPageLoadStrategy(PageLoadStrategy.EAGER);
            options.addArguments("--incognito");
            options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});
            //options.addArguments("--headless","--whitelisted-ips","--no-sandbox","--disable-extensions");
            driver = new ChromeDriver(options);

        } else if (Constants.CHROME_REMOTE_BROWSER.equals(browser)) {

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
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(5000));

        return driver;
    }

    @Attachment(value = "Screenshot", type = "image/png")
    public byte[] takeScreenShot() {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }

}
