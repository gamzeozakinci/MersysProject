package runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "src/test/resources/features/US015_ProfileFeature.feature",
        glue = "stepDefinitions")

public class US015_ProfileFeatureRunner extends AbstractTestNGCucumberTests {

}
