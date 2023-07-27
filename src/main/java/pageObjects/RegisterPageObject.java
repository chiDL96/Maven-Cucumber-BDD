package pageObjects;

import commons.BasePage;
import commons.GlobalConstants;
import org.openqa.selenium.WebDriver;
import pageUI.RegisterPageUI;

public class RegisterPageObject extends BasePage {
    WebDriver driver;

    public RegisterPageObject(WebDriver driver) {
        this.driver = driver;
    }

    public void openBankGuruRegisterPage() {
        openPageURL(driver, GlobalConstants.BANK_GURU_URL);
    }

    public void enterToEmailTextbox() {
        waitForElementVisible(driver, RegisterPageUI.EMAIL_TEXTBOX);
        sendKeyToElement(driver, RegisterPageUI.EMAIL_REGISTER, RegisterPageUI.EMAIL_TEXTBOX);
    }

    public String getUsername() {
        waitForElementVisible(driver, RegisterPageUI.USERID_TEXT);
        return getElementText(driver, RegisterPageUI.USERID_TEXT);
    }

    public String getPassword() {
        waitForElementVisible(driver, RegisterPageUI.PASSWORD_TEXT);
        return getElementText(driver, RegisterPageUI.PASSWORD_TEXT);
    }

    public void clickToSubmitButton() {
        waitForElementClickabled(driver, RegisterPageUI.SUBMIT_BUTTON);
        clickToElement(driver, RegisterPageUI.SUBMIT_BUTTON);
    }
}
