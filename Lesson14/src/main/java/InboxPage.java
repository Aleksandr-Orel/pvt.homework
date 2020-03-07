import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class InboxPage {
    int maxTimeWait = 10;
    private WebDriver driver;

    @FindBy(xpath = "//span[@title='Отправить']")
    private WebElement sentTheMessageButton;

    @FindBy(xpath = "//span[@title='Написать письмо']")
    private WebElement writeMessageButton;

    @FindBy(xpath = "//div[@class='input--3slxg']/*//input[@class='container--H9L5q size_s--3_M-_']")
    private WebElement destinationOfMessageField;

    @FindBy(xpath = "//div[@role='textbox']/div")
    private WebElement textOfMessageField;

    @FindBy(xpath = "//div[contains(@class, 'layer layer_media')]")
    private WebElement confirmationOfSendMassage;

    @FindBy(xpath = "//button[@title='Увеличить']")
    private WebElement increaseMessageWindowButton;

    @FindBy(xpath = "//button[@title='Уменьшить']")
    private WebElement decreaseMessageWindowButton;

    public InboxPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void openWriteMessageForm() {
        writeMessageButton.click();
        WebElement explicitWait = (new WebDriverWait(driver, maxTimeWait))
                .until(ExpectedConditions.elementToBeClickable(destinationOfMessageField));
    }
    public void declareDestinationAddresses(String... emailAddress) {
        WebElement explicitWait = (new WebDriverWait(driver, maxTimeWait))
                .until(ExpectedConditions.elementToBeClickable(destinationOfMessageField));
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
        WebElement explicitWait = (new WebDriverWait(driver, maxTimeWait))
                .until(ExpectedConditions.elementToBeClickable(confirmationOfSendMassage));
    }

    public void increaseMessageForm() {
        increaseMessageWindowButton.click();
    }

    public void decreaseMessageWindow() {
        decreaseMessageWindowButton.click();
    }
}

