package api;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.junit.Cucumber;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import org.junit.Assert;
import org.junit.runner.RunWith;

import java.io.File;
import java.util.List;
import java.util.Map;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchema;


@RunWith(Cucumber.class)
public class ReqResTests {

    ReqRes reqRes = new ReqRes();
    Response listUsersRes, singleUserGetRes, singleUserNonExisting;
    Response listResourceRes, singleResourceGetRes, singleResourceNonExisting;
    Response createUserRes, updateUserRes, PatchUserRes, deleteUserRes;
    Response registerUserRes, registerFailRes, logInRes, logInFailRes, delayedRes;
    int userIdToUpdate;

    @Given("^Project configurations are loaded$")
    public void project_configurations_are_loaded() {
        reqRes.loadProjConfig();
    }

    @When("^I call list users api$")
    public void i_call_list_users_api() {
        listUsersRes = reqRes.listUsers();
    }

    @When("^I call Single user api$")
    public void i_call_single_user_api() {
        singleUserGetRes = reqRes.getSingleUser(9);
    }

    @When("^I call Single User API and user doesn't exist$")
    public void i_call_single_user_api_and_user_doesnt_exist() {
        singleUserNonExisting = reqRes.getSingleUser(23);
    }

    @Then("^Verify the response contains list of users with details$")
    public void verify_the_response_contains_list_of_users_with_details() {
        if (listUsersRes != null) {
            JsonPath jp = listUsersRes.jsonPath();
            List<Object> userElements = jp.getList("data");
            Assert.assertTrue("Verify that the API has returned array of 6 users", userElements.size() == 6);
        } else {
            Assert.assertTrue("API failed ", false);
        }
    }

    @Then("^Verify the response status and \"([^\"]*)\" json schema$")
    public void verify_the_response_status_and_something_json_schema(String respObj) {
        switch (respObj) {
            case "List Users":
                Assert.assertTrue("Verify the response status code is 200.", listUsersRes.statusCode() == 200);
                listUsersRes.then().assertThat().body(matchesJsonSchema(new File("./src/test/resources/schemas/ListUsers.json")));
                break;
            case "Single User":
                Assert.assertTrue("Verify the response status code is 200.", singleUserGetRes.statusCode() == 200);
                singleUserGetRes.then().assertThat().body(matchesJsonSchema(new File("./src/test/resources/schemas/SingleUser.json")));
                break;
            case "Single User Not Exist":
                Assert.assertTrue("Verify the response status code is 404.",
                        singleUserNonExisting.statusCode() == 404);
                break;
            case "List Resource":
                Assert.assertTrue("Verify the response status code is 200.", listResourceRes.statusCode() == 200);
                listResourceRes.then().assertThat().body(matchesJsonSchema(new File("./src/test/resources/schemas/ListResource.json")));
                break;
            case "Single Resource":
                Assert.assertTrue("Verify the response status code is 200.", singleResourceGetRes.statusCode() == 200);
                singleResourceGetRes.then().assertThat().body(matchesJsonSchema(new File("./src/test/resources/schemas/SingleResource.json")));
                break;
//
            case "Single Resource Not Exist":
                Assert.assertTrue("Verify the response status code is 404.",
                        singleResourceNonExisting.statusCode() == 404);
                break;
            case "Create User":
                Assert.assertTrue("Verify the response status code is 201.", createUserRes.statusCode() == 201);
                createUserRes.then().assertThat().body(matchesJsonSchema(new File("./src/test/resources/schemas/CreateUser.json")));
                break;
            case "Update User":
                Assert.assertTrue("Verify the response status code is 200.", updateUserRes.statusCode() == 200);
                System.out.println("Patch User Patch User Response : " + updateUserRes.getBody().asString());
                updateUserRes.then().assertThat().body(matchesJsonSchema(new File("./src/test/resources/schemas/UpdateUser.json")));
                break;
            case "Patch User":
                Assert.assertTrue("Verify the response status code is 200.", PatchUserRes.statusCode() == 200);
                System.out.println("Patch User Patch User Response : " + PatchUserRes.getBody().toString());
                PatchUserRes.then().assertThat().body(matchesJsonSchema(new File("./src/test/resources/schemas/UpdateUser.json")));
                break;
            case "Delete User":
                Assert.assertTrue("Verify the response status code is 404.",
                        deleteUserRes.statusCode() == 204);
                break;
//
            case "Register User":
                Assert.assertTrue("Verify the response status code is 200.", registerUserRes.statusCode() == 200);
                registerUserRes.then().assertThat().body(matchesJsonSchema(new File("./src/test/resources/schemas/UpdateUser.json")));
                break;
            case "Register User Fail":
                Assert.assertTrue("Verify the response status code is 200.", registerFailRes.statusCode() == 200);
                registerFailRes.then().assertThat().body(matchesJsonSchema(new File("./src/test/resources/schemas/UpdateUser.json")));
                break;
            case "Login User":
                Assert.assertTrue("Verify the response status code is 200.", logInRes.statusCode() == 200);
                logInRes.then().assertThat().body(matchesJsonSchema(new File("./src/test/resources/schemas/UpdateUser.json")));
                break;
            case "Login User fail":
                Assert.assertTrue("Verify the response status code is 200.", logInFailRes.statusCode() == 200);
                logInFailRes.then().assertThat().body(matchesJsonSchema(new File("./src/test/resources/schemas/UpdateUser.json")));
                break;
            case "List Users Delayed":
                Assert.assertTrue("Verify the response status code is 200.", delayedRes.statusCode() == 200);
                delayedRes.then().assertThat().body(matchesJsonSchema(new File("./src/test/resources/schemas/UpdateUser.json")));
                break;
        }
    }

