import com.applitools.eyes.EyesRunner;
import com.applitools.eyes.MatchLevel;
import com.applitools.eyes.TestResultsSummary;
import com.applitools.eyes.selenium.ClassicRunner;
import com.applitools.eyes.selenium.Eyes;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class InboxPageSteps {
    private static final String MAIN_URL = "http://mail.ru";
    private static final String LOGIN = "Aleksand_or92";
    private static final String PASSWORD = "pru1269";
    private String message = "test";
    private String destinationAddress = "Aleksand_or92@mail.ru";
    private static WebDriver driver;
    private static LoginPage loginPage;
    private static InboxPage inboxPage;
    EyesRunner runner;
    Eyes eyes;
    private int maxTimeWait = 10;

    public InboxPageSteps() {
        System.setProperty("webdriver.chrome.driver", "D:\\Java_lessons/chromedriver.exe");
        driver = new ChromeDriver();
        loginPage = new LoginPage(driver);
        inboxPage = new InboxPage(driver);
    }

    @Given("^i am on main page of email$")
    public void loadInboxPage() {
        runner = new ClassicRunner();
        eyes = new Eyes(runner);
        eyes.setApiKey("FGdQeQpbIinoCNAo2VXbOquvU8XSakWI4DN6pckc6VQ110");
        eyes.open(driver, "Demo App", "SendMessageTest");
        driver.get(MAIN_URL);
        driver.manage().window().fullscreen();
        loginPage.login(LOGIN, PASSWORD);
        eyes.checkWindow("Inbox page");
    }

    @When("^i open write message form$")
    public void switchToWriteMessageForm() {
        inboxPage.openWriteMessageForm();
        inboxPage.increaseMessageForm();
        eyes.checkWindow("Open write message form");
    }

    @And("^i declare destination address of message$")
    public void declareDestinationAddress() {
        inboxPage.declareDestinationAddresses(destinationAddress);
        inboxPage.decreaseMessageWindow();
        eyes.checkWindow("Declare destination address");
    }

    @And("^i write message$")
    public void writeMessage() {
        inboxPage.writeMessage(message);
        eyes.checkWindow("Write message");
    }

    @And("^send message and see confirm$")
    public void sendMessage() {
        inboxPage.sendMessage();
        WebElement explicitWait = (new WebDriverWait(driver, maxTimeWait))
                .until(ExpectedConditions.elementToBeClickable(By.xpath("//div[contains(@class, \"layer layer_media\")]")));
        eyes.checkWindow("Confirm of sending message");
    }

    @Then("^test and close$")
    public void testAndClose() {
        eyes.closeAsync();
        eyes.setMatchLevel(MatchLevel.LAYOUT);
        eyes.abortIfNotClosed();
        driver.close();
        TestResultsSummary allTestResults = runner.getAllTestResults();
        System.out.println(allTestResults);
        Assert.assertTrue(allTestResults.getAllResults()[0].getTestResults().isPassed());
    }
}
