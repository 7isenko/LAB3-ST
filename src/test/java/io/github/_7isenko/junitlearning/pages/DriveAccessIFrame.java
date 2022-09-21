package io.github._7isenko.junitlearning.pages;

import io.github._7isenko.junitlearning.utils.ActionUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * @author 7isenko
 */
public class DriveAccessIFrame extends WebPage{
    public DriveAccessIFrame(WebDriver driver) {
        super(driver);
    }

    private static final String IFRAME_XPATH = "//iframe[@class = 'ea-Rc-x-Vc']";
    private static final String ACCESS_MENU_XPATH = "//button[@class = 'VfPpkd-LgbsSe VfPpkd-LgbsSe-OWXEXe-Bz112c-UbuQg LjDxcd XhPA0b LQeN7 QdKnMc S9uFJc']";
    private static final String ACCESS_WITH_LINK_XPATH = "//span[text() = 'Все, у кого есть ссылка']";
    private static final String ACCESS_IS_GIVEN_XPATH = "//div[text() = 'Просматривать могут все в интернете, у кого есть эта ссылка.']";
    private static final String COPY_LINK_BUTTON_XPATH = "//span[text() = 'Копировать ссылку']";
    private static final String READY_BUTTON_XPATH = "//span[text() = 'Готово']";

    public void switchToIFrame() {
        waitForVisibility(By.xpath(IFRAME_XPATH));
        driver.switchTo().frame(driver.findElement(By.xpath(IFRAME_XPATH)));
    }

    public void openAccessMenu() {
        waitForVisibility(By.xpath(ACCESS_MENU_XPATH));
        WebElement accessMenu = driver.findElement(By.xpath(ACCESS_MENU_XPATH));
        ActionUtils.leftClick(driver, accessMenu);
    }

    public void clickAccessWithLink() {
        waitForVisibility(By.xpath(ACCESS_WITH_LINK_XPATH));
        driver.findElement(By.xpath(ACCESS_WITH_LINK_XPATH)).click();
    }

    public boolean isAccessGiven() {
        waitForVisibility(By.xpath(ACCESS_IS_GIVEN_XPATH));
        return !driver.findElements(By.xpath(ACCESS_IS_GIVEN_XPATH)).isEmpty();
    }

    public void copyLink() {
        waitForVisibility(By.xpath(COPY_LINK_BUTTON_XPATH));
        driver.findElement(By.xpath(COPY_LINK_BUTTON_XPATH)).click();
    }

    public void closeMenu() {
        waitForVisibility(By.xpath(READY_BUTTON_XPATH));
        driver.findElement(By.xpath(READY_BUTTON_XPATH)).click();
    }

}
