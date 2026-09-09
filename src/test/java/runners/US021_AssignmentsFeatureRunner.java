package runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "src/test/resources/features/US021_AssignmentsFeature.feature",
        glue = "stepDefinitions")

public class US021_AssignmentsFeatureRunner extends AbstractTestNGCucumberTests {
}
