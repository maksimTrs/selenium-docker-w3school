package testsuite;

import com.demo.constants.Constants;
import com.demo.pages.SqlSelectPage;
import io.qameta.allure.Allure;
import io.qameta.allure.Feature;
import io.qameta.allure.Link;
import io.qameta.allure.Story;
import org.testng.Assert;
import org.testng.annotations.Test;
import testsuite.common.BaseTest;

import java.util.Map;

import static com.demo.constants.Constants.LAUNCH_URL;

public class UpdateCustomerTest extends BaseTest {


    @Feature("UPDATE SQL FOR CUSTOMER")
    @Story("4. Обновить все поля (кроме CustomerID) в любой записи таблицы Customers и проверить, что изменения записались в базу.")
    @Link("https://www.w3schools.com/sql/trysql.asp?filename=trysql_select_all")
    @Test()
    public void updateCustomerTest() {

        String customerId = "1";
        String contactName = "John222 Doe222";
        String sqlCustomerQuery = "UPDATE Customers SET " +
                "CustomerName = 'Bob222 Bob222', " +
                "ContactName = '%s', " +
                "Address = 'Main St. 7777', " +
                "City = 'Anytown1', " +
                "PostalCode = '12345', " +
                "Country = 'China' " +
                "WHERE CustomerID = '%s'";


        driver.get(LAUNCH_URL);
        SqlSelectPage launchPage = new SqlSelectPage(driver);
        launchPage.updateSqlQuery(String.format(sqlCustomerQuery, contactName, customerId));
        Map<String, String> contactNameAndAddressMap = launchPage.getContactAndAddressTableData();

        Assert.assertTrue(contactNameAndAddressMap.containsKey(contactName));
        Assert.assertEquals(contactNameAndAddressMap.get(contactName), "Main St. 7777");


        Allure.addAttachment("Actual Updated  Customer ID " + customerId + " has address: ",
                contactNameAndAddressMap.get(contactName));
    }
}
