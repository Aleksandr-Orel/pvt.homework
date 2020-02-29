import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import java.util.List;

public class Test1 {
    static WebDriver driver;
    private int highestPrice = 119;
    private int numberOfDays = 7;
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
        selectNumberOfAdults.selectByIndex(1);

        WebElement search = driver.findElement(By.xpath("//button[contains(@class, \"sb-searchbox__button\")]"));
        search.click();
        WebElement flat = driver.findElement(By.xpath("//div[contains(@class, \"sr_item\")]"));
        Assert.assertTrue(flat.isDisplayed());

        WebElement chooseLowerPrice = driver.findElement(By.xpath("//a[@data-id=\"pri-1\"]"));
        chooseLowerPrice.click();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        WebElement sortByScoreAndPrice = driver.findElement(By.xpath("//a[@data-category=\"review_score_and_price\"]"));
        sortByScoreAndPrice.click();
        WebElement price = driver.findElement(By.xpath("(//div[contains(@class, \"bui-price-display\") and @aria-hidden=\"true\"])[1]"));
        String priceOfFlat = price.getText().replaceAll("[^0-9]", "");
        Assert.assertTrue(Integer.parseInt(priceOfFlat) <= highestPrice * numberOfDays);
    }
}
