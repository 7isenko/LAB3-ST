package io.github._7isenko.junitlearning.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.time.Duration;

/**
 * @author 7isenko
 */
public class ActionUtils {
    public static void rightClick(WebDriver driver, WebElement element) {
        Actions action = new Actions(driver);
        action.moveToElement(element);
        action.contextClick();
        action.perform();
    }

    public static void leftClick(WebDriver driver, WebElement element) {
        Actions action = new Actions(driver);
        action.moveToElement(element);
        action.pause(Duration.ofSeconds(1));
        action.click();
        action.perform();
    }
}
