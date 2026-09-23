package runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "src/test/resources/features/US020_AssignmentQuickActions.feature",
        glue = "stepDefinitions")

public class US020_AssignmentQuickActionsRunner extends AbstractTestNGCucumberTests {
}
