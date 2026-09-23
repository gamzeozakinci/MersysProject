package runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "src/test/resources/features/US019_AssignmentDiscussion.feature",
        glue = "stepDefinitions")

public class US019_AssignmentDiscussionRunner extends AbstractTestNGCucumberTests {
}
