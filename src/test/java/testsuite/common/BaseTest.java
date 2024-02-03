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
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.*;
import utils.WebDriverFactory;

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

    @Parameters({"BROWSER"})  // "CHROME_REMOTE" | "CHROME"
    @BeforeMethod
    public void beforeTest(@Optional("CHROME_REMOTE") String xmlBrowser, ITestContext testContext) {

        // Check if the system property -DBROWSER is provided
        String systemBrowser = System.getProperty("BROWSER");
        // Determine the browser based on priority
        String browser = (systemBrowser != null && !systemBrowser.isEmpty()) ? systemBrowser : xmlBrowser;

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
                    + iTestResult.getTestName() + " ###################");
        }
        driver.quit();
    }

    @Attachment(value = "Screenshot", type = "image/png")
    public byte[] takeScreenShot() {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }

}
