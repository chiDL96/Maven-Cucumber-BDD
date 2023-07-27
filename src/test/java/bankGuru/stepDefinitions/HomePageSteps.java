package bankGuru.stepDefinitions;

import bankGuru.cucumberOptions.Hooks;
import commons.PageGeneratorManager;
import cucumber.api.java.en.And;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import pageObjects.HomePageObject;

public class HomePageSteps {
    WebDriver driver;
    HomePageObject homePage;
    public HomePageSteps() {
        driver = Hooks.openAndQuitBrowser();
        homePage = PageGeneratorManager.getHomePage(driver);
    }

    @And("^Verify Home page displayed$")
    public void verifyHomePageDisplayed(){
        Assert.assertTrue(homePage.isWelcomeTextDisplayed(RegisterPageSteps.username));
    }
}
