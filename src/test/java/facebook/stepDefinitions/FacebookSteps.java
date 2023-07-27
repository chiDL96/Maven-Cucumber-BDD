package facebook.stepDefinitions;

import cucumber.api.DataTable;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


import java.util.List;
import java.util.Map;

public class FacebookSteps {
    public WebDriver driver;
//    @Given("^Open facebook application$")
    @Before("@Login")
    public void openFacebookApplication(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("https://www.facebook.com/");
    }


//    @And("^Closed application$")
    @After("@Login")
    public void closedApplication(){
        driver.quit();
    }


    @When("^Enter to Email textbox$")
    public void enterToEmailTextbox(){
        driver.findElement(By.id("email")).clear();
        driver.findElement(By.id("email")).sendKeys("chidl@gmail.com");
    }

    @When("^Enter to Password textbox$")
    public void enterToPasswordTextbox(){
        driver.findElement(By.id("pass")).clear();
        driver.findElement(By.id("pass")).sendKeys("12345678");
    }

    @When("^Enter to Email textbox with \"([^\"]*)\"$")
    public void enterToEmailTextboxWith(String email){
        driver.findElement(By.id("email")).clear();
        driver.findElement(By.id("email")).sendKeys(email);
    }

    @When("^Enter to Email textbox with ([^\"]*)$")
    public void enterToEmailTextbox(String email){
        driver.findElement(By.id("email")).clear();
        driver.findElement(By.id("email")).sendKeys(email);
    }

    @When("^Enter to Password textbox with \"([^\"]*)\"$")
    public void enterToPasswordTextboxWith(String password){
        driver.findElement(By.id("pass")).clear();
        driver.findElement(By.id("pass")).sendKeys(password);
    }

    @When("^Enter to Password textbox with ([^\"]*)$")
    public void enterToPasswordTextbox(String password){
        driver.findElement(By.id("pass")).clear();
        driver.findElement(By.id("pass")).sendKeys(password);
    }

    @When("^Enter to Email textbox with \"([^\"]*)\" and Password textbox with \"([^\"]*)\"$")
    public void enterToEmailTextboxAndPasswordTextbox(String email, String password){
        driver.findElement(By.id("email")).clear();
        driver.findElement(By.id("email")).sendKeys(email);
        driver.findElement(By.id("pass")).clear();
        driver.findElement(By.id("pass")).sendKeys(password);
    }

    @When("^Input to Username and Password$")
    public void inputToEmailAndPassword(DataTable dataTable){
        List<Map< String, String >> items = dataTable.asMaps(String.class, String.class);

        driver.findElement(By.id("email")).clear();
        driver.findElement(By.id("email")).sendKeys(items.get(0).get("Username"));
        driver.findElement(By.id("pass")).clear();
        driver.findElement(By.id("pass")).sendKeys(items.get(0).get("Password"));

        driver.findElement(By.id("email")).clear();
        driver.findElement(By.id("email")).sendKeys(items.get(1).get("Username"));
        driver.findElement(By.id("pass")).clear();
        driver.findElement(By.id("pass")).sendKeys(items.get(1).get("Password"));
    }

    @When("^Input to \"([^\"]*)\" and \"([^\"]*)\"$")
    public void inputToEmailAndPassword(String email, String password){
        driver.findElement(By.id("email")).clear();
        driver.findElement(By.id("email")).sendKeys(email);
        driver.findElement(By.id("pass")).clear();
        driver.findElement(By.id("pass")).sendKeys(password);
    }
    @Then("^Verify submit info Address and Age$")
    public void verifySubmitButton(DataTable dataTable) {
        List<Map< String, String >> items = dataTable.asMaps(String.class, String.class);
        System.out.println(items.get(0).get("Address"));
        System.out.println(items.get(0).get("Age"));
    }

    @Then("^Verify submit info \"([^\"]*)\" and \"([^\"]*)\"$")
    public void verifySubmitButton(String Address, int Age) {
        System.out.println(Address);
        System.out.println(Age);
    }


}
