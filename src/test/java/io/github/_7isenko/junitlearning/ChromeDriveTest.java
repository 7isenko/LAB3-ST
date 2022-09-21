package io.github._7isenko.junitlearning;

import io.github._7isenko.junitlearning.utils.UserInputUtils;
import io.github._7isenko.junitlearning.utils.Properties;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;

/**
 * @author 7isenko
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ChromeDriveTest {

    protected static DrivePage drivePage;
    protected static DriveAccessIFrame driveAccessIFrame;
    protected static WebDriver driver;

    @BeforeAll
    public static void setup() {
        System.setProperty("webdriver.chrome.driver", Properties.getProperty("chromedriver"));
        ChromeOptions options = new ChromeOptions();
        options.addArguments("user-data-dir=" + Properties.getProperty("userdatadir"));
        options.addArguments("profile-directory=" + Properties.getProperty("userprofile"));
        options.addArguments("disable-extensions");
        driver = new ChromeDriver(ChromeDriverService.createDefaultService(), options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        drivePage = new DrivePage(driver);
        driveAccessIFrame = new DriveAccessIFrame(driver);

        driver.get(Properties.getProperty("drivepage"));

    }

    @Test
    @Order(1)
    @DisplayName("Should upload file")
    void fileUploadTest() {
        drivePage.openMenu();
        drivePage.uploadButtonClick();
        drivePage.uploadToFileInput(Properties.getProperty("uploadfilepath") + Properties.getProperty("uploadfilename"));
        Assertions.assertTrue(drivePage.isUploadedFileAccessible());
    }

    @Test
    @Order(2)
    @DisplayName("Should change file access to \"by link\"")
    void accessTest() {
        drivePage.openUploadedFileContextMenu();
        drivePage.pressAccessOnUploadedFileMenu();
        driveAccessIFrame.switchToIFrame();
        driveAccessIFrame.openAccessMenu();
        driveAccessIFrame.clickAccessWithLink();
        Assertions.assertTrue(driveAccessIFrame.isAccessGiven());
        driveAccessIFrame.copyLink();
        driveAccessIFrame.closeMenu();
        drivePage.switchToDefaultContent();
    }

    @Test
    @Order(3)
    @DisplayName("Link should lead to downloadable file by guest")
    void accessLinkTest() {
        WebDriver driverNoProfile = getDriverNoProfile();
        DownloadPage downloadPage = new DownloadPage(driverNoProfile);
        driverNoProfile.get(UserInputUtils.getClipboard());

        Assertions.assertTrue(downloadPage.isDownloadable());
        driverNoProfile.quit();
    }

    @Test
    @Order(4) @DisplayName("Should create directory")
    void createDirectoryTest() {
        drivePage.openMenu();
        drivePage.newDirectoryButtonClick();
        drivePage.fillNewDirectoryInput();
        Assertions.assertTrue(drivePage.isDirectoryAccessible());
    }

    @Test
    @Order(5) @DisplayName("Should drag and drop file, so file must be moved")
    void dragAndDropFileTest() {
        drivePage.dragAndDropFile();
        Assertions.assertFalse(drivePage.isUploadedFileAccessible());
    }

    @Test
    @Order(6) @DisplayName("Should drag and drop file back, so directory is empty")
    void moveFileBackTest() {
        drivePage.moveToDirectory();
        drivePage.moveFileBack();
        Assertions.assertTrue(drivePage.isDirectoryEmpty());
    }

    @Test
    @Order(7) @DisplayName("Should encounter file on main page")
    void fileIsAvailableInMainPage() {
        drivePage.moveToMainPage();
        Assertions.assertTrue(drivePage.isUploadedFileAccessible());
    }


    @Test
    @Order(8) @DisplayName("Should delete file")
    void deleteFileTest() {
        drivePage.openUploadedFileContextMenu();
        drivePage.pressDeleteOnContextMenu();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        Assertions.assertFalse(drivePage.isUploadedFileAccessible());
    }

    @Test
    @Order(9) @DisplayName("Should delete directory")
    void deleteDirectoryTest() {
        drivePage.openDirectoryContextMenu();
        drivePage.pressDeleteOnContextMenu();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        Assertions.assertFalse(drivePage.isDirectoryAccessible());
    }

    @AfterAll
    public static void finish() {
        driver.quit();
    }

    protected WebDriver getDriverNoProfile() {
        System.setProperty("webdriver.chrome.driver", Properties.getProperty("chromedriver"));
        WebDriver driverNoProfile = new ChromeDriver();
        driverNoProfile.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        return driverNoProfile;
    }
}
