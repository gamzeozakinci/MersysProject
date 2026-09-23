package runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "src/test/resources/features/US011_Finance.feature",
        glue = "stepDefinitions")

public class US011_FinanceRunner extends AbstractTestNGCucumberTests {
}
