package runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "src/test/resources/features/US001_Login.feature",
        glue = "stepDefinitions")

public class US001_LoginRunner extends AbstractTestNGCucumberTests {
}
