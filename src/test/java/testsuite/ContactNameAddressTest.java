package testsuite;

import com.demo.constants.Constants;
import com.demo.pages.SqlSelectPage;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Map;

import static com.demo.constants.Constants.ADDRESS_ROVELLI;
import static com.demo.constants.Constants.CONTACT_ROVELLI;

public class ContactNameAddressTest extends BaseTest {

/*
    @Test
    public void contactAddressTest() {
        driver.get(Constants.LAUNCH_URL);
        SqlSelectPage launchPage = new SqlSelectPage(driver);
        launchPage.executeSQLRunButton();
        //launchPage.switchToSQLFrame();

        Assert.assertEquals(launchPage.getTitle(), Constants.LAUNCH_PAGE_TITLE);
        Assert.assertEquals(launchPage.getContactAddressText(CONTACT_ROVELLI), ADDRESS_ROVELLI);
    }
*/


    @Test()
    public void contactAddress2Test() {
        driver.get(Constants.LAUNCH_URL);
        SqlSelectPage launchPage = new SqlSelectPage(driver);
        launchPage.executeSQLRunButton();
        //launchPage.switchToSQLFrame();

        Assert.assertEquals(launchPage.getTitle(), Constants.LAUNCH_PAGE_TITLE);

        Map<String, String> contactNameAndAddressMap = launchPage.getContactNameAndAddressMap();
        Assert.assertEquals(contactNameAndAddressMap.get(CONTACT_ROVELLI), ADDRESS_ROVELLI);
    }
}
