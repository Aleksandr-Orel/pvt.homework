package junitcucumber;
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

public class writeAndSendMessage {
    private static final String MAIN_URL = "http://mail.ru";
    private static final String LOGIN = "Aleksand_or92";
    private static final String PASSWORD = "PASSWORD";
    private static WebDriver driver;
    private static LoginPage loginPage;
    private static MainPage mainPage;
    private static int numberOfMessages = 1;
    private int maxTimeWait = 10;

    public writeAndSendMessage() {
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

    private String message = "test";
    private String destinationAddress = "Aleksand_or92@mail.ru";

    @When("^I open write message form$")
    public void openWriteMessageForm() {
        mainPage.openWriteMessageForm();
    }

    @And("^I declare destination address of message$")
    public void declareDestinationAddress() {
        mainPage.declareDestinationAddresses(destinationAddress);
    }

    @And("^I write message$")
    public void writeMessage() {
        mainPage.writeMessage(message);
    }

    @And("^Send message$")
    public void sendMessage() {
        mainPage.sendMessage();
    }

    @Then("^I see confirm of sending message$")
    public void seeConfirm() {
        WebElement explicitWait = (new WebDriverWait(driver, maxTimeWait))
                .until(ExpectedConditions.elementToBeClickable(By.xpath("//div[contains(@class, \"layer layer_media\")]")));
        WebElement confirmationOfSentMassage = driver
                .findElement(By.xpath("//div[contains(@class, \"layer layer_media\")]"));
        Assert.assertTrue(confirmationOfSentMassage.isEnabled());
    }
}
