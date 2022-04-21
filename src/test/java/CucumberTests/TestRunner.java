package CucumberTests;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
//By adding tags, it matches will the name in all files under the destined features folder
@CucumberOptions(features = "feature" , plugin = "json:target/jsonReports/cucumber-report.json",glue = "stepImplementation" , tags = "@AddPlace")
public class TestRunner {
//tags= {"@DeletePlace"}  compile test verify
}
