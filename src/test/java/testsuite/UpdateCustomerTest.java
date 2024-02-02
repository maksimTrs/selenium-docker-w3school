package testsuite;

import com.demo.constants.Constants;
import com.demo.pages.SqlSelectPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class UpdateCustomerTest extends BaseTest {

    @Test()
    public void updateCustomerTest() {
        driver.get(Constants.LAUNCH_URL);
        SqlSelectPage launchPage = new SqlSelectPage(driver);

        launchPage.executeJavaScriptToUpdateCustomer("Bob2 Bob2", "John2 Doe2",
                "Main St. 123", "Anytown", "12345", "China", "1");


        Assert.assertEquals(launchPage.getContactAddressText("John2 Doe2"), "Main St. 123");
    }
}
