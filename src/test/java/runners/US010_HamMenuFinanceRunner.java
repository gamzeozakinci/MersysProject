package runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "src/test/resources/features/US010_HamMenuFinance.feature",
        glue = "stepDefinitions")

public class US010_HamMenuFinanceRunner extends AbstractTestNGCucumberTests {

}