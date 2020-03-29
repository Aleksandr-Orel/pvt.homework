package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import core.configuration.Configuration;

public class LoginPage {
    public final int TIMEOUT = 15;

    @FindBy(id="mailbox:error")
    private WebElement mailboxError;

    @FindBy(id="mailbox:login")
    private WebElement loginField;

    @FindBy(id="mailbox:password")
    private WebElement passwordField;

    @FindBy(xpath = "//input[@class='o-control']")
    private WebElement nextButton;

    private WebDriver driver;
    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void login(String emailLogin, String emailPassword) {
        loginField.clear();
        loginField.sendKeys(emailLogin);
        nextButton.click();
        WebElement explicitWait = (new WebDriverWait(driver, TIMEOUT))
                .until(ExpectedConditions.elementToBeClickable(passwordField));
        passwordField.clear();
        passwordField.sendKeys(emailPassword);
        nextButton.click();
        boolean explicitWaitManePage = (new WebDriverWait(driver, TIMEOUT))
                .until(ExpectedConditions.urlContains(Configuration.getInboxPageUrl()));
    }

    public void enterLogin(String emailLogin) {
        loginField.clear();
        loginField.sendKeys(emailLogin);
        nextButton.click();
    }

    public void enterPassword(String emailPassword) {
        WebElement explicitWait = (new WebDriverWait(driver, TIMEOUT))
                .until(ExpectedConditions.elementToBeClickable(passwordField));
        passwordField.clear();
        passwordField.sendKeys(emailPassword);
        nextButton.click();
    }

    public WebElement getMailboxError() {
        return mailboxError;
    }

    public void wait(WebElement element) {
        WebElement explicitWait = (new WebDriverWait(driver, TIMEOUT))
                .until(ExpectedConditions.elementToBeClickable(element));
    }
}
