package core.browser;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.GeckoDriverService;

public class FirefoxDriverManager extends DriverManager {

    private GeckoDriverService geckoDriverService;

    @Override
    public void startService() {
        WebDriverManager.firefoxdriver().setup();
        if (null == geckoDriverService) {
            try {
                geckoDriverService = new GeckoDriverService.Builder()
                        .usingAnyFreePort()
                        .build();
                geckoDriverService.start();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void stopService() {
        if (null != geckoDriverService && geckoDriverService.isRunning())
            geckoDriverService.stop();
    }

    @Override
    public void createDriver() {
        driver = new FirefoxDriver(geckoDriverService);
    }
}