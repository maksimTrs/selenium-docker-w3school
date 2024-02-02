package testsuite;

import com.demo.constants.PageConstants;
import com.demo.pages.SqlSelectPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CityQueryTest extends BaseTest {

    @Test()
    public void citySQLSearchTest() {
        driver.get(PageConstants.LAUNCH_URL);
        SqlSelectPage launchPage = new SqlSelectPage(driver);

        launchPage.executeJavaScriptSearchCustomerCity("London");
        //launchPage.executeSQLRunButton();
        //launchPage.switchToSQLFrame();

        int londonRowCount = launchPage.getSQLRunTableSizeResult();
        Assert.assertEquals(londonRowCount, 6);

    }
}
