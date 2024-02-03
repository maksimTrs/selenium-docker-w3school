package testsuite;

import com.demo.constants.Constants;
import com.demo.pages.SqlSelectPage;
import io.qameta.allure.Allure;
import io.qameta.allure.Feature;
import io.qameta.allure.Link;
import io.qameta.allure.Story;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import testsuite.common.BaseTest;

import java.util.Map;

public class DeleteCustomerTest extends BaseTest {

    private static final String CONTRACT_NAME = "John_Del Doe_Del";


    @BeforeMethod
    private   void insertCustomerForDeletionTest() {
        String sqlCustomerQuery = "INSERT INTO Customers (CustomerName, ContactName, Address, City, PostalCode, Country) " +
                "VALUES (\"Bob_Del Mol_Del\", \"%s\", \"007 DEL Street\", \"Paradise\", \"9999\", \"USA\")";

        driver.get(Constants.LAUNCH_URL);
        SqlSelectPage launchPage = new SqlSelectPage(driver);
        launchPage.insertOrDeleteSqlQuery(String.format(sqlCustomerQuery, CONTRACT_NAME));

        Assert.assertEquals(launchPage.getContactAddressText(CONTRACT_NAME), "007 DEL Street");
    }


    @Feature("DELETE SQL  CUSTOMER")
    @Story("5. Придумать собственный автотест и реализовать -> выбран delete сценарий для проверки логики CRUD SQL")
    @Link("https://www.w3schools.com/sql/trysql.asp?filename=trysql_select_all")
    @Test()
    public void deleteCustomerTest() {

        String sqlCustomerQuery = "DELETE FROM Customers WHERE ContactName=\"%s\"";

        //driver.get(Constants.LAUNCH_URL);
        SqlSelectPage launchPage = new SqlSelectPage(driver);
        launchPage.insertOrDeleteSqlQuery(String.format(sqlCustomerQuery, CONTRACT_NAME));

        Map<String, String> contactNameAndAddressMap = launchPage.getContactAndAddressTableData();

		Assert.assertFalse(contactNameAndAddressMap.containsKey(CONTRACT_NAME));


        Allure.addAttachment("Actual Deleted Customer " + CONTRACT_NAME + " has and address: ",
                contactNameAndAddressMap.getOrDefault(CONTRACT_NAME, "NULL -> DELETED OBJECT"));
    }
}
