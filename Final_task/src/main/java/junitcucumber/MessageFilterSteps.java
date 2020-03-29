package junitcucumber;

import core.browser.SingletonChromeDriver;
import core.configuration.Configuration;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.AfterClass;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import page.InboxPage;
import page.LoginPage;

public class MessageFilterSteps {
    private WebDriver driver;
    private LoginPage loginPage;
    private InboxPage inboxPage;
    private int timeout = 15;


    public MessageFilterSteps() {
        driver = SingletonChromeDriver.getInstance();
        loginPage = new LoginPage(driver);
        inboxPage = new InboxPage(driver);
    }

    @When("^i write new test message$")
    public void writeTestMessage() {
        String testEmailAddress = "Aleksand_or92@mail.ru";
        inboxPage.openWriteMessageForm();
        inboxPage.declareDestinationAddresses(testEmailAddress);
        inboxPage.writeMessage("test_message");
        inboxPage.sendMessage();
        inboxPage.openInboxPage();
    }

    @When("^i enter into inbox page$")
    public void enterIntoInboxPage() {
        inboxPage.openInboxPage();
    }

    @And("^i set a flag on message$")
    public void setFlagOnMessage() {
        inboxPage.clickToFlagOnMassage();
    }

    @And("^i open message filter$")
    public void deployMessageFilter() {
        inboxPage.openMessageFilter();
    }

    @And("^i check unread messages$")
    public void chooseUnreadMessage() {
        inboxPage.filterUnreadMessage();
    }

    @And("^i check messages with flag$")
    public void chooseMessagesWithFlag() {
        inboxPage.filterMessageWithFlag();
    }

    @Then("^i see only unread messages$")
    public void checkUnreadMessages() {
        wait(Configuration.getUnreadMessagesPageUrl());
        Assert.assertEquals(inboxPage.amountOfMessages(), inboxPage.amountOfUnreadMessages());
    }

    @Then("^i see only messages with flag$")
    public void checkMessagesWithFlag() {
        wait(Configuration.getMessagesWithFlagPageUrl());
        Assert.assertEquals(inboxPage.amountOfMessages(), inboxPage.amountOfMessagesWithFlag());
    }

    public void wait(String url) {
        boolean explicitWaitManePage = (new WebDriverWait(driver, timeout))
                .until(ExpectedConditions.urlContains(url));
    }

    @AfterClass
    public void cleanUp() {
        driver.close();
    }
}
