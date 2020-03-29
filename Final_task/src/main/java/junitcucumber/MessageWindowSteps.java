package junitcucumber;

import core.browser.SingletonChromeDriver;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import model.Message;
import org.junit.Assert;
import org.openqa.selenium.Alert;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;
import page.InboxPage;
import page.LoginPage;
import parser.DomParser;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.stream.XMLStreamException;
import java.io.IOException;
import java.util.List;

public class MessageWindowSteps {
    private WebDriver driver;
    private LoginPage loginPage;
    private InboxPage inboxPage;
    List<Message> messages;
    String testEmailAddress;
    String testEmailHeading;
    String testEmailMessage;
    int numberOfMessage = 1;

    public MessageWindowSteps() {
        driver = SingletonChromeDriver.getInstance();
        loginPage = new LoginPage(driver);
        inboxPage = new InboxPage(driver);
    }

    @Given("^i sign in inbox page$")
    public void openInboxPage() throws ParserConfigurationException, XMLStreamException, SAXException, IOException {
        loginPage = new LoginPage(driver);
        inboxPage = new InboxPage(driver);
        messages = runParser();
        testEmailAddress = messages.get(0).getDestinationAddress();
        testEmailHeading = messages.get(0).getMessageHeading();
        testEmailMessage = messages.get(0).getMessageText();
    }

    @Given("^i open inbox page$")
    public void refreshInboxPage() {
        inboxPage.openInboxPage();
    }

    @When("^i write test message$")
    public void writeTestMessage() {
        inboxPage.openWriteMessageForm();
        inboxPage.declareDestinationAddresses(testEmailAddress);
        inboxPage.writeMessage(testEmailMessage);
        inboxPage.sendMessage();
        inboxPage.wait(inboxPage.getConfirmationOfSendMessage());
        inboxPage.openInboxPage();
    }

    @And("^i open reply to message window$")
    public void openReplyToMessageWindow() {
        inboxPage.openMessage(numberOfMessage);
        inboxPage.openReplyToMessageForm();
    }

    @And("^i enter wrong destination address$")
    public void enterWrongDestinationAddress() {
        String wrongEmailAddress = "aleksand_or92mailru";
        inboxPage.declareDestinationAddresses(wrongEmailAddress);
    }

    @And("^i write text of message$")
    public void writeTextOfMessage() {
        String text = testEmailMessage;
        inboxPage.writeMessage(text);
    }

    @And("^i send message$")
    public void sendTestMessage() {
        inboxPage.sendMessage();
    }

    @And("^i close alert$")
    public void closeAlert() {
        driver.get("https://e.mail.ru/inbox");
        long waitForAlert= System.currentTimeMillis() + 10;
        boolean boolFound = false;
        do
        {
            try
            {
                Alert alert = this.driver.switchTo().alert();
                if (alert != null)
                {
                    alert.accept();
                    boolFound = true;
                }
            }
            catch (NoAlertPresentException ex) {}
        } while ((System.currentTimeMillis() < waitForAlert) && (!boolFound));
    }

    @Then("^i see confirm of sending message$")
    public void hasConfirmOfSendingMessage() {
        WebElement element = inboxPage.getConfirmationOfSendMessage();
        inboxPage.wait(element);
        Assert.assertTrue(element.isEnabled());
    }

    @Then("^i see confirmation of send empty letter$")
    public void hasConfirmationOfSendEmptyLetter() {
        WebElement element = inboxPage.getConfirmationOfSendEmptyMessage();
        inboxPage.wait(element);
        Assert.assertTrue(element.isEnabled());
    }

    @Then("^i see alert that destination address is wrong$")
    public void hasAlertWrongDestinationAddress() {
        WebElement element = inboxPage.getAlertDestinationAddressWrong();
        inboxPage.wait(element);
        Assert.assertTrue(element.isEnabled());
    }

    public List<Message> runParser() throws ParserConfigurationException, SAXException, IOException,
            XMLStreamException {
        final String MESSAGES_XML = "messages.xml";
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document document = dBuilder.parse(MESSAGES_XML);
        messages = new DomParser().parse(document);
        return messages;
    }
}
