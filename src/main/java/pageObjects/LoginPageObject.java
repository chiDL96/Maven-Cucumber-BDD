package pageObjects;

import commons.BasePage;
import commons.GlobalConstants;
import org.openqa.selenium.WebDriver;
import pageUI.LoginPageUI;

public class LoginPageObject extends BasePage {
    WebDriver driver;

    public LoginPageObject(WebDriver driver) {
        this.driver = driver;
    }

    public void openLoginPage() {
        openPageURL(driver, GlobalConstants.BANK_GURU_LOGIN_URL);
    }

    public void inputUsernameAndPassword(String userid, String password) {
        waitForElementVisible(driver, LoginPageUI.USERID_TEXTBOX);
        sendKeyToElement(driver, userid, LoginPageUI.USERID_TEXTBOX);
        waitForElementVisible(driver, LoginPageUI.PASSWORD_TEXTBOX);
        sendKeyToElement(driver, password, LoginPageUI.PASSWORD_TEXTBOX);
    }

    public void clickToLoginButton() {
        waitForElementClickabled(driver, LoginPageUI.LOGIN_BUTTON);
        clickToElement(driver, LoginPageUI.LOGIN_BUTTON);
    }
}
