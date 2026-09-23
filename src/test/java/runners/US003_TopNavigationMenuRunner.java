package runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "src/test/resources/features/US003_TopNavigationMenu.feature",
        glue = "stepDefinitions")

public class US003_TopNavigationMenuRunner extends AbstractTestNGCucumberTests {
}
