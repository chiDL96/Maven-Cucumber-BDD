package pageObjects;

import commons.BasePage;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import pageUI.CommonPageUI;
import pageUI.HomePageUI;

public class CommonPageObject extends BasePage {
    WebDriver driver;

    public CommonPageObject(WebDriver driver) {
        this.driver = driver;
    }

    public void clickToDynamicPageLink(WebDriver driver, String pageName){
        waitForElementClickabled(driver, HomePageUI.DYNAMIC_PAGE_LINK, pageName);
        clickToElement(driver, HomePageUI.DYNAMIC_PAGE_LINK, pageName);
    }

    public void clickToDynamicButton(WebDriver driver, String buttonValue){
        waitForElementClickabled(driver, CommonPageUI.DYNAMIC_BUTTON, buttonValue);
        clickToElement(driver, CommonPageUI.DYNAMIC_BUTTON, buttonValue);
    }

    public void inputToDynamicTextbox(WebDriver driver, String fieldName, String inputValue){
        waitForElementVisible(driver, CommonPageUI.DYNAMIC_TEXT_INPUT, fieldName);
        sendKeyToElement(driver, inputValue, CommonPageUI.DYNAMIC_TEXT_INPUT, fieldName);
    }

    public void inputToDynamicTextarea(WebDriver driver, String fieldName, String inputValue){
        waitForElementVisible(driver, CommonPageUI.DYNAMIC_TEXT_AREA, fieldName);
        sendKeyToElement(driver, inputValue, CommonPageUI.DYNAMIC_TEXT_AREA, fieldName);
    }

    public void clickToDynamicRadioButton(WebDriver driver, String fieldName, String value){
        waitForElementClickabled(driver, CommonPageUI.DYNAMIC_RADIO_BUTTON, fieldName, value);
        clickToElement(driver, CommonPageUI.DYNAMIC_RADIO_BUTTON, fieldName, value);
    }
}
