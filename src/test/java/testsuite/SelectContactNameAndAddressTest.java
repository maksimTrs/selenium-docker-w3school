package testsuite;

import com.demo.constants.Constants;
import com.demo.pages.SqlSelectPage;
import org.testng.Assert;
import org.testng.annotations.Test;
import testsuite.common.BaseTest;

import java.util.Map;


public class SelectContactNameAndAddressTest extends BaseTest {

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
    public void contactAddressTest() {
        String addressRovelli = "Via Ludovico il Moro 22";
        String contactRovelli = "Giovanni Rovelli";

        driver.get(Constants.LAUNCH_URL);
        SqlSelectPage launchPage = new SqlSelectPage(driver);
        launchPage.executeSQLRunButton();
        //launchPage.switchToSQLFrame();
        Map<String, String> contactNameAndAddressMap = launchPage.getContactAndAddressTableData();

        Assert.assertEquals(contactNameAndAddressMap.get(contactRovelli), addressRovelli);
    }
}
