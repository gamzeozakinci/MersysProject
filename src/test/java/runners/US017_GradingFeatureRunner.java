package runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "src/test/resources/features/US017_GradingFeature.feature",
        glue = "stepDefinitions")

public class US017_GradingFeatureRunner extends AbstractTestNGCucumberTests {
}
