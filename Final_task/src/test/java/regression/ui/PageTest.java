package regression.ui;

import core.browser.DriverManager;
import core.browser.DriverManagerFactory;
import core.configuration.Configuration;
import model.DBReader;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.apache.log4j.Logger;
import page.InboxPage;
import page.LoginPage;

import java.util.ArrayList;
import java.util.List;

public class PageTest {
    private static Logger logger = Logger.getLogger(PageTest.class);
    private WebDriver driver;
    private InboxPage inboxPage;

    @BeforeTest
    public void beforeTest() {

        DriverManager driverManager = DriverManagerFactory.getManager(Configuration.getDriverType());
        driver = driverManager.getDriver();
        logger.info("create WebDriver");
        driver.manage().window().maximize();
        driver.get(Configuration.getMainApplicationPageUrl());
        LoginPage loginPage = new LoginPage(driver);
        inboxPage = new InboxPage(driver);
        DBReader dbReader = new DBReader();
        String request = String.format("SELECT * FROM auto_user.user WHERE note='%s'",
                Configuration.getRightLoginAndPassword());
        int userIndex = 0;
        String emailLogin = dbReader.getDBUsers(request).get(userIndex).getLogin();
        String emailPassword = dbReader.getDBUsers(request).get(userIndex).getPassword();
        loginPage.login(emailLogin, emailPassword);
    }

    @AfterTest
    public void closeDriver() {
        logger.info("close WebDriver");
        driver.close();
    }

    @Test
    public void deleteMail() {
        String fistMessage = "//a[contains(@class,\"llct js-letter\")]";
        String messageAttribute = "data-uidl-id";
        String messageId = getElementAttribute(fistMessage, messageAttribute);
        inboxPage.checkSomeMessages(1);
        inboxPage.deleteMessage();
        logger.info("delete message");
        inboxPage.openBasket();
        logger.info("open basket");
        List<String> attributesOfMessagesInBasket = getListOfTitle(fistMessage, messageAttribute);
        Assert.assertTrue(attributesOfMessagesInBasket.contains(messageId));
    }

    @Test
    public void findMail() {
        String fistMessage = "//a[contains(@class,\"llct js-letter\")]//span[@class=\"ll-crpt\"]";
        String messageAttribute = "title";
        String messageTitle = getElementAttribute(fistMessage, messageAttribute);
        inboxPage.findMessage(messageTitle);
        List<String> attributesOfMessagesAfterFind = getListOfTitle(fistMessage, messageAttribute);
        Assert.assertTrue(attributesOfMessagesAfterFind.contains(messageTitle));
    }

    public String getElementAttribute(String firstMessage, String messageAttribute) {
        WebElement messageOnTheManePage = driver.findElement(By.xpath(firstMessage));
        return messageOnTheManePage.getAttribute(messageAttribute);
    }

    public List<String> getListOfTitle(String fistMessage, String messageAttribute) {
        List<WebElement> messages = driver.findElements(By.xpath(fistMessage));
        List<String> attributes = new ArrayList<String>();
        for (WebElement message : messages) {
            attributes.add(message.getAttribute(messageAttribute));
        }
        return attributes;
    }
}
