package runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "src/test/resources/features/US007_HamMenuMessaging.feature",
        glue = "stepDefinitions")


public class US007_HamMenuMessagingRunner extends AbstractTestNGCucumberTests {
}
