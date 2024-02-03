package listeners;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListener implements ITestListener {

    private static final Logger LOG = LogManager.getLogger(TestListener.class);

    @Override
    public void onStart(ITestContext context) {
        LOG.info("-------------------- Test: " + context.getName() + " STARTED ----------------------");
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        LOG.info("-------------------- Test: " + result.getName() + " PASSED ----------------------");
    }

    @Override
    public void onFinish(ITestContext context) {
        LOG.info("-------------------- Test: " + context.getName() + " FINISHED ----------------------");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        LOG.error("-------------------- " + result.getName() + " FAILED ----------------------");
    }
}
