package utilities;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Hooks {

    @Before
    public void setUp(Scenario scenario) {
        System.out.println("Scenario started: " + scenario.getName());
    }

    @After
    public void afterScenario(Scenario scenario) {
        try {
            if (scenario.isFailed()) {
                System.out.println("Scenario FAILED: " + scenario.getName());

                byte[] screenshot = ((TakesScreenshot) GWD.getDriver()).getScreenshotAs(OutputType.BYTES);
                scenario.attach(screenshot, "image/png", "Failure Screenshot");
                saveScreenshot(screenshot, scenario);
            }
        } catch (Exception e) {
            System.out.println("After hook error: " + e.getMessage());
        } finally {
            GWD.quitDriver();
            System.out.println("Scenario finished: " + scenario.getName());
        }
    }

    private static void saveScreenshot(byte[] png, Scenario scenario) {
        String featureName = scenario.getUri().getPath()
                .replaceAll(".*/", "")
                .replace(".feature", "");

        String scenarioName = scenario.getName().replaceAll("[^a-zA-Z0-9]", "_");
        if (scenarioName.length() > 50) {
            scenarioName = scenarioName.substring(0, 50);
        }

        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss_SSS"));
        Path folder = Paths.get(System.getProperty("user.dir"), "target", "screenshots");
        Path file = folder.resolve(featureName + "_" + scenarioName + "_" + timestamp + ".png");

        try {
            Files.createDirectories(folder);
            Files.write(file, png);
            System.out.println("Screenshot saved: " + file.toAbsolutePath());
        } catch (IOException e) {
            System.out.println("Screenshot could not be saved: " + e.getMessage());
        }
    }
}