    @Then("^Verify the response contains data of single user$")
    public void verify_the_response_contains_data_of_single_user() {
        if (singleUserGetRes != null) {
            JsonPath jp = singleUserGetRes.jsonPath();
            Map<Object, Object> userData = jp.getMap("data");
            Assert.assertTrue("Verify that the API has returned data of single user",
                    userData.containsKey("email") &&
                            Integer.parseInt(userData.get("id").toString()) == 9);
        } else {
            Assert.assertTrue("API failed ", false);
        }
    }

    @When("^I call list resource api$")
    public void i_call_list_resource_api() {
        listResourceRes = reqRes.getListResource();
        System.out.println("Res: " + listResourceRes);
    }

    @When("^I call Single resource api$")
    public void i_call_single_resource_api() {
        singleResourceGetRes = reqRes.getSingleResource(2);
        System.out.println("Res: " + singleResourceGetRes);
    }

    @Then("^Verify the response contains list of resources with details$")
    public void verify_the_response_contains_list_of_resources_with_details() {
        if (listResourceRes != null) {
            JsonPath jp = listResourceRes.jsonPath();
            List<Object> userElements = jp.getList("data");
            Assert.assertTrue("Verify that the API has returned array of 6 resources", userElements.size() == 6);
        } else {
            Assert.assertTrue("API failed ", false);
        }
    }

    @Then("^Verify the response contains data of resource user$")
    public void verify_the_response_contains_data_of_resource_user() {
        if (singleResourceGetRes != null) {
            JsonPath jp = singleResourceGetRes.jsonPath();
            Map<Object, Object> userData = jp.getMap("data");
            Assert.assertTrue("Verify that the API has returned data of single resource",
                    userData.containsKey("name") &&
                            Integer.parseInt(userData.get("id").toString()) == 2);
        } else {
            Assert.assertTrue("API failed ", false);
        }
    }

    @When("^I call Single resource API and user doesn't exist$")
    public void i_call_single_resource_api_and_user_doesnt_exist() {
        singleResourceNonExisting = reqRes.getSingleResource(23);
    }

//
    @When("^I create a new user$")
    public void i_create_a_new_user() {
        createUserRes = reqRes.createNewUser();
        JsonPath jp = createUserRes.jsonPath();
        userIdToUpdate = jp.getInt("id");
        reqRes.setValue("CREATE_USER_ID", userIdToUpdate);
        System.out.println("New user ID : " + userIdToUpdate);
    }

    @When("^I update an existing user$")
    public void i_update_an_existing_user() {
        updateUserRes = reqRes.updateUser();
    }

    @When("^I update an existing user with Patch$")
    public void i_update_an_existing_user_with_patch() {
        PatchUserRes = reqRes.patchUser();
    }

    @When("^I delete a user$")
    public void i_delete_a_user() {
        deleteUserRes = reqRes.deleteUser();
    }

    @When("^I register a new user$")
    public void i_register_a_new_user() {
        
    }

    @When("^I register a new user with incorrect details$")
    public void i_register_a_new_user_with_incorrect_details() {
        
    }

    @When("^I Login a user with email and password$")
    public void i_login_a_user_with_email_and_password() {
        
    }

    @When("^I login a user with missing email password$")
    public void i_login_a_user_with_missing_email_password() {
        
    }

    @When("^List users api is called with delay$")
    public void list_users_api_is_called_with_delay() {
        
    }

}