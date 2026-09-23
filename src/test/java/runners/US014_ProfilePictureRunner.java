package runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "src/test/resources/features/US014_ProfilePicture.feature",
        glue = "stepDefinitions")

public class US014_ProfilePictureRunner extends AbstractTestNGCucumberTests {
}
