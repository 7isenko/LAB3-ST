package io.github._7isenko.junitlearning;

import io.github._7isenko.junitlearning.pages.DownloadPage;
import io.github._7isenko.junitlearning.utils.Properties;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

/**
 * @author 7isenko
 */
@TestMethodOrder(MethodOrderer.MethodName.class)
public class ChromeDownloadTest {

    protected static final String ACCESSIBLE_LINK = "https://drive.google.com/file/d/1cysw860ctQFtCIMf7mSm3awGRL0mxOSE/view?usp=sharing";
    protected static final String NOT_ACCESSIBLE_LINK = "https://drive.google.com/file/d/1jNmN2QImBRY_MPBueFPDyybYp3mFYaUs/view?usp=sharing";

    protected static WebDriver driver;
    protected static DownloadPage downloadPage;

    @BeforeAll
    public static void setup() {
        System.setProperty("webdriver.chrome.driver", Properties.getProperty("chromedriver"));
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    }

    @Test @DisplayName("File with access should be downloadable")
    void downloadWithAccess() {
        downloadPage = new DownloadPage(driver);
        driver.get(ACCESSIBLE_LINK);
        Assertions.assertTrue(downloadPage.isDownloadable());
    }

    @Test @DisplayName("File without access should not be downloadable")
    void downloadWithNoAccess() {
        downloadPage = new DownloadPage(driver);
        driver.get(NOT_ACCESSIBLE_LINK);
        Assertions.assertFalse(downloadPage.isDownloadable());
    }

    @AfterAll
    public static void closeBrowser() {
        driver.quit();
    }


}
