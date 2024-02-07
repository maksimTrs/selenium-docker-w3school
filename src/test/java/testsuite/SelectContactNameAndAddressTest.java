package testsuite;

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


public class SelectContactNameAndAddressTest extends BaseTest {

    @Feature("SELECT SQL")
    @Story("1. Вывести все строки таблицы Customers и убедиться, что запись с ContactName равной 'Giovanni Rovelli' " +
            "имеет Address = 'Via Ludovico il Moro 22'.")
    @Link("https://www.w3schools.com/sql/trysql.asp?filename=trysql_select_all")
    @Test()
    public void contactAddressTest() {
        String addressRovelli = "Via Ludovico il Moro 22";
        String contactRovelli = "Giovanni Rovelli";

        driver.get(LAUNCH_URL);
        SqlSelectPage launchPage = new SqlSelectPage(driver);
        launchPage.executeSQLRunButton();
        Map<String, String> contactNameAndAddressMap = launchPage.getContactAndAddressTableData();

        Allure.addAttachment("Actual contact " + contactRovelli + " address: ",
                contactNameAndAddressMap.get(contactRovelli));

        Assert.assertEquals(contactNameAndAddressMap.get(contactRovelli), addressRovelli);
    }
}
