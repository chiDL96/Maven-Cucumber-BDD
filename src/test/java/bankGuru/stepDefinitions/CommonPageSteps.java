package bankGuru.stepDefinitions;

import bankGuru.cucumberOptions.Hooks;
import commons.PageGeneratorManager;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import pageObjects.CommonPageObject;
import pageObjects.HomePageObject;

public class CommonPageSteps {
    WebDriver driver;
    CommonPageObject commonPage;
    public CommonPageSteps() {
        driver = Hooks.openAndQuitBrowser();
        commonPage = PageGeneratorManager.getCommonPage(driver);
    }

    @Given("^Open \"([^\"]*)\" page$")
    public void openPage(String pageName){
        commonPage.clickToDynamicPageLink(driver, pageName);
    }

    @When("^Input to \"([^\"]*)\" textbox with value \"([^\"]*)\"$")
    public void inputToTextboxWithValue(String fieldName, String inputValue){
        commonPage.inputToDynamicTextbox(driver,fieldName, inputValue);
    }

    @And("^Click to \"([^\"]*)\" radio button with value \"([^\"]*)\"$")
    public void clickToRadioButtonWithValue(String fieldName, String value){
        commonPage.clickToDynamicRadioButton(driver, fieldName, value);
    }

    @And("^Input to \"([^\"]*)\" textarea with value \"([^\"]*)\"$")
    public void inputToTextareaWithValue(String fieldName, String inputValue){
        commonPage.inputToDynamicTextarea(driver, fieldName, inputValue);
    }

    @And("^Click to \"([^\"]*)\" button$")
    public void clickToButton(String fieldName){
        commonPage.clickToDynamicButton(driver, fieldName);
    }

    @Then("^Verify Success message displayed with \"([^\"]*)\"$")
    public void verifySuccessMessageDisplayed(String expectedMessage){
        Assert.assertTrue(commonPage.is);
    }
}
