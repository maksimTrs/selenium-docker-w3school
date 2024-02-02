package testsuite;

import com.demo.constants.Constants;
import com.demo.pages.SqlSelectPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class InsertCustomerTest extends BaseTest {

    @Test()
    public void insertCustomerTest() {
        driver.get(Constants.LAUNCH_URL);
        SqlSelectPage launchPage = new SqlSelectPage(driver);

        launchPage.executeJavaScriptToCreateNewCustomer("Bob1 Bob1", "John1 Doe1",
                "1234 Main St", "Anytown", "12345", "USA");


        //launchPage.switchToSQLFrame();

	/*	Map<String, String> contactNameAndAddressMap = launchPage.getContactNameAndAddressMap();
		System.out.println(contactNameAndAddressMap);

		Assert.assertTrue(contactNameAndAddressMap.containsKey("John1 Doe1"));*/

        Assert.assertEquals(launchPage.getContactAddressText("John1 Doe1"), "1234 Main St");
    }
}
