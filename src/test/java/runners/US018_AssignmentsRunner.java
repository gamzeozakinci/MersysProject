package runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "src/test/resources/features/US018_Assignments.feature",
        glue = {"stepDefinitions", "utilities"},
        plugin = {"pretty", "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"})

public class US018_AssignmentsRunner extends AbstractTestNGCucumberTests {
}
