package junitcucumber;

import core.browser.SingletonChromeDriver;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import model.DBReader;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import page.InboxPage;
import page.LoginPage;
import core.configuration.Configuration;

public class LoginSteps {
    private String emailLogin;
    private String emailPassword;
    private WebDriver driver;
    private LoginPage loginPage;
    private InboxPage inboxPage;
    private DBReader dbReader;
    private int userIndex = 0;

    public LoginSteps() {
        driver = SingletonChromeDriver.getInstance();
        loginPage = new LoginPage(driver);
        inboxPage = new InboxPage(driver);
    }

    @Given("^i open main application page$")
    public void openMainApplicationPage() {
        dbReader = new DBReader();
        driver.manage().window().maximize();
        driver.get(Configuration.getMainApplicationPageUrl());
    }

    @When("^i enter right login and wrong password$")
    public void enterRightLoginAndWrongPassword() {
        getLoginAndPassword(Configuration.getRightLoginWrongPassword());
        loginPage.enterLogin(emailLogin);
        loginPage.enterPassword(emailPassword);
    }

    @When("^i enter right login and password$")
    public void enterRightLoginPassword() {
        getLoginAndPassword(Configuration.getRightLoginAndPassword());
        loginPage.login(emailLogin, emailPassword);
    }

    @When("^i enter wrong login$")
    public void enterWrongLoginAndRightPassword() {
        getLoginAndPassword(Configuration.getWrongLoginRightPassword());
        loginPage.enterLogin(emailLogin);
    }

    @Then("^i see mailbox error$")
    public void checkWrongLoginOrPasswordMessage() {
        WebElement element = loginPage.getMailboxError();
        loginPage.wait(element);
        Assert.assertTrue(element.isEnabled());
    }

    @Then("^i see inbox page$")
    public void checkInboxPage() {
        WebElement element = inboxPage.getMessageFilter();
        loginPage.wait(element);
        Assert.assertTrue(element.isEnabled());
    }

    public void getLoginAndPassword(String note) {
        String request = String.format("SELECT * FROM auto_user.user WHERE note='%s'", note);
        emailLogin = dbReader.getDBUsers(request).get(userIndex).getLogin();
        emailPassword = dbReader.getDBUsers(request).get(userIndex).getPassword();
    }
}
