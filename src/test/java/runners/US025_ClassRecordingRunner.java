package runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "src/test/resources/features/US025_ClassRecording.feature",
        glue = "stepDefinitions")

public class US025_ClassRecordingRunner extends AbstractTestNGCucumberTests {
}
