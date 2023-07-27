package facebook.cucumberOptions;


import cucumber.api.CucumberOptions;
import cucumber.api.SnippetType;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        //Feature: để chứa các test case viết bằng ngôn ngữ Gherkin.
        //Step definition: để chứ các script ứng với test case.
        //Test runner: để chạy script.

        //Đường dẫn đến file feature
        features = "src/test/java/facebook/features",
        //Đường dẫn đến package step definition
        glue = "facebook/stepDefinitions",
        monochrome = true,
        plugin = {"pretty", "html:target/site/cucumber-report-default", "json:target/site/cucumber.json"},
        snippets = SnippetType.CAMELCASE,
        tags = {"@Login"}
)

public class TestRunner {

}
