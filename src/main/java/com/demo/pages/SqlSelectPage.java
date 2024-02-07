package com.demo.pages;

import com.demo.pages.common.BasePage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class SqlSelectPage extends BasePage {

    private static final Logger LOG = LogManager.getLogger(BasePage.class);

    private static String tableAddressDataFromContactName = "//div[@id='divResultSQL']//table//th[text()='ContactName']" +
            "/following::tr[td]/td[3][contains(text(), '%s')]/../td[4]";
    private static String jsScriptSQL2 = "window.editor.getDoc().setValue(\"%s\");";
    private static String jsScriptSQL1 = "window.editor.getDoc().setValue('%s');";

    private static String jsScriptInsertCustomer = "w3schoolsNoWebSQLSelectStar('Customers');";
    private static String submitSQLQuery = "w3schoolsSQLSubmit();";


    @FindBy(xpath = "//button[text()='Run SQL »']")
    private WebElement runSQLBtn;
    @FindBy(id = "iframeResultSQL")
    private WebElement resultSQLIframe;
    @FindBy(xpath = "//div[@id='divResultSQL']//table//th[text()='ContactName']/../../tr[td]")
    private List<WebElement> tableRowValuesList;

    @FindBy(css = "div#resultSQL div#divResultSQL > div")
    private WebElement resultSQLQueryInfo;


    public SqlSelectPage(WebDriver argDriver) {
        super(argDriver);
    }


    public void executeSQLRunButton() {
        runSQLBtn.click();
    }

    public int getTableSize() {
        return tableRowValuesList.size();
    }


    public String getContactAddressText(String contactName) {
        String format = getDriver().findElement(By.xpath(String.format(tableAddressDataFromContactName, contactName)))
                .getText();

        LOG.info("####### CONTACT ADDRESS INFO: " + format.trim() + " #######");
        return format.trim();
    }


    public Map<String, String> getContactAndAddressTableData() {

        Map<String, String> collect1 = new LinkedHashMap<>();

        for (WebElement webElement : tableRowValuesList) {
            WebElement contactName = webElement.findElement(By.xpath("td[3]"));
            WebElement address = webElement.findElement(By.xpath("td[4]"));

            collect1.put(contactName.getText(), address.getText());
        }
        LOG.info("####### COLLECTION DATA: " + collect1 + " #######");
        LOG.info("####### COLLECTION SIZE: " + collect1.size() + " #######");

        return collect1;
    }


    public void selectSqlQuery(String sqlCustomerQuery) {
        ((JavascriptExecutor) getDriver()).executeScript(String.format(jsScriptSQL2, sqlCustomerQuery));
        ((JavascriptExecutor) getDriver()).executeScript(submitSQLQuery);
    }

    private void submitSqlQuery() {
        ((JavascriptExecutor) getDriver()).executeScript(jsScriptInsertCustomer);
        ((JavascriptExecutor) getDriver()).executeScript(submitSQLQuery);
    }


    // TODO: <refactoring plan: optimize JS methods, avoid duplication logic >
    public void insertOrDeleteSqlQuery(String sqlCustomerQuery) {
        ((JavascriptExecutor) getDriver()).executeScript(String.format(jsScriptSQL1, sqlCustomerQuery));
        ((JavascriptExecutor) getDriver()).executeScript(submitSQLQuery);

        // TODO: <refactoring plan: improve logic to avoid Thread.sleep() -> add a wait  logic  for the upcoming  element! >
        try {
            Thread.sleep(900);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        //getWait().until(ExpectedConditions.visibilityOf(resultSQLQueryInfo));
        submitSqlQuery();
    }


    // TODO: <refactoring plan: optimize JS methods, avoid duplication logic >
    public void updateSqlQuery(String sqlCustomerQuery) {
        selectSqlQuery(sqlCustomerQuery);

        // TODO: <refactoring plan: improve logic to avoid Thread.sleep() -> add a wait  logic  for the upcoming  element! >
        try {
            Thread.sleep(900);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        //  getWait().until(ExpectedConditions.visibilityOf(resultSQLQueryInfo));
        submitSqlQuery();
    }
}
