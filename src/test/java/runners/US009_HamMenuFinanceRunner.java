package runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "src/test/resources/features/US009_HamMenuFinance.feature",
        glue = "stepDefinitions")

public class US009_HamMenuFinanceRunner extends AbstractTestNGCucumberTests {

}
