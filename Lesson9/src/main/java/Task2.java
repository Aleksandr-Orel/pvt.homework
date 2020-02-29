import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import java.util.List;

public class Task2 {
    static WebDriver driver;
    private int lowerPrice = 955;
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
        System.out.println(dateOfLeave);
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
        WebElement chooseHigherPrice = driver.findElement(By.xpath("//a[@data-id=\"pri-5\"]"));
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
        WebElement price = driver.findElement(By.xpath("(//div[contains(@class, \"bui-price-display\") and @aria-hidden=\"true\"])[1]"));
        System.out.println(price.getText());
        String priceOfFlat = price.getText().replaceAll("[^0-9]", "");
        System.out.println(priceOfFlat);
        Assert.assertTrue(Integer.parseInt(priceOfFlat) >= lowerPrice);
    }
}
