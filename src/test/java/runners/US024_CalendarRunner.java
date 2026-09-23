package runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "src/test/resources/features/US024_Calendar.feature",
        glue = "stepDefinitions")

public class US024_CalendarRunner extends AbstractTestNGCucumberTests {
}
