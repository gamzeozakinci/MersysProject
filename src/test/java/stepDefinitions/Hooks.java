package stepDefinitions;

import utilities.GWD;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Hooks {

    private static WebElement lastUsedElement;

    public static void setLastUsedElement(WebElement element) {
        lastUsedElement = element;
    }

    @Before
    public void setUp(Scenario scenario) {
        System.out.println("Scenario Started: " + scenario.getName());
    }

    @After
    public void afterScenario(Scenario scenario) {

        try {
            WebDriver driver = GWD.getDriver();

            if (scenario.isFailed()) {

                System.out.println("Scenario FAILED: " + scenario.getName());
                System.out.println("lastUsedElement = " + lastUsedElement);

                if (lastUsedElement != null) {
                    highlightElement(lastUsedElement, driver);
                } else {
                    System.out.println("Highlight yapılamadı: lastUsedElement null.");
                }

                byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
                scenario.attach(screenshot, "image/png", "Failure Screenshot");

                takeScreenshot(driver, scenario);
            }

        } catch (Exception e) {
            System.out.println("After Hook hatası: " + e.getMessage());

        } finally {
            GWD.quitDriver();
            lastUsedElement = null;
            System.out.println("Scenario finished: " + scenario.getName());
        }
    }

    public static void highlightElement(WebElement element, WebDriver driver) {
        try {
            if (element == null || driver == null) {
                System.out.println("Highlight yapılamadı: element veya driver null.");
                return;
            }

            JavascriptExecutor js = (JavascriptExecutor) driver;

            js.executeScript(
                    "arguments[0].scrollIntoView({block:'center'});",
                    element
            );

            Thread.sleep(300);

            js.executeScript(
                    "arguments[0].style.setProperty('border', '5px solid red', 'important');" +
                            "arguments[0].style.setProperty('outline', '5px solid red', 'important');" +
                            "arguments[0].style.setProperty('box-shadow', '0 0 20px red', 'important');" +
                            "arguments[0].style.setProperty('background-color', 'yellow', 'important');" +
                            "arguments[0].style.setProperty('z-index', '999999', 'important');",
                    element
            );

            Thread.sleep(700);

        } catch (Exception e) {
            System.out.println("Element highlight hatası: " + e.getMessage());
        }
    }

    public static void takeScreenshot(WebDriver driver, Scenario scenario) {

        if (!scenario.isFailed()) {
            return;
        }

        try {
            LocalDateTime now = LocalDateTime.now();

            DateTimeFormatter folderFormat = DateTimeFormatter.ofPattern("yyyy/MM/dd");
            DateTimeFormatter fileFormat = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss_SSS");

            String dateFolder = now.format(folderFormat);
            String dateFile = now.format(fileFormat);

            String status = "FAILED";

            String featureName = scenario.getUri().getPath()
                    .replaceAll(".*/", "")
                    .replaceAll("\\.feature", "");

            String scenarioName = scenario.getName()
                    .replaceAll("[^a-zA-Z0-9]", "_");

            if (scenarioName.length() > 50) {
                scenarioName = scenarioName.substring(0, 50);
            }

            String folderPath = System.getProperty("user.dir")
                    + "\\src\\ScreenShots\\"
                    + dateFolder
                    + "\\"
                    + status
                    + "\\";

            File folder = new File(folderPath);

            if (!folder.exists()) {
                folder.mkdirs();
            }

            File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

            String fileName = featureName + "_" + scenarioName + "_" + dateFile + ".png";

            File destinationFile = new File(folderPath + fileName);

            Files.copy(
                    screenshot.toPath(),
                    destinationFile.toPath(),
                    StandardCopyOption.REPLACE_EXISTING
            );

            System.out.println("Screenshot kaydedildi: " + destinationFile.getAbsolutePath());

        } catch (IOException e) {
            System.out.println("Screenshot kaydedilemedi: " + e.getMessage());
        }
    }
}

