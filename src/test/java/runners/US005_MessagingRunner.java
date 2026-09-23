package runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "src/test/resources/features/US005_Messaging.feature",
        glue = "stepDefinitions")

public class US005_MessagingRunner extends AbstractTestNGCucumberTests {
}
