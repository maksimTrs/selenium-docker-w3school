package testsuite;

import com.demo.constants.PageConstants;
import com.demo.pages.SqlSelectPage;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Map;

import static com.demo.constants.PageConstants.ADDRESS_ROVELLI;
import static com.demo.constants.PageConstants.CONTACT_ROVELLI;

public class ContactNameAddressTest extends BaseTest {

    @Test
    public void contactAddressTest() {
        driver.get(PageConstants.LAUNCH_URL);
        SqlSelectPage launchPage = new SqlSelectPage(driver);
        launchPage.executeSQLRunButton();
        //launchPage.switchToSQLFrame();

        Assert.assertEquals(launchPage.getTitle(), PageConstants.LAUNCH_PAGE_TITLE);
        Assert.assertEquals(launchPage.getContactAddressText(CONTACT_ROVELLI), ADDRESS_ROVELLI);
    }


    @Test(enabled = false)
    public void contactAddress2Test() {
        driver.get(PageConstants.LAUNCH_URL);
        SqlSelectPage launchPage = new SqlSelectPage(driver);
        launchPage.executeSQLRunButton();
        //launchPage.switchToSQLFrame();

        Map<String, String> contactNameAndAddressMap = launchPage.getContactNameAndAddressMap();
        Assert.assertEquals(contactNameAndAddressMap.get(CONTACT_ROVELLI), ADDRESS_ROVELLI);
    }
}
