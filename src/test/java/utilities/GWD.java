package utilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.time.Duration;
import java.util.Locale;

public class GWD {
        private static ThreadLocal<WebDriver> threadDriver = new ThreadLocal<>();
        public static ThreadLocal<String> threadBrowserName = new ThreadLocal<>();

        public static WebDriver getDriver() {

            Locale.setDefault(Locale.ENGLISH);
            System.setProperty("user.language", "EN");

            if (threadBrowserName.get() == null) {
                threadBrowserName.set("chrome");
            }

            if (threadDriver.get() == null) {

                String browserName = threadBrowserName.get().toLowerCase();

                switch (browserName) {

                    case "edge":
                        threadDriver.set(new EdgeDriver());
                        break;

                    case "firefox":
                        threadDriver.set(new FirefoxDriver());
                        break;

                    default:
                        ChromeOptions chromeOptions = new ChromeOptions();

                        chromeOptions.addArguments("--disable-notifications");

                        if (isRunningOnJenkins()) {
                            chromeOptions.addArguments(
                                    "--headless=new",
                                    "--no-sandbox",
                                    "--disable-dev-shm-usage",
                                    "--disable-gpu",
                                    "--window-size=1400,2400"
                            );
                        } else {
                            chromeOptions.addArguments("--start-maximized");
                        }

                        threadDriver.set(new ChromeDriver(chromeOptions));
                        break;
                }

                threadDriver.get().manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
                threadDriver.get().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
            }

            return threadDriver.get();
        }

        public static boolean isRunningOnJenkins() {
            String jenkinsHome = System.getenv("JENKINS_HOME");
            return jenkinsHome != null && !jenkinsHome.isEmpty();
        }

        public static void quitDriver() {

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            if (threadDriver.get() != null) {
                threadDriver.get().quit();
                threadDriver.remove();
            }
        }
    }

