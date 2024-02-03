package testsuite;

import com.demo.pages.SqlSelectPage;
import io.qameta.allure.Allure;
import io.qameta.allure.Feature;
import io.qameta.allure.Link;
import io.qameta.allure.Story;
import org.testng.Assert;
import org.testng.annotations.Test;
import testsuite.common.BaseTest;

import static com.demo.constants.Constants.LAUNCH_URL;

public class SelectCityResultTest extends BaseTest {

    @Feature("SELECT SQL WITH CITY")
    @Story("Вывести только те строки таблицы Customers, где city='London'. Проверить, что в таблице ровно 6 записей.")
    @Link("https://www.w3schools.com/sql/trysql.asp?filename=trysql_select_all")
    @Test()
    public void citySQLSearchTest() {

        String city = "London";
        String sqlCustomerQuery = "Select * FROM Customers where city='%s';";

        driver.get(LAUNCH_URL);
        SqlSelectPage launchPage = new SqlSelectPage(driver);
        launchPage.selectSqlQuery(String.format(sqlCustomerQuery, city));

        Assert.assertEquals(launchPage.getTableSize(), 6);


        Allure.addAttachment("Actual result count for the " + city +  " query: ",
                String.valueOf(launchPage.getTableSize()));
    }
}
