package api;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
public class ReqResTests {

    ReqRes reqRes = new ReqRes();
    @When("^I call list users api$")
    public void i_call_list_users_api() {

    }

    @When("^I call Single user api$")
    public void i_call_single_user_api() {
        
    }

    @When("^I call Single User API and user doesn't exist$")
    public void i_call_single_user_api_and_user_doesnt_exist() {
        
    }

    @Then("^Verify the response contains list of users with details$")
    public void verify_the_response_contains_list_of_users_with_details() {
        
    }

    @Then("^Verify the response code and json schema$")
    public void verify_the_response_code_and_json_schema() {
        
    }

    @Then("^Verify the response contains data of single user$")
    public void verify_the_response_contains_data_of_single_user() {
        
    }

    @Then("^Verify the error response code$")
    public void verify_the_error_response_code() {
        
    }
}