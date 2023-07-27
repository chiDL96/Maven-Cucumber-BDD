package bankGuru.stepDefinitions;

import bankGuru.cucumberOptions.Hooks;
import com.gargoylesoftware.htmlunit.Page;
import commons.PageGeneratorManager;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import org.openqa.selenium.WebDriver;
import pageObjects.RegisterPageObject;

public class RegisterPageSteps {
    WebDriver driver;
    RegisterPageObject registerPage;
    static String username, password;
    public RegisterPageSteps() {
        driver = Hooks.openAndQuitBrowser();
        registerPage = PageGeneratorManager.getRegisterPage(driver);
    }

    @Given("^Open BankGuru Register page$")
    public void openBankGuruRegisterPage(){
        registerPage.openBankGuruRegisterPage();
    }

    @When("^Input to email textbox$")
    public void inputToEmailTextbox(){
        registerPage.enterToEmailTextbox();
        registerPage.clickToSubmitButton();
    }

    @And("^Get username and password info$")
    public void getUsernameAndPasswordInfo(){
        username = registerPage.getUsername();
        password = registerPage.getPassword();
    }
}
