package eyesApplitools;
import com.applitools.eyes.EyesRunner;
import com.applitools.eyes.MatchLevel;
import com.applitools.eyes.TestResultsSummary;
import com.applitools.eyes.selenium.ClassicRunner;
import com.applitools.eyes.selenium.Eyes;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Screenshot {
    private final String APIKEY = "FGdQeQpbIinoCNAo2VXbOquvU8XSakWI4DN6pckc6VQ110";
    private EyesRunner runner;
    private Eyes eyes;
    private WebDriver driver;
    private int maxTimeWait = 15;

    public Screenshot(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void eyesOpen(String applicationName, String testName) {
        runner = new ClassicRunner();
        eyes = new Eyes(runner);
        eyes.setApiKey(APIKEY);
        eyes.open(driver, applicationName, testName);
    }

    public void eyesCheckWindow(String screenshotTag, WebElement webElement) {
        WebElement explicitWait = (new WebDriverWait(driver, maxTimeWait))
                .until(ExpectedConditions.elementToBeClickable(webElement));
        eyes.checkWindow(screenshotTag);
    }

    public TestResultsSummary takeScreenshot() {
        eyes.closeAsync();
        eyes.setMatchLevel(MatchLevel.LAYOUT);
        eyes.abortIfNotClosed();
        TestResultsSummary allTestResults = runner.getAllTestResults();
        return allTestResults;
    }
}
