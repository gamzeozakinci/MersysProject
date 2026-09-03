package runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "src/test/resources/features",

        glue = {"stepDefinitions"},

        plugin = {
                "pretty",
                "html:target/cucumber-reports/cucumber.html",
                "json:target/cucumber-reports/cucumber.json",
                "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"
        },

        // Sadece belirli senaryoları koşmak istersen buraya etiket yazabilirsin (Örn: "@Smoke")
        tags = "",

        // true yaparsan tarayıcıyı açmadan sadece eksik adımları (step definitions) kontrol eder
        // Testleri gerçekten koşmak için false kalmalıdır.
        dryRun = false
)
public class TestRunner extends AbstractTestNGCucumberTests {
    // Paralel test koşumu yapılacaksa TestNG'nin bu metodu Override edilir (Şimdilik boş kalabilir)
}

//test push

