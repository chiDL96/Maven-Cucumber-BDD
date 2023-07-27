package bankGuru.stepDefinitions;

import bankGuru.cucumberOptions.Hooks;
import commons.PageGeneratorManager;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import org.openqa.selenium.WebDriver;
import pageObjects.LoginPageObject;

public class LoginPageSteps {
    WebDriver driver;
    LoginPageObject loginPage;
    public LoginPageSteps() {
        driver = Hooks.openAndQuitBrowser();
        loginPage = PageGeneratorManager.getLoginPage(driver);
    }

    @And("^Open Login page$")
    public void openLoginPage(){
        loginPage.openLoginPage();
    }
    @Then("^Submit username password to Login page$")
    public void submitUsernameAndPasswordToLoginPage(){
        loginPage.inputUsernameAndPassword(RegisterPageSteps.username, RegisterPageSteps.password);
        loginPage.clickToLoginButton();
    }
}
