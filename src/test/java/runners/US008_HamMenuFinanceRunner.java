package runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "src/test/resources/features/US008_HamMenuFinance.feature",
        glue = "stepDefinitions")

public class US008_HamMenuFinanceRunner extends AbstractTestNGCucumberTests {
}
