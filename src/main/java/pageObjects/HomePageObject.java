package pageObjects;

import commons.BasePage;
import org.openqa.selenium.WebDriver;
import pageUI.HomePageUI;

public class HomePageObject extends BasePage {
    WebDriver driver;

    public HomePageObject(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isWelcomeTextDisplayed(String username) {
        waitForElementVisible(driver, HomePageUI.HOME_PAGE_WELCOME, username);
        return isElementDisplayed(driver, HomePageUI.HOME_PAGE_WELCOME, username);
    }
}
