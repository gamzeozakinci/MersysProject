package runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "src/test/resources/features/US009_HamMenuFinance.feature",
        glue = {"stepDefinitions", "utilities"},
        plugin = {"pretty", "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"})

public class US009_HamMenuFinanceRunner extends AbstractTestNGCucumberTests {
}
