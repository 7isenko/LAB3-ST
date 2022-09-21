package io.github._7isenko.junitlearning;

import io.github._7isenko.junitlearning.utils.Properties;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.time.Duration;

/**
 * @author 7isenko
 */
public class FirefoxDownloadTest extends ChromeDownloadTest {
    @BeforeAll
    public static void setup() {
        System.setProperty("webdriver.gecko.driver", Properties.getProperty("geckodriver"));
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    }
}
