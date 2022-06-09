package runner;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(features = "./features", glue = { "stepdefinitions" })

public class Runner {

//public class CucumberRunner extends AbstractTestNGCucumberTests{		

}
