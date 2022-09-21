package io.github._7isenko.junitlearning;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

/**
 * @author 7isenko
 */
public abstract class WebPage {
    protected static final Duration DEFAULT_WAIT_DURATION = Duration.ofSeconds(5);

    protected final WebDriver driver;

    public WebPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    protected void waitForVisibility(By by) {
        WebDriverWait wait = new WebDriverWait(driver, DEFAULT_WAIT_DURATION);
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
    }

    protected void waitForPresence(By by) {
        WebDriverWait wait = new WebDriverWait(driver, DEFAULT_WAIT_DURATION);
        wait.until(ExpectedConditions.presenceOfElementLocated(by));
    }
}
