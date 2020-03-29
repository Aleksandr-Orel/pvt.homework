package junitcucumber;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = {"features/Login.feature", "features/MessageWindow.feature", "features/MessageFilter.feature"}
)

public class PagesStepTest {
}
