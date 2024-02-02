package testsuite;

import com.demo.constants.Constants;
import com.demo.pages.SqlSelectPage;
import org.testng.Assert;
import org.testng.annotations.Test;

import static com.demo.constants.Constants.LAUNCH_URL;

public class CityQueryTest extends BaseTest {

    @Test()
    public void citySQLSearchTest() {
        driver.get(LAUNCH_URL);
        SqlSelectPage launchPage = new SqlSelectPage(driver);

        launchPage.executeJavaScriptSearchCustomerCity("London");
        //launchPage.executeSQLRunButton();
        //launchPage.switchToSQLFrame();

        int londonRowCount = launchPage.getSQLRunTableSizeResult();
        Assert.assertEquals(londonRowCount, 6);

    }
}
