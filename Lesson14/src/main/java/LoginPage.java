import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {
    private int timeOut = 10;

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

    private int maxTimeWait = 10;
    public void login(String login, String password) {
        loginField.sendKeys(login);
        nextButton.click();
        WebElement explicitWait = (new WebDriverWait(driver, timeOut))
                .until(ExpectedConditions.elementToBeClickable(passwordField));
        passwordField.sendKeys(password);
        nextButton.click();
        boolean explicitWaitInboxPage = (new WebDriverWait(driver, maxTimeWait))
                .until(ExpectedConditions.urlContains("https://e.mail.ru/inbox"));
    }
}
