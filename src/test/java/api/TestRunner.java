package api;


import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features ="src/test/resources/features/ReqRes.feature" ,
        glue = "steps",
        tags="@test",
        plugin = { "pretty","html:target/cucumber-reports","json:target/cucumber-reports/cucumber.json" },
        monochrome = true
)


public class TestRunner {

}
