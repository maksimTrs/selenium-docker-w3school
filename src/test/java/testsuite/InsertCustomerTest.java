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

public class InsertCustomerTest extends BaseTest {


    @Feature("INSERT SQL NEW CUSTOMER")
    @Story("3. Добавить новую запись в таблицу Customers и проверить, что эта запись добавилась.")
    @Link("https://www.w3schools.com/sql/trysql.asp?filename=trysql_select_all")
    @Test()
    public void insertCustomerTest() {

        String contactName = "John1 Doe1";
        String sqlCustomerQuery = "INSERT INTO Customers (CustomerName, ContactName, Address, City, PostalCode, Country) " +
                "VALUES (\"Bob1 Bob1\", \"%s\", \"000 Main St\", \"Anytown\", \"12345\", \"USA\")";

        driver.get(LAUNCH_URL);
        SqlSelectPage launchPage = new SqlSelectPage(driver);
        launchPage.insertOrDeleteSqlQuery(String.format(sqlCustomerQuery, contactName));
        Map<String, String> contactNameAndAddressMap = launchPage.getContactAndAddressTableData();

        Assert.assertTrue(contactNameAndAddressMap.containsKey(contactName));
        Assert.assertEquals(contactNameAndAddressMap.get(contactName), "000 Main St");


        Allure.addAttachment("Actual Inserted Customer " + contactName + " Address: ",
                contactNameAndAddressMap.get(contactName));
    }
}
