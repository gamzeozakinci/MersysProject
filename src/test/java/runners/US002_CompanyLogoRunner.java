package runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "src/test/resources/features/US002_CompanyLogo.feature",
        glue = "stepDefinitions")

public class US002_CompanyLogoRunner extends AbstractTestNGCucumberTests {
}
