package testsuite;

import com.demo.pages.SqlSelectPage;
import org.testng.Assert;
import org.testng.annotations.Test;
import testsuite.common.BaseTest;

import static com.demo.constants.Constants.LAUNCH_URL;

public class SelectCityResultTest extends BaseTest {

    @Test()
    public void citySQLSearchTest() {

        String sqlCustomerQuery = "Select * FROM Customers where city=\"London\";";

        driver.get(LAUNCH_URL);
        SqlSelectPage launchPage = new SqlSelectPage(driver);
        launchPage.selectSqlQuery(sqlCustomerQuery);
        //launchPage.executeSQLRunButton();
        //launchPage.switchToSQLFrame();


        Assert.assertEquals(launchPage.getTableSize(), 6);

    }
}
