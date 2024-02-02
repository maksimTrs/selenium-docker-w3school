package com.demo.pages;

import com.demo.pages.common.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.time.Duration;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class SqlSelectPage extends BasePage {

    // private static String tableAddressDataFromContactName = "//body/table//th[text()='ContactName']/following::tr[td]/td[3][contains(text(), '%s')]/../td[4]";
    private static String tableAddressDataFromContactName = "//div[@id='divResultSQL']//table//th[text()='ContactName']/following::tr[td]/td[3][contains(text(), '%s')]/../td[4]";
    // private static String jsScriptTextareaCodeSQLCity = "document.querySelector('#textareaCodeSQL').value = 'Select * FROM Customers where city=\"%s\";'";
    private static String jsScriptTextareaCodeSQLCity = "window.editor.getDoc().setValue('Select * FROM Customers where city=\"%s\";');";

    private static String jsScriptBtCode = "document.querySelector('#bt').value = '1'";
    private static String submitSQLQuery = "w3schoolsNoWebSQLSubmit();";
    private static String jsScriptInsertCustomer4 = "window.editor.getDoc().setValue('INSERT INTO Customers (CustomerName, ContactName, Address, City, PostalCode, Country) VALUES (\\'%s\\', \\'%s\\', \\'%s\\', \\'%s\\', \\'%s\\', \\'%s\\');');";

    private static String jsScriptInsertCustomer2 = "document.getElementById('textareaCodeSQL').value = 'INSERT INTO Customers (CustomerName, ContactName, Address, City, PostalCode, Country) VALUES (\"%s\", \"%s\", \"%s\", \"%s\", \"%s\", \"%s\");'";
    private static String jsScriptInsertCustomer = "w3schoolsNoWebSQLSelectStar('Customers');";
    private static String submitSQLQuery2 = "w3schoolsSQLSubmit();";

    private static String jsScriptUpdateCustomer = "window.editor.getDoc().setValue('UPDATE Customers SET CustomerName=\\'%s\\', ContactName=\\'%s\\', Address=\\'%s\\', City=\\'%s\\', PostalCode=\\'%s\\', Country=\\'%s\\' WHERE CustomerID=\\'%s\\';');";


    @FindBy(xpath = "//button[text()='Run SQL »']")
    private WebElement runSQLBtn;


    @FindBy(id = "iframeResultSQL")
    private WebElement resultSQLIframe;

    @FindBy(xpath = "//div[@id='divResultSQL']//table//th[text()='ContactName']/../../tr[td]")
    private List<WebElement> tableRowValuesList;


    public SqlSelectPage(WebDriver argDriver) {
        super(argDriver);
    }


    public String getContactAddressText(String contactName) {
        // getDriver().switchTo().frame(resultSQLIframe);
        String format = getDriver().findElement(By.xpath(String.format(tableAddressDataFromContactName, contactName)))
                .getText();
        // getDriver().switchTo().defaultContent();

        System.out.println("####### " + format.trim() + " #######");
        return format.trim();
    }

    public void executeSQLRunButton() {
        runSQLBtn.click();
    }

    public int getSQLRunTableSizeResult() {
        return tableRowValuesList.size();
    }

    public void switchToSQLFrame() {
        getDriver().switchTo().frame(resultSQLIframe);
    }


    public Map<String, String> getContactNameAndAddressMap() {

        Map<String, String> collect1 = new LinkedHashMap<>();

        for (WebElement webElement : tableRowValuesList) {
            WebElement contactName = webElement.findElement(By.xpath("td[3]"));
            WebElement address = webElement.findElement(By.xpath("td[4]"));

            collect1.put(contactName.getText(), address.getText());
        }

        System.out.println(collect1);

     /*   Map<String, String> collect = tableRowValuesList.stream()
                .map(WebElement::getText)
                .peek(System.out::println)
                //.map(String::trim)
                .map(s -> s.split(" "))
                .peek(str -> System.out.println(Arrays.toString(str)))
                .collect(Collectors.toMap(arr -> arr[2], arr -> arr[3]));*/

        System.out.println("####### " + collect1.size() + " #######");
        return collect1;
    }


    public void executeJavaScriptSearchCustomerCity(String city) {
        getDriver().manage().timeouts().scriptTimeout(Duration.ofMillis(600));
        ((JavascriptExecutor) getDriver()).executeScript(String.format(jsScriptTextareaCodeSQLCity, city));
        ((JavascriptExecutor) getDriver()).executeScript(submitSQLQuery2);
        //  ((JavascriptExecutor) getDriver()).executeScript(jsScriptBtCode);
        //  ((JavascriptExecutor) getDriver()).executeScript(submitSQLQuery);
    }

    public void executeJavaScriptToCreateNewCustomer(String customerName, String contactName, String address,
                                                     String city, String postalCode, String country) {

        // getDriver().manage().timeouts().scriptTimeout(Duration.ofMillis(600));
        ((JavascriptExecutor) getDriver()).executeScript(String.format(jsScriptInsertCustomer4, customerName,
                contactName,
                address,
                city,
                postalCode,
                country));

        ((JavascriptExecutor) getDriver()).executeScript(submitSQLQuery2);

        // TODO improve logic to avoid Thread.sleep
        try {
            Thread.sleep(600);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        ((JavascriptExecutor) getDriver()).executeScript(jsScriptInsertCustomer);
        ((JavascriptExecutor) getDriver()).executeScript(submitSQLQuery2);
    }

    public void executeJavaScriptToUpdateCustomer(String customerName, String contactName, String address,
                                                  String city, String postalCode, String country, String customerID) {

        //getDriver().manage().timeouts().scriptTimeout(Duration.ofMillis(700));

        ((JavascriptExecutor) getDriver()).executeScript(String.format(jsScriptUpdateCustomer, customerName,
                contactName,
                address,
                city,
                postalCode,
                country,
                customerID));

        ((JavascriptExecutor) getDriver()).executeScript(submitSQLQuery2);

        // TODO improve logic to avoid Thread.sleep
        try {
            Thread.sleep(600);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        ((JavascriptExecutor) getDriver()).executeScript(jsScriptInsertCustomer);
        ((JavascriptExecutor) getDriver()).executeScript(submitSQLQuery2);
    }
}
