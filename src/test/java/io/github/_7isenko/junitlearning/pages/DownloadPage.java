package io.github._7isenko.junitlearning.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class DownloadPage extends WebPage {
    public DownloadPage(WebDriver driver) {
        super(driver);
    }

    private static final String DOWNLOAD_BUTTON_XPATH = "//div[contains(text(), 'Скачать')]";

    @FindBy(xpath = DOWNLOAD_BUTTON_XPATH)
    private WebElement downloadButton;

    public boolean isDownloadable() {
        try {
            downloadButton.isEnabled();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}