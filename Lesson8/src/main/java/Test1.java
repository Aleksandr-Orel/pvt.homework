import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class Test1 {
    static WebDriver driver;
    @BeforeClass
    public static void browserOpen() {
        System.setProperty("webdriver.chrome.driver", "D:\\Java_lessons/chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("http://www.booking.com");
    }
    @Test
    public void searchOfHotel() {
        WebElement searchInput = driver.findElement(By.xpath("//input[contains(@data-component,\"search/destination\")]"));
        searchInput.sendKeys("москва");

        WebElement searchTown = driver.findElement(By.xpath("//button[contains(@class, \"sb-searchbox__button\")]"));
        searchTown.submit();

        WebElement checkInDate = driver.findElement(By.xpath("//td[@data-date=\"2020-03-19\" or @data-id=\"1584576000000\"]"));
        checkInDate.click();

        WebElement openCheckOutDateBox = driver.findElement(By.xpath("//div[contains(@class,\"date-field\") and @data-mode=\"checkout\"]"));
        openCheckOutDateBox.click();
        WebElement checkOutDate = driver.findElement(By.xpath("//td[@data-date=\"2020-03-25\"]"));
        checkOutDate.click();

        WebElement numberOfAdults = driver.findElement(By.xpath("//select[@aria-label=\"Number of adults\"]"));
        Select selectNumberOfAdults = new Select(numberOfAdults);
        selectNumberOfAdults.selectByIndex(1);

        WebElement numberOfRooms = driver.findElement(By.xpath("//select[@id=\"no_rooms\"]"));
        Select selectNumberOfRooms = new Select(numberOfRooms);
        selectNumberOfRooms.selectByIndex(0);

        WebElement search = driver.findElement(By.xpath("//button[contains(@class, \"sb-searchbox__button\")]"));
        search.click();

        WebElement flat = driver.findElement(By.xpath("//div[contains(@class, \"sr_item\")]"));
        Assert.assertTrue(flat.isDisplayed());
    }
    @AfterClass
    public static void browserClose() {
        driver.close();
    }
}
