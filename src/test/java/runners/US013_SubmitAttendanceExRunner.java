package runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "src/test/resources/features/US013_SubmitAttendaceEx.feature",
        glue = "stepDefinitions")

public class US013_SubmitAttendanceExRunner extends AbstractTestNGCucumberTests {


}
