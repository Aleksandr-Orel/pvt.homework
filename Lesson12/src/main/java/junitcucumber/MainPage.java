package junitcucumber;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.NoSuchElementException;

public class MainPage {
    int maxTimeWait = 10;
    private String checkBoxOfTheLastMessage = "//div[@class=\"llct__header\"]";
    private WebDriver driver;

    @FindBy(xpath = "//span[contains(@class,\"button2_spam\")]")
    private WebElement putToSpamFolderButton;

    @FindBy(xpath = "//a[@href=\"/spam/\"]")
    private WebElement spamFolderButton;

    @FindBy(xpath = "//span[@title=\"Отправить\"]")
    private WebElement sentTheMessageButton;

    @FindBy(id = "ph_mail")
    private WebElement mailButton;

    @FindBy(xpath = "//span[@title=\"Написать письмо\"]")
    private WebElement writeMessageButton;

    @FindBy(xpath = "//div[@class=\"input--3slxg\"]/*//input[@class=\"container--H9L5q size_s--3_M-_\"]")
    private WebElement destinationOfMessageField;

    @FindBy(xpath = "//div[@role=\"textbox\"]/div")
    private WebElement textOfMessageField;

    @FindBy(xpath = "//span[contains(@class,\"button2_select-all\")]")
    private WebElement checkAllMessagesButton;

    public MainPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void checkSomeMessages(int numberOfMessages) {
        String xpathOfMassage;
        for (int i = 1; i <= numberOfMessages; i++) {
            xpathOfMassage = "(" + checkBoxOfTheLastMessage + ")[" + i + "]";
            WebElement checkBoxOfMessage = driver.findElement(By.xpath(xpathOfMassage));
            Actions actions = new Actions(driver);
            actions.moveToElement(checkBoxOfMessage).click().build().perform();
        }
    }

    public void moveMessageIntoOrFromSpam() {
        putToSpamFolderButton.click();
    }
    public void openSpamFolder() {
        spamFolderButton.click();
        boolean explicitWait = (new WebDriverWait(driver, maxTimeWait))
                .until(ExpectedConditions.titleIs("Спам - Почта Mail.ru"));
    }
    public void openPageWithMessages() {
        mailButton.click();
        boolean explicitWait = (new WebDriverWait(driver, maxTimeWait))
                .until(ExpectedConditions.titleIs("Почта Mail.ru"));
    }
    public void checkAllMessages() {
        checkAllMessagesButton.click();
    }
    public void openWriteMessageForm() {
        writeMessageButton.click();
        WebElement explicitWait = (new WebDriverWait(driver, maxTimeWait))
                .until(ExpectedConditions.elementToBeClickable(destinationOfMessageField));
    }

    private int intervalOfPolling = 2;
    public void declareDestinationAddresses(String... emailAddress) {
        Wait wait = new FluentWait(driver)
                .withTimeout(Duration.ofSeconds(maxTimeWait))
                .pollingEvery(Duration.ofSeconds(intervalOfPolling))
                .ignoring(NoSuchElementException.class);
        wait.until(ExpectedConditions.elementToBeClickable(destinationOfMessageField));
        for (int i = 0; i < emailAddress.length; i++) {
            destinationOfMessageField.click();
            destinationOfMessageField.sendKeys(emailAddress[i]);
            destinationOfMessageField.sendKeys(Keys.ENTER);
        }
    }
    public void writeMessage(String massage) {
        textOfMessageField.click();
        textOfMessageField.sendKeys(massage);
    }
    public void sendMessage() {
        sentTheMessageButton.click();
    }
}
