package runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "src/test/resources/features/US006_DeleteMessage.feature",
        glue = "stepDefinitions")

public class US006_DeleteMessageRunner extends AbstractTestNGCucumberTests {
}
