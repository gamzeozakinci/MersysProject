package runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "src/test/resources/features/US004_HamMenuMessaging.feature",
        glue = "stepDefinitions")


public class US004_HamMenuMessagingRunner extends AbstractTestNGCucumberTests {
}
