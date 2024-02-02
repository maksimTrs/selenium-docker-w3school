package listeners;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TestListener implements ITestListener {

    private static final Logger LOG = LogManager.getLogger(TestListener.class);

    @Override
    public void onStart(ITestContext context) {
        LOG.info("-------------------- Test: " + context.getName().toUpperCase()+ " STARTED ----------------------");
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        LOG.info("-------------------- Test: " + result.getName().toUpperCase() + " PASSED ----------------------");
    }

    @Override
    public void onFinish(ITestContext context) {
        LOG.info("-------------------- Test: " + context.getName().toUpperCase()+ " FINISHED ----------------------");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        LOG.error("-------------------- " + result.getName().toUpperCase() + "FAILED ----------------------");
    }
}
