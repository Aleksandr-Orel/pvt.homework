package page;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.List;

public class InboxPage {
    int maxTimeWait = 15;
    private String xpathCheckBoxOfLastMessage = "//div[@class='llct__header']";

    private String xpathLastMessage = "//div[@class='llct__container']";
    private WebDriver driver;

    @FindBy(xpath = "//a[contains(@class,'letter-list-item')]")
    private WebElement lastMessage;

    @FindBy(xpath = "//span[@title='Отправить']")
    private WebElement sendMessageButton;

    @FindBy(id = "ph_mail")
    private WebElement mailButton;

    @FindBy(xpath = "//span[@title='Написать письмо']")
    private WebElement writeMessageButton;

    @FindBy(xpath = "//div[@class='input--3slxg']/*//input[@class='container--H9L5q size_s--3_M-_']")
    private WebElement destinationOfMessageField;

    @FindBy(xpath = "//div[@role='textbox']/div")
    private WebElement textOfMessageField;

    @FindBy(xpath = "//span[@title='Ответить']")
    private WebElement replyButton;

    @FindBy(xpath = "//span[@data-title-shortcut='Del']")
    private WebElement deleteButton;

    @FindBy(xpath = "//a[@href='/trash/']")
    private WebElement basketFolder;

    @FindBy(xpath = "//div[@type='button']//span[contains(@class,'search-panel-button')]")
    private WebElement searchFieldButton;

    @FindBy(xpath = "//input[@class='_1BEp2b6vqOez8I6Rw9SpK6 _3pRLYQt59tmiwn0Ugfy9W5']")
    private WebElement searchField;

    @FindBy(xpath = "//span[text()='Найти']")
    private WebElement searchButton;

    @FindBy(xpath = "//span[text()='Новая папка']")
    private WebElement createNewFolderButton;

    @FindBy(xpath = "//input[@placeholder='Название']")
    private WebElement folderNameField;

    @FindBy(xpath = "//span[text() = 'Добавить папку']")
    private WebElement addFolderButton;

    @FindBy(xpath = "//div[@title='Фильтр по письмам']")
    private WebElement messageFilter;

    @FindBy(xpath = "//span[text()='Непрочитанные']")
    private WebElement filterUnreadMessageButton;

    @FindBy(xpath =  "//span[text()='С флажком']")
    private WebElement filterMessageWithFlagButton;

    @FindBy(xpath = "//button[@title='Пометить флажком']")
    private WebElement setFlagOnMessageButton;

    @FindBy(xpath = "//div[contains(@class, 'layer layer_media')]")
    private WebElement confirmationOfSendMessage;

    @FindBy(xpath = "//h1[text()='Вы действительно хотите отправить пустое письмо?']")
    private WebElement confirmationOfSendEmptyMessage;

    @FindBy(xpath = "//h1[text()='Присутствуют некорректные адреса']")
    private WebElement alertDestinationAddressWrong;

    @FindBy(xpath = "//div[@data-test-id='confirmation:empty-letter']//span[text()='Отправить']")
    private WebElement sendEmptyMessageButton;

    @FindBy(xpath = "//span[text()='Отменить']")
    public WebElement cancelButton;

    public InboxPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void checkSomeMessages(int numberOfMessages) {
        String xpathOfMassage;
        for (int i = 1; i <= numberOfMessages; i++) {
            xpathOfMassage = String.format("(%s)[%d]", xpathCheckBoxOfLastMessage, i);
            WebElement checkBoxOfMessage = driver.findElement(By.xpath(xpathOfMassage));
            Actions actions = new Actions(driver);
            actions.moveToElement(checkBoxOfMessage).click().build().perform();
        }
    }

    public void openInboxPage() {
        wait(mailButton);
        mailButton.click();
        wait(lastMessage);
    }

    public void openWriteMessageForm() {
        wait(writeMessageButton);
        writeMessageButton.click();
        wait(destinationOfMessageField);
    }

    public void declareDestinationAddresses(String... emailAddress) {
        wait(destinationOfMessageField);
        destinationOfMessageField.clear();
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
        sendMessageButton.click();
    }

    public void openMessage(int numberOfMessage) {
        wait(lastMessage);
        String xpathOfMassage = String.format("(%s)[%d]", xpathLastMessage, numberOfMessage);
        WebElement message = driver.findElement(By.xpath(xpathOfMassage));
        Actions actions = new Actions(driver);
        actions.moveToElement(message).click().build().perform();
        wait(replyButton);
    }

    public void openReplyToMessageForm() {
        wait(replyButton);
        replyButton.click();
        wait(textOfMessageField);
    }

    public void deleteMessage() {
        deleteButton.click();
    }

    public void openBasket() {
        basketFolder.click();
        boolean explicitWait = (new WebDriverWait(driver, maxTimeWait))
                .until(ExpectedConditions.urlContains("https://e.mail.ru/trash/"));
    }

    public void findMessage(String request) {
        searchFieldButton.click();
        searchField.sendKeys(request);
        searchButton.click();
    }

    public void openMessageFilter() {
        messageFilter.click();
        wait(filterUnreadMessageButton);
    }

    public  void filterUnreadMessage() {
        filterUnreadMessageButton.click();
        wait(lastMessage);
    }

    public void filterMessageWithFlag() {
        filterMessageWithFlagButton.click();
        wait(lastMessage);
    }

    public void clickToFlagOnMassage() {
        lastMessage.click();
        setFlagOnMessageButton.click();
    }

    public int amountOfMessages() {
        List<WebElement> list = driver.findElements(By.xpath(xpathLastMessage));
        return list.size();
    }

    public int amountOfUnreadMessages() {
        String unreadMessageMark = "//span[@title='Пометить прочитанным']";
        List<WebElement> list = driver.findElements(By.xpath(unreadMessageMark));
        return list.size();
    }

    public int amountOfMessagesWithFlag() {
        String messageWithFlag = "//button[@title='Снять флажок']";
        List<WebElement> list = driver.findElements(By.xpath(messageWithFlag));
        return list.size();
    }

    public void wait(WebElement webElement) {
        WebElement explicitWait = (new WebDriverWait(driver, maxTimeWait))
                .until(ExpectedConditions.elementToBeClickable(webElement));
    }

    public WebElement getMessageFilter() {
        return messageFilter;
    }

    public WebElement getConfirmationOfSendMessage() {
        return confirmationOfSendMessage;
    }

    public WebElement getConfirmationOfSendEmptyMessage() {
        return confirmationOfSendEmptyMessage;
    }

    public WebElement getAlertDestinationAddressWrong() {
        return alertDestinationAddressWrong;
    }
}

