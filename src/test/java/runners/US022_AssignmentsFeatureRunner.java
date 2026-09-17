package runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "src/test/resources/features/US022_AssigmentsFeature.feature",
        glue = "stepDefinitions")


public class US022_AssignmentsFeatureRunner extends AbstractTestNGCucumberTests {
}
