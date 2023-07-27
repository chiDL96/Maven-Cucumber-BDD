package commons;


import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class BasePage {
    public static BasePage getBasePageObject() {
        return new BasePage();
    }

    public void openPageURL(WebDriver driver, String pageURL) {
        driver.get(pageURL);
    }

    protected String getPageTitle(WebDriver driver) {
        return driver.getTitle();
    }

    public String getCurrentURL(WebDriver driver) {
        return driver.getCurrentUrl();
    }

    protected String getPageSource(WebDriver driver) {
        return driver.getPageSource();
    }

    protected void backPage(WebDriver driver) {
        driver.navigate().back();
    }

    protected void forwardPage(WebDriver driver) {
        driver.navigate().forward();
    }

    public void refreshPage(WebDriver driver) {
        driver.navigate().refresh();
    }

    protected Alert waitAlertPresence(WebDriver driver) {
        WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
        return explicitWait.until(ExpectedConditions.alertIsPresent());
    }

    protected void acceptAlert(WebDriver driver) {
        waitAlertPresence(driver).accept();
    }

    protected void cancelAlert(WebDriver driver) {
        waitAlertPresence(driver).dismiss();
    }

    protected String getAlertText(WebDriver driver) {
        return waitAlertPresence(driver).getText();
    }

    protected void sendKeyToAlert(WebDriver driver, String textValue) {
        waitAlertPresence(driver).sendKeys(textValue);
    }

    protected void switchWindowByID(WebDriver driver, String windowID) {
        Set<String> allWindowID = driver.getWindowHandles();
        for (String id : allWindowID) {
            if (id.equals(windowID)) {
                driver.switchTo().window(id);
                break;
            }
        }
    }

    protected void switchWindowByTitle(WebDriver driver, String tabTitle) {
        Set<String> allWindowID = driver.getWindowHandles();
        for (String id : allWindowID) {
            driver.switchTo().window(id);
            String actualTitle = driver.getTitle();
            if (actualTitle.equals(tabTitle)) {
                break;
            }
        }
    }

    protected void closeAllTabWithoutParent(WebDriver driver, String parentID) {
        Set<String> allWindowID = driver.getWindowHandles();
        for (String id : allWindowID) {
            if (!id.equals(parentID)) {
                driver.switchTo().window(id);
                driver.close();
            }
            driver.switchTo().window(parentID);
        }
    }

    protected WebElement getWebElement(WebDriver driver, String locatorType) {
        return driver.findElement(getByLocator(locatorType));
    }

    protected WebElement getWebElement(WebDriver driver, String locatorType, String... dynamicValue) {
        locatorType = getDynamicXpath(locatorType, dynamicValue);
        return driver.findElement(getByLocator(locatorType));
    }

    protected By getByXpath(String xpathLocator) {
        return By.xpath(xpathLocator);
    }

    protected void clickToElement(WebDriver driver, String locatorType, String... values) {
        getWebElement(driver, getDynamicXpath(locatorType, values)).click();
    }

    protected void clickToElement(WebDriver driver, String locatorType) {
        getWebElement(driver, locatorType).click();
    }

    protected void sendKeyToElement(WebDriver driver, String textValue, String locatorType) {
        WebElement element = getWebElement(driver, locatorType);
        element.clear();
        element.sendKeys(textValue);
    }


    protected void clearTextboxByPressKey(WebDriver driver, String locatorType) {
        WebElement element = getWebElement(driver, locatorType);
        if(System.getProperty("os.name").contains("Mac")){
            element.sendKeys(Keys.chord(Keys.COMMAND, "a", Keys.DELETE));
        } else if (System.getProperty("os.name").contains("Window")){
            element.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
        }
    }
    protected void sendKeyToElement(WebDriver driver, String textValue, String locatorType, String... values) {
        WebElement element = getWebElement(driver, getDynamicXpath(locatorType, values));
        element.clear();
        element.sendKeys(textValue);
    }

    protected String getElementText(WebDriver driver, String locatorType) {
        return getWebElement(driver, locatorType).getText();
    }
    protected String getElementText(WebDriver driver, String locatorType, String... dynamicValue) {
        return getWebElement(driver, getDynamicXpath(locatorType,dynamicValue)).getText();
    }

    protected void selectItemInDefaultDropdownByValue(WebDriver driver, String locatorType, String valueItem) {
        Select select = new Select(getWebElement(driver, locatorType));
        select.selectByValue(valueItem);
    }

    protected void selectItemInDefaultDropdownByValue(WebDriver driver, String valueItem, String locatorType, String... dynamicValue) {
        Select select = new Select(getWebElement(driver, locatorType, dynamicValue));
        select.selectByValue(valueItem);
    }

    protected void selectItemInDefaultDropdownByText(WebDriver driver, String locatorType, String textItem) {
        Select select = new Select(getWebElement(driver, locatorType));
        select.selectByVisibleText(textItem);
    }

    protected void selectItemInDefaultDropdownByText(WebDriver driver, String textItem, String locatorType, String... values) {
        Select select = new Select(getWebElement(driver, getDynamicXpath(locatorType, values)));
        select.selectByVisibleText(textItem);
    }

    protected String getSelectedItemInDefaultDropdown(WebDriver driver, String locatorType, String textItem) {
        Select select = new Select(getWebElement(driver, locatorType));
        return select.getFirstSelectedOption().getText();
    }

    protected Boolean isDropdownMultiple(WebDriver driver, String locatorType) {
        Select select = new Select(getWebElement(driver, locatorType));
        return select.isMultiple();
    }

    protected void selectItemInCustomDropdown(WebDriver driver, String parentLocatorType, String childItemLocatorType, String expectedItem) {
        getWebElement(driver, parentLocatorType).click();
        sleepInSecond(1);

        WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
        List<WebElement> allItems = explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(getByLocator(childItemLocatorType)));

        for (WebElement item : allItems) {
            if (item.getText().trim().equals(expectedItem)) {
                JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
                jsExecutor.executeScript("arguments[0].scrollIntoView(true);", item);
                sleepInSecond(1);

                item.click();
                sleepInSecond(1);
                break;
            }
        }
    }

    protected String getWebElementAttribute(WebDriver driver, String locatorType, String attributeName) {
        return getWebElement(driver, locatorType).getAttribute(attributeName);
    }

    protected String getWebElementAttribute(WebDriver driver, String locatorType, String attributeName, String... dynamicValue) {
        return getWebElement(driver, getDynamicXpath(locatorType, dynamicValue)).getAttribute(attributeName);
    }

    protected String getElementCssValue(WebDriver driver, String locatorType, String propertyName) {
        return getWebElement(driver, locatorType).getCssValue(propertyName);
    }

    protected String getHexaColorFromRGBA(String rgbaValue) {
        return Color.fromString(rgbaValue).asHex();
    }

    protected int getElementSize(WebDriver driver, String locatorType) {
        return driver.findElements(getByLocator(locatorType)).size();
    }

    protected int getElementSize(WebDriver driver, String locatorType, String... values) {
        return driver.findElements(getByLocator(getDynamicXpath(locatorType, values))).size();
    }

    protected void checkToDefaultCheckboxRadio(WebDriver driver, String locatorType) {
        WebElement element = getWebElement(driver, locatorType);
        if (!element.isSelected()) {
            element.click();
        }
    }

    protected void checkToDefaultCheckboxRadio(WebDriver driver, String locatorType, String... values) {
        WebElement element = getWebElement(driver, getDynamicXpath(locatorType, values));
        if (!element.isSelected()) {
            element.click();
        }
    }

    protected void unCheckToDefaultCheckboxRadio(WebDriver driver, String locatorType) {
        WebElement element = getWebElement(driver, locatorType);
        if (element.isSelected()) {
            element.click();
        }
    }

    protected void unCheckToDefaultCheckboxRadio(WebDriver driver, String locatorType, String... values) {
        WebElement element = getWebElement(driver, getDynamicXpath(locatorType, values));
        if (element.isSelected()) {
            element.click();
        }
    }

    protected Boolean isElementDisplayed(WebDriver driver, String locatorType) {
        try {
            //Tim kiem element
            //Displayed trong DOM: Tim thay thi tra true
            //unDisplayed - nhung co trong DOM: Khong tim thay thi tra false
            return getWebElement(driver, locatorType).isDisplayed();
        } catch (NoSuchElementException e){
            //unDisplayed - nhung k co trong DOM: Khong tim thay thi tra false
            return false;
        }
    }

    protected Boolean isElementDisplayed(WebDriver driver, String locatorType, String... values) {
        try {
            return getWebElement(driver, getDynamicXpath(locatorType, values)).isDisplayed();
        } catch (NoSuchElementException e){
            return false;
        }
    }

    protected void overrideGlobalTimeout (WebDriver driver, long timeOut){
        driver.manage().timeouts().implicitlyWait(timeOut, TimeUnit.SECONDS);
    }

    //in DOM or not in DOM and override implicit timeout
    public void waitForElementUndisplayed(WebDriver driver, String locatorType){
        WebDriverWait explicitWait = new WebDriverWait(driver, shortTimeout);
        overrideGlobalTimeout(driver, shortTimeout);
        explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(getByLocator(locatorType)));
        overrideGlobalTimeout(driver, longTimeout);
    }

    protected boolean isElementUndisplayed(WebDriver driver, String locatorType){
         overrideGlobalTimeout(driver, shortTimeout);

         List<WebElement> elements = getListWebElement(driver, locatorType);

         overrideGlobalTimeout(driver, longTimeout);

         if (elements.size() == 0){
             System.out.println("Element khong co trong DOM");
             return true;
         }
         //No co kich thuoc = 1 (co trong DOM)
         //Nhung k duoc hien thi
         else if (elements.size() > 0 && !elements.get(0).isDisplayed()){
             System.out.println("Element co trong DOM nhung k visible/ displayed");
             return true;
         }
         else {
             System.out.println("Element co trong DOM va visible");
             return false;
         }
    }

    protected boolean isElementUndisplayed(WebDriver driver, String locatorType, String... dynamicLocator){
        overrideGlobalTimeout(driver, shortTimeout);
        List<WebElement> elements = getListWebElement(driver, getDynamicXpath(locatorType, dynamicLocator));
        overrideGlobalTimeout(driver, longTimeout);
        if (elements.size() == 0){
            System.out.println("Element khong co trong DOM");
            return true;
        }
        else if (elements.size() > 0 && !elements.get(0).isDisplayed()){
            System.out.println("Element co trong DOM nhung k visible/ displayed");
            return true;
        }
        else {
            System.out.println("Element co trong DOM va visible");
            return false;
        }
    }

    protected Boolean isElementEnabled(WebDriver driver, String locatorType) {
        return getWebElement(driver, locatorType).isEnabled();
    }

    protected Boolean isElementSelected(WebDriver driver, String locatorType) {
        return getWebElement(driver, locatorType).isSelected();
    }

    protected Boolean isElementSelected(WebDriver driver, String locatorType, String... values) {
        return getWebElement(driver, getDynamicXpath(locatorType, values)).isSelected();
    }

    protected void switchToFrameIframe(WebDriver driver, String locatorType) {
        driver.switchTo().frame(getWebElement(driver, locatorType));
    }

    protected void switchToDefaultContent(WebDriver driver) {
        driver.switchTo().defaultContent();
    }

    protected void hoverMouseToElement(WebDriver driver, String locatorType) {
        Actions action = new Actions(driver);
        action.moveToElement(getWebElement(driver, locatorType)).perform();
    }

    protected void pressKeyToElement(WebDriver driver, String locatorType, Keys key) {
        Actions action = new Actions(driver);
        action.sendKeys(getWebElement(driver, locatorType), key).perform();
    }

    protected void pressKeyToElement(WebDriver driver, String locatorType, Keys key, String... dynamicValue) {
        Actions action = new Actions(driver);
        action.sendKeys(getWebElement(driver, getDynamicXpath(locatorType, dynamicValue)), key).perform();
    }

    protected void scrollToBottomPage(WebDriver driver) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("window.scrollBy(0,document.body.scrollHeight)");
    }

    protected void highlightElement(WebDriver driver, String locatorType) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        WebElement element = getWebElement(driver, locatorType);
        String originalStyle = element.getAttribute("style");
        jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style", "border: 2px solid red; border-style: dashed;");
        sleepInSecond(1);
        jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style", originalStyle);
    }

    protected void clickToElementByJS(WebDriver driver, String locatorType) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("arguments[0].click();", getWebElement(driver, locatorType));
    }
    protected void clickToElementByJS(WebDriver driver, String locatorType, String... dynamicValue) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("arguments[0].click();", getWebElement(driver, getDynamicXpath(locatorType, dynamicValue)));
    }

    protected void scrollToElement(WebDriver driver, String locatorType) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("arguments[0].scrollIntoView(true);", getWebElement(driver, locatorType));
    }

    protected void scrollToElement(WebDriver driver, String locatorType, String... dynamicValue) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("arguments[0].scrollIntoView(true);", getByLocator(locatorType));
    }
    protected void removeAttributeInDOM(WebDriver driver, String locatorType, String attributeRemove) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("arguments[0].removeAttribute('" + attributeRemove + "');", getWebElement(driver, locatorType));
    }

    protected boolean areJQueryAndJSLoadedSuccess(WebDriver driver) {
        WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;

        ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                try {
                    return ((Long) jsExecutor.executeScript("return jQuery.active") == 0);
                } catch (Exception e) {
                    return true;
                }
            }
        };

        ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                return jsExecutor.executeScript("return document.readyState").toString().equals("complete");
            }
        };

        return explicitWait.until(jQueryLoad) && explicitWait.until(jsLoad);
    }

    protected String getElementValidationMessage(WebDriver driver, String locatorType) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        return (String) jsExecutor.executeScript("return arguments[0].validationMessage;", getWebElement(driver, locatorType));
    }

    protected boolean isImageLoaded(WebDriver driver, String locatorType) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        boolean status = (boolean) jsExecutor.executeScript("return arguments[0].complete && typeof arguments[0].naturalWidth != \"undefined\" && arguments[0].naturalWidth > 0", getWebElement(driver, locatorType));
        if (status) {
            return true;
        } else {
            return false;
        }
    }

    protected void waitForElementVisible(WebDriver driver, String locatorType) {
        WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(getByLocator(locatorType)));
    }


    //driver, xpath=//div[contains(@class.'account-navigation')]//a[text()='%s'], %s = 'Customer info'
    protected void waitForElementVisible(WebDriver driver, String locatorType, String... dynamicValue) {
        WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
        locatorType = getDynamicXpath(locatorType, dynamicValue);
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(getByLocator(locatorType)));
    }

    protected void waitForAllElementVisible(WebDriver driver, String locatorType) {
        WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
        explicitWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(getByLocator(locatorType)));
    }

    protected void waitForElementInvisible(WebDriver driver, String locatorType) {
        WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
        explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(getByLocator(locatorType)));
    }

    protected void waitForAllElementInvisible(WebDriver driver, String locatorType) {
        WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
        explicitWait.until(ExpectedConditions.invisibilityOfAllElements(getListWebElement(driver, locatorType)));
    }

    protected void waitForElementClickabled(WebDriver driver, String locatorType) {
        WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
        explicitWait.until(ExpectedConditions.elementToBeClickable(getByLocator(locatorType)));
    }

    protected void waitForElementClickabled(WebDriver driver, String locatorType, String... dynamicValue) {
        WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
        locatorType = getDynamicXpath(locatorType, dynamicValue);
        explicitWait.until(ExpectedConditions.elementToBeClickable(getByLocator(locatorType)));
    }
    protected List getListWebElement(WebDriver driver, String locatorType) {
        return driver.findElements(getByLocator(locatorType));
    }

    protected List getListWebElement(WebDriver driver, String locatorType, String... dynamicValue) {
        return driver.findElements(getByLocator(getDynamicXpath(locatorType, dynamicValue)));
    }


    public WebElement GetShadowDom(String locatorType, WebDriver driver) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        WebElement element = (WebElement) jsExecutor.executeScript("arguments[0].shadowRoot;", getWebElement(driver, locatorType));
        return element;
    }

    private By getByLocator(String locatorType) {
        By by = null;
//        System.out.println("Locator type = " + locatorType);
        if (locatorType.startsWith("id=") || locatorType.startsWith("ID=") || locatorType.startsWith("Id=")) {
            by = By.id(locatorType.substring(3));
        } else if (locatorType.startsWith("class=") || locatorType.startsWith("CLASS=") || locatorType.startsWith("Class=")) {
            by = By.className(locatorType.substring(6));
        } else if (locatorType.startsWith("name=") || locatorType.startsWith("NAME=") || locatorType.startsWith("Name=")) {
            by = By.name(locatorType.substring(5));
        } else if (locatorType.startsWith("css=") || locatorType.startsWith("CSS=") || locatorType.startsWith("Css=")) {
            by = By.cssSelector(locatorType.substring(4));
        } else if (locatorType.startsWith("xpath=") || locatorType.startsWith("Xpath=") || locatorType.startsWith("XPath=") || locatorType.startsWith("XPATH=")) {
            by = By.xpath(locatorType.substring(6));
        } else {
            throw new RuntimeException("Locator type is not supported");
        }

        return by;
    }

    private String getDynamicXpath(String locatorType, String... values) {
        if (locatorType.startsWith("xpath=") || locatorType.startsWith("Xpath=") || locatorType.startsWith("XPath=") || locatorType.startsWith("XPATH=")) {
            locatorType = String.format(locatorType, (Object[]) values);
        }
        return locatorType;
    }


    public Boolean isFileLoadedByName(WebDriver driver, String locatorType, String... dynamicValue) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        boolean status = (boolean) jsExecutor.executeScript("return arguments[0].complete && typeof arguments[0].naturalWidth != \"undefined\" && arguments[0].naturalWidth > 0", getWebElement(driver, getDynamicXpath(locatorType, dynamicValue)));
        return status;
    }

    private long longTimeout = GlobalConstants.LONG_TIMEOUT;
    private long shortTimeout = GlobalConstants.SHORT_TIMEOUT;

    protected void sleepInSecond(long timeInSecond) {
        try {
            Thread.sleep(timeInSecond * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void inputToTextboxByName(WebDriver driver, String textValue, String locatorType, String... dynamicValue) {
        waitForElementVisible(driver, locatorType, dynamicValue);
        sendKeyToElement(driver, textValue, locatorType, dynamicValue);
    }

    public void clickToPageLinkByClassName(WebDriver driver, String locatorType, String... dynamicValue) {
        waitForElementClickabled(driver, locatorType, dynamicValue);
        clickToElement(driver, locatorType, dynamicValue);
    }

    public void clickToButtonByName(WebDriver driver, String locatorType, String... dynamicValue) {
        waitForElementClickabled(driver, locatorType, dynamicValue);
        clickToElement(driver, locatorType, dynamicValue);
    }

    public void selectToDropdownByValueAttribute(WebDriver driver, String value, String locatorType, String dynamicValue) {
        waitForElementClickabled(driver, locatorType, dynamicValue);
        selectItemInDefaultDropdownByText(driver, value, locatorType, dynamicValue);
    }

    public void selectToCheckboxByName(WebDriver driver, String locatorType, String dynamicValue) {
        waitForElementClickabled(driver, locatorType, dynamicValue);
        checkToDefaultCheckboxRadio(driver, locatorType, dynamicValue);
    }

}
