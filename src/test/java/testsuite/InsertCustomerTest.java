package testsuite;

import com.demo.constants.Constants;
import com.demo.pages.SqlSelectPage;
import org.testng.Assert;
import org.testng.annotations.Test;
import testsuite.common.BaseTest;

import java.util.Map;

public class InsertCustomerTest extends BaseTest {

    @Test()
    public void insertCustomerTest() {

        String sqlCustomerQuery = "INSERT INTO Customers (CustomerName, ContactName, Address, City, PostalCode, Country) " +
                "VALUES (\"Bob1 Bob1\", \"John1 Doe1\", \"000 Main St\", \"Anytown\", \"12345\", \"USA\");";


        driver.get(Constants.LAUNCH_URL);
        SqlSelectPage launchPage = new SqlSelectPage(driver);
        launchPage.insertOrUpdateSqlQuery(sqlCustomerQuery);
        //launchPage.switchToSQLFrame();
		Map<String, String> contactNameAndAddressMap = launchPage.getContactAndAddressTableData();


		Assert.assertTrue(contactNameAndAddressMap.containsKey("John1 Doe1"));
		Assert.assertEquals(contactNameAndAddressMap.get("John1 Doe1"), "000 Main St");
       // Assert.assertEquals(launchPage.getContactAddressText("John1 Doe1"), "1234 Main St");
    }
}
