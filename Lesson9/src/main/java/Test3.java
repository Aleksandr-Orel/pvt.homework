import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import java.util.List;
import java.util.Set;

public class Test3 {
    static WebDriver driver;
    private int today;
    private int shiftOfInputDay = 3;
    private int shiftOfOutputDay = 10;


    @Test
    public void searchOfHotel() {
        System.setProperty("webdriver.chrome.driver", "D:\\Java_lessons/chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("http://www.booking.com");
        WebElement searchInput = driver.findElement(By.xpath("//input[contains(@data-component,\"search/destination\")]"));
        searchInput.sendKeys("париж");
        WebElement searchTown = driver.findElement(By.xpath("//button[contains(@class, \"sb-searchbox__button\")]"));
        searchTown.submit();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        List<WebElement> calenderOfCheckIn = driver.findElements(By.xpath("//div[@class=\"c2-calendar\"]/*//td[contains(@class,\"c2-day\")]"));
        WebElement numberOfDay = driver.findElement(By.xpath("//td[@class=\"c2-day c2-day-s-today\"]"));
        today = Integer.parseInt(numberOfDay.getText());
        System.out.println(today);
        int checkInDate = today + shiftOfInputDay;
        int checkOutDate = today + shiftOfOutputDay;

        String dateOfArrive = String.valueOf("(//div[@class=\"c2-calendar\"]/*//td[contains(@class,\"c2-day\")])[" + checkInDate + "]");
        WebElement arriveDate = driver.findElement(By.xpath(dateOfArrive));
        arriveDate.click();

        List<WebElement> calenderOfCheckOut = driver.findElements(By.xpath("//div[contains(@class,\"checkout-field\")]/*//td[contains(@class,\"c2-day\")]"));
        WebElement checkOutDateBox = driver.findElement(By.xpath("//div[contains(@class,\"date-field\") and @data-mode=\"checkout\"]"));
        checkOutDateBox.click();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String dateOfLeave = String.valueOf("(//div[contains(@class,\"checkout-field\")]/*//td[contains(@class,\"c2-day\")])[" + checkOutDate + "]");
        WebElement leaveDate = driver.findElement(By.xpath((dateOfLeave)));
        leaveDate.click();

        WebElement numberOfAdults = driver.findElement(By.xpath("//select[@aria-label=\"Number of adults\"]"));
        Select selectNumberOfAdults = new Select(numberOfAdults);
        selectNumberOfAdults.selectByIndex(3);

        WebElement numberOfRooms = driver.findElement(By.xpath("//select[@id=\"no_rooms\"]"));
        Select selectNumberOfRooms = new Select(numberOfRooms);
        selectNumberOfRooms.selectByIndex(1);

        WebElement search = driver.findElement(By.xpath("//button[contains(@class, \"sb-searchbox__button\")]"));
        search.click();
        WebElement chooseHigherPrice = driver.findElement(By.xpath("//a[@data-id=\"pri-1\"]"));
        chooseHigherPrice.click();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        WebElement sortByLowerPrice = driver.findElement(By.xpath("//li[@class=\" sort_category   sort_price \"]"));
        sortByLowerPrice.click();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        WebElement hotel = driver.findElement(By.xpath("(//a[@class=\"hotel_name_link url\"])[1]"));
        hotel.click();

        Set<String> windowHandles = driver.getWindowHandles();
        for (String window : windowHandles) {
            driver.switchTo().window(window);
        }
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        WebElement numberOfRoomsBox = driver.findElement(By.xpath("(//select[@class=\"hprt-nos-select\"])[1]"));
        Select selectNumberOfRoomsBox = new Select(numberOfRoomsBox);
        selectNumberOfRoomsBox.selectByIndex(1);

        WebElement reserveButton = driver.findElement(By.xpath("//button[contains(@class, \"js-reservation-button\")]"));
        reserveButton.click();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        WebElement lastName = driver.findElement(By.id("lastname"));
        lastName.sendKeys("Alex");
        WebElement emailAddress = driver.findElement(By.id("email"));
        emailAddress.sendKeys("tenow61032@twit-mail.com");
        WebElement confirmEmailAddress = driver.findElement(By.id("email_confirm"));
        confirmEmailAddress.sendKeys("tenow61032@twit-mail.com");
        WebElement finalDetailsButton = driver.findElement(By.xpath("//i[@class=\"submit-button__arrow\"]"));
        finalDetailsButton.click();

        WebElement telephoneNumber = driver.findElement(By.id("phone"));
        telephoneNumber.sendKeys("555555555");
        WebElement cardType = driver.findElement(By.id("cc_type"));
        Select select = new Select(cardType);
        select.selectByIndex(3);
        WebElement cardNumber = driver.findElement(By.id("cc_number"));
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        cardNumber.sendKeys("4242 4242 4242 4242");
        try {
            Thread.sleep(7000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        WebElement expirationDate = driver.findElement(By.id("cc_month"));
        Select selectDate = new Select(expirationDate);
        selectDate.selectByIndex(3);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        WebElement expirationYear = driver.findElement(By.id("ccYear"));
        Select selectYear = new Select(expirationYear);
        selectYear.selectByIndex(3);
        WebElement codeCVC = driver.findElement(By.id("cc_cvc"));
        codeCVC.sendKeys("111");
        WebElement completeBooking = driver.findElement(By.xpath("(//button[@name=\"book\"])[2]"));
        completeBooking.click();
        try {
            Thread.sleep(20000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        WebElement successRegistration = driver.findElement(By.xpath("//div[@class=\"bui-alert__description\"]"));
        Assert.assertTrue(successRegistration.isDisplayed());
    }
}

