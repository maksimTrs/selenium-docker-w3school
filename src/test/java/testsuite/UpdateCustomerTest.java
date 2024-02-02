package testsuite;

import com.demo.constants.Constants;
import com.demo.pages.SqlSelectPage;
import org.testng.Assert;
import org.testng.annotations.Test;
import testsuite.common.BaseTest;

public class UpdateCustomerTest extends BaseTest {

    @Test()
    public void updateCustomerTest() {

        String customerId = "1";
        String sqlCustomerQuery = "UPDATE Customers SET " +
                "CustomerName = \"Bob2 Bob2\", " +
                "ContactName = \"John222 Doe222\", " +
                "Address = \"Main St. 777\", " +
                "City = \"Anytown\", " +
                "PostalCode = \"12345\", " +
                "Country = \"China\" " +
                "WHERE CustomerID = '%s';";

        driver.get(Constants.LAUNCH_URL);
        SqlSelectPage launchPage = new SqlSelectPage(driver);
        launchPage.selectSqlQuery(String.format(sqlCustomerQuery, customerId));


        Assert.assertEquals(launchPage.getContactAddressText("John222 Doe222"), "Main St. 777");
    }
}
