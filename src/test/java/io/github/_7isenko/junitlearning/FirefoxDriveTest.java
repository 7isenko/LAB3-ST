package io.github._7isenko.junitlearning;

import io.github._7isenko.junitlearning.utils.Properties;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;

import java.io.File;
import java.time.Duration;

/**
 * @author 7isenko
 */
public class FirefoxDriveTest extends ChromeDriveTest {
    @BeforeAll
    public static void setup() {
        System.setProperty("webdriver.gecko.driver", Properties.getProperty("geckodriver"));
        FirefoxOptions options = new FirefoxOptions();
        FirefoxProfile profile = new FirefoxProfile(new File(Properties.getProperty("userprofilegecko")));
        options.setCapability("dom.file.createInChild", true);
        options.setProfile(profile);
        driver = new FirefoxDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        drivePage = new DrivePage(driver);
        driveAccessIFrame = new DriveAccessIFrame(driver);

        driver.get(Properties.getProperty("drivepage"));
    }

    @Override
    protected WebDriver getDriverNoProfile() {
        System.setProperty("webdriver.gecko.driver", Properties.getProperty("geckodriver"));
        WebDriver driverNoProfile = new FirefoxDriver();
        driverNoProfile.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        return driverNoProfile;
    }
}
