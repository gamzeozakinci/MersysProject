package runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "src/test/resources/features/US023_WeeklyCoursePlan.feature",
        glue = "stepDefinitions")

public class US023_WeeklyCoursePlanRunner extends AbstractTestNGCucumberTests {
}
