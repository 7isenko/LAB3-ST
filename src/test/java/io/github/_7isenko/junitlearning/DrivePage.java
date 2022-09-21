package io.github._7isenko.junitlearning;

import io.github._7isenko.junitlearning.utils.ActionUtils;
import io.github._7isenko.junitlearning.utils.UserInputUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import java.time.Duration;

// page_url = https://drive.google.com/drive/my-drive
public class DrivePage extends WebPage {

    public DrivePage(WebDriver driver) {
        super(driver);
    }

    public void switchToDefaultContent() {
        driver.switchTo().defaultContent();
    }

    private static final String MENU_BUTTON_X_PATH = "//*[contains(text(), 'Создать')]";
    @FindBy(xpath = MENU_BUTTON_X_PATH)
    private WebElement menuButton;

    private static final String UPLOAD_BUTTON_X_PATH = "//*[contains(text(), 'Загрузить файлы')]";
    @FindBy(xpath = UPLOAD_BUTTON_X_PATH)
    private WebElement uploadButton;

    private static final String NEW_DIRECTORY_BUTTON_X_PATH = "//*[contains(text(), 'Создать папку')]";
    @FindBy(xpath = NEW_DIRECTORY_BUTTON_X_PATH)
    private WebElement newDirectoryButton;

    private static final String FILE_INPUT_X_PATH = "//*[contains(@type, 'file')]";
    @FindBy(xpath = FILE_INPUT_X_PATH)
    private WebElement fileInput;

    private static final String UPLOADED_FILE_X_PATH = "//div[@class = 'gudAKb']";
    @FindBy(xpath = UPLOADED_FILE_X_PATH)
    private WebElement uploadedFile;

    private static final String UPLOADED_FILE_DELETE_BUTTON_CSS = "[aria-label = 'Удалить']";
    @FindBy(css = UPLOADED_FILE_DELETE_BUTTON_CSS)
    private WebElement uploadedFileDeleteButton;

    private static final String UPLOADED_FILE_ACCESS_BUTTON_X_PATH = "//div[contains(@aria-label, 'Открыть доступ к объекту \"lab3.drawio\"')]";
    @FindBy(xpath = UPLOADED_FILE_ACCESS_BUTTON_X_PATH)
    private WebElement uploadedFileAccessButton;

    private static final String NEW_DIRECTORY_INPUT_X_PATH = "//button/span[text() = 'Создать']";
    @FindBy(xpath = NEW_DIRECTORY_INPUT_X_PATH)
    private WebElement newDirectoryInput;

    private static final String DIRECTORY_X_PATH = "//div[@class = 'Q5txwe' and text() = 'Без названия']";
    @FindBy(xpath = DIRECTORY_X_PATH)
    private WebElement directory;

    private static final String MY_DISK_X_PATH = "//div[@class = 'o-Yc-o-T' and text() = 'Мой диск']";
    @FindBy(xpath = MY_DISK_X_PATH)
    private WebElement myDisk;

    private static final String EMPTY_DIR_NOTIFY_X_PATH = "//div[text() = 'Перетащите файлы сюда']";

    public void openMenu() {
        waitForVisibility(By.xpath(MENU_BUTTON_X_PATH));
        ActionUtils.leftClick(driver, menuButton);
    }

    public void uploadButtonClick() {
        waitForVisibility(By.xpath(UPLOAD_BUTTON_X_PATH));
        uploadButton.click();
    }

    public void newDirectoryButtonClick() {
        waitForVisibility(By.xpath(NEW_DIRECTORY_BUTTON_X_PATH));
        newDirectoryButton.click();
    }

    public void fillNewDirectoryInput() {
        newDirectoryInput.click();
    }

    public boolean isDirectoryAccessible() {
        return !driver.findElements(By.xpath(DIRECTORY_X_PATH)).isEmpty();
    }

    public void uploadToFileInput(String file) {
        waitForPresence(By.xpath(FILE_INPUT_X_PATH));
        fileInput.sendKeys(file);
        UserInputUtils.pressEsc();
    }

    public boolean isUploadedFileAccessible() {
        return !driver.findElements(By.xpath(UPLOADED_FILE_X_PATH)).isEmpty();
    }

    public void openUploadedFileContextMenu() {
        waitForVisibility(By.xpath(UPLOADED_FILE_X_PATH));
        ActionUtils.rightClick(driver, uploadedFile);
    }

    public void openDirectoryContextMenu() {
        waitForVisibility(By.xpath(DIRECTORY_X_PATH));
        ActionUtils.rightClick(driver, directory);
    }

    public void pressAccessOnUploadedFileMenu() {
        waitForVisibility(By.xpath(UPLOADED_FILE_ACCESS_BUTTON_X_PATH));
        ActionUtils.leftClick(driver, uploadedFileAccessButton);
    }

    public void pressDeleteOnContextMenu() {
        waitForVisibility(By.cssSelector(UPLOADED_FILE_DELETE_BUTTON_CSS));
        ActionUtils.leftClick(driver, uploadedFileDeleteButton);
    }

    public void dragAndDropFile() {
        Actions actions = new Actions(driver);
        actions.clickAndHold(uploadedFile);
        actions.pause(Duration.ofSeconds(1));
        actions.moveToElement(directory);
        actions.pause(Duration.ofSeconds(1));
        actions.release();
        actions.pause(Duration.ofSeconds(2));
        actions.build().perform();
    }

    public void moveToDirectory() {
        Actions actions = new Actions(driver);
        actions.doubleClick(directory);
        actions.build().perform();
    }

    public void moveFileBack() {
        Actions actions = new Actions(driver);
        actions.clickAndHold(uploadedFile);
        actions.pause(Duration.ofSeconds(1));
        actions.moveToElement(myDisk);
        actions.pause(Duration.ofSeconds(1));
        actions.release();
        actions.pause(Duration.ofSeconds(2));
        actions.build().perform();
    }

    public boolean isDirectoryEmpty() {
        return !driver.findElements(By.xpath(EMPTY_DIR_NOTIFY_X_PATH)).isEmpty();
    }

    public void moveToMainPage() {
        Actions actions = new Actions(driver);
        actions.click(myDisk);
        actions.build().perform();
    }
}
