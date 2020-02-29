package junitcucumber;
import cucumber.api.java.After;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.ArrayList;
import java.util.List;

public class sendMessageToSpamSteps {
    private static final String MAIN_URL = "http://mail.ru";
    private static final String LOGIN = "Aleksand_or92";
    private static final String PASSWORD = "PASSWORD";
    private static WebDriver driver;
    private static LoginPage loginPage;
    private static MainPage mainPage;
    private static int numberOfMessages = 1;

    public sendMessageToSpamSteps() {
        System.setProperty("webdriver.chrome.driver", "D:\\Java_lessons/chromedriver.exe");
        driver = new ChromeDriver();
        loginPage = new LoginPage(driver);
        mainPage = new MainPage(driver);
    }

    @Given("^I am on main page of email$")
    public void loadMainPage() {
        driver.get(MAIN_URL);
        driver.manage().window().fullscreen();
        loginPage.login(LOGIN, PASSWORD);
    }

    private String messageId;
    private String fistMessage = "//a[contains(@class,\"llct js-letter\")]";
    private String messageAttribute = "data-uidl-id";
    @When("^I send message to spam$")
    public void sendMessageToSpam() {
        System.out.println(driver.getTitle());
        WebElement messageOnTheManePage = driver.findElement(By.xpath(fistMessage));
        messageId = messageOnTheManePage.getAttribute(messageAttribute);
        mainPage.checkSomeMessages(numberOfMessages);
        mainPage.moveMessageIntoOrFromSpam();
    }

    @Then("^I can found this message in the spam folder$")
    public void seeMessageInSpam() {
        mainPage.openSpamFolder();
        List<WebElement> messagesInSpam = driver.findElements(By.xpath(fistMessage));
        List<String> attributesOfMessagesInSpam = new ArrayList<>();
        for (WebElement message : messagesInSpam) {
            attributesOfMessagesInSpam.add(message.getAttribute(messageAttribute));
        }
        Assert.assertTrue(attributesOfMessagesInSpam.contains(messageId));
    }
    @After
    public void afterClass() {
        driver.quit();
    }
}
