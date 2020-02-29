package Pages;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class EmailMainPageTest {
    private static int maxTimeWait = 10;
    private static WebDriver driver;
    private static LoginPage loginPage;
    private static EmailMainPage emailMainPage;

    @BeforeClass
    public static void openBrowser() {
        System.setProperty("webdriver.chrome.driver", "D:\\Java_lessons/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(maxTimeWait, TimeUnit.SECONDS);
        driver.get("http://mail.ru");
        driver.manage().window().fullscreen();
        loginPage = new LoginPage(driver);
        emailMainPage = new EmailMainPage(driver);
        loginPage.login("LOGIN", "PASSWORD");
    }

    private String fistMessage = "//a[contains(@class,\"llct js-letter\")]";
    private String messageAttribute = "data-uidl-id";
    private String messageId;
    @Test
    public void putMessageToSpam() {
        WebElement messageOnTheManePage = driver.findElement(By.xpath(fistMessage));
        messageId = messageOnTheManePage.getAttribute(messageAttribute);
        emailMainPage.checkSomeMessages(1);
        emailMainPage.moveMessageIntoOrFromSpam();
        emailMainPage.openSpamFolder();
        List<WebElement> messagesInSpam = driver.findElements(By.xpath(fistMessage));
        List<String> attributesOfMessagesInSpam = new ArrayList<>();
        for (WebElement message : messagesInSpam) {
            attributesOfMessagesInSpam.add(message.getAttribute(messageAttribute));
        }
        Assert.assertTrue(attributesOfMessagesInSpam.contains(messageId));
    }

    @Test
    public void removeMessageFromSpam() {
        emailMainPage.openSpamFolder();
        WebElement messageOnTheSpamFolder = driver.findElement(By.xpath(fistMessage));
        messageId = messageOnTheSpamFolder.getAttribute(messageAttribute);
        emailMainPage.checkSomeMessages(1);
        emailMainPage.moveMessageIntoOrFromSpam();
        emailMainPage.openPageWithMessages();
        List<WebElement> messagesOnTheManePage = driver.findElements(By.xpath(fistMessage));
        List<String> attributesOfMessages = new ArrayList<>();
        for (WebElement message : messagesOnTheManePage) {
            attributesOfMessages.add(message.getAttribute(messageAttribute));
        }
        Assert.assertTrue(attributesOfMessages.contains(messageId));
    }

    @Test
    public void sentMessageToSomeUsers() {
        emailMainPage.openWriteMessageForm();
        emailMainPage.declareDestinationAddresses("Aleksand_or92@mail.ru", "Alexor.minsk@gmail.com");
        emailMainPage.writeMessage("test");
        emailMainPage.sendMessage();
        WebElement confirmationOfSentMassage = driver
                .findElement(By.xpath("//div[contains(@class, \"layer layer_media\")]"));
        Assert.assertTrue(confirmationOfSentMassage.isEnabled());
    }

    private int numberOfMessages = 3;
    private String xpathOfTheFirstMassage;
    private String xpathOfTheMassage;
    @Test
    public void checkSomeMessages() {
        xpathOfTheFirstMassage = "//a[@class=\"llct js-letter-list-item llct_active\"]";
        emailMainPage.checkSomeMessages(numberOfMessages);
        for (int i = 1; i <= numberOfMessages; i++) {
            xpathOfTheMassage = "(" + xpathOfTheFirstMassage + ")[" + i + "]";
            WebElement checkBoxOfMassage = driver.findElement(By.xpath(xpathOfTheMassage));
            Assert.assertTrue(checkBoxOfMassage.isEnabled());
        }
    }

    @Test
    public void cancelCheckSomeMassages() {
        xpathOfTheFirstMassage = "//a[@class=\"llct js-letter-list-item\"]";
        emailMainPage.checkSomeMessages(numberOfMessages);
        emailMainPage.checkAllMessages();
        emailMainPage.checkAllMessages();
        for (int i = 1; i <= numberOfMessages; i++) {
            xpathOfTheMassage = "(" + xpathOfTheFirstMassage + ")[" + i + "]";
            WebElement checkBoxOfMassage = driver.findElement(By.xpath(xpathOfTheMassage));
            Assert.assertTrue(checkBoxOfMassage.isEnabled());
        }
    }

    @AfterClass
    public static void closeBrowser() {
        driver.close();
    }
}
