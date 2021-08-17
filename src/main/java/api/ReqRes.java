package api;

import api.utils.APIRequestUtils;
import api.utils.PropertyUtils;
import io.restassured.response.Response;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class ReqRes extends APIRequestUtils {

    PropertyUtils propertyUtils = new PropertyUtils();

    public void loadProjConfig(){
        propertyUtils.loadPropertyFile();
        propertyUtils.loadExtensionsFile();
        BASE_URL = propertyUtils.getValue("BaseURL");
    }

    public Response listUsers(){
        Map<String, String> queryParameters = new HashMap<String, String>();
        queryParameters.put("page", Integer.toString(1));
        String apiExtensions = propertyUtils.getExtension("LIST_USERS");
        return getResponse(apiExtensions, "", queryParameters, "application/json",
                null, RequestMethodType.GET);
    }

    public Response getSingleUser(int userID){
        String apiExtensions = propertyUtils.getExtension("SINGLE_USER").replace("ID", Integer.toString(userID));
        return getResponse(apiExtensions,
                "", null,"application/json", null, RequestMethodType.GET);
    }

    public Response getListResource(){
        Map<String, String> queryParameters = new HashMap<String, String>();
        queryParameters.put("page", Integer.toString(1));
        String apiExtensions = propertyUtils.getExtension("LIST_RESOURCE");
        return getResponse(apiExtensions, "", queryParameters, "application/json",
                null, RequestMethodType.GET);
    }

    public Response getSingleResource(int resourceID){
        String apiExtensions = propertyUtils.getExtension("SINGLE_RESOURCE").replace("ID", Integer.toString(resourceID));
        return getResponse(apiExtensions, "", null,"application/json", null, RequestMethodType.GET);
    }

    public Response createNewUser(){
        Map<Object, Object> body = new HashMap<>();
        body.put("\"name\"", "\"" + getRandomString() + "\"");
        body.put("\"job\"", "\"" + getRandomString() + "\"");
        String apiExtensions = propertyUtils.getExtension("CREATE");
        return getResponse(apiExtensions, body.toString(), null,"application/json", null, RequestMethodType.POST);
    }

    public Response updateUser(){
        Map<Object, Object> body = new HashMap<>();
        body.put("\"name\"", "\"" + getRandomString() + "\"");
        body.put("\"name\"", "\"" + getRandomString() + "\"");
        String apiExtensions = propertyUtils.getExtension("SINGLE_USER").replace("ID", propertyUtils.getValue("CREATE_USER_ID"));
        return getResponse(apiExtensions, body.toString(), null,"application/json", null, RequestMethodType.PUT);
    }

    public Response patchUser(){
        Map<Object, Object> body = new HashMap<>();
        body.put("\"name\"", "\"" + getRandomString() + "\"");
        body.put("\"name\"", "\"" + getRandomString() + "\"");
        String apiExtensions = propertyUtils.getExtension("SINGLE_USER").replace("ID", propertyUtils.getValue("CREATE_USER_ID"));
        return getResponse(apiExtensions, body.toString(), null,"application/json", null, RequestMethodType.PATCH);
    }

    public Response deleteUser(){
        String apiExtensions = propertyUtils.getExtension("SINGLE_USER").replace("ID", propertyUtils.getValue("CREATE_USER_ID"));
        return getResponse(apiExtensions, "", null,"application/json", null, RequestMethodType.DELETE);
    }

    public String getRandomString(){
        int leftLimit = 97; // letter 'a'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 8;
        Random random = new Random();
        StringBuilder buffer = new StringBuilder(targetStringLength);
        for (int i = 0; i < targetStringLength; i++) {
            int randomLimitedInt = leftLimit + (int)
                    (random.nextFloat() * (rightLimit - leftLimit + 1));
            buffer.append((char) randomLimitedInt);
        }
        return buffer.toString();
    }

    public void setValue(String property, Object value){
        propertyUtils.setPropertyValue(property, value);
    }

    public Response registerUser(boolean correct){
        Map<Object, Object> body = new HashMap<>();
        body.put("\"email\"", "\"eve.holt@reqres.in\"");
        String apiExtensions = propertyUtils.getExtension("REGISTER");
        if(correct){
            body.put("\"password\"", "\"cityslicka\"");
        }
        return getResponse(apiExtensions, body.toString(), null,"application/json", null, RequestMethodType.POST);
    }
    public Response logInUserSuccess(){
        Map<Object, Object> body = new HashMap<>();
        body.put("\"email\"", "\"" + propertyUtils.getValue("EMAIL") + "\"");
        body.put("\"password\"", "\"" + propertyUtils.getValue("PASSWORD") + "\"");
        String apiExtensions = propertyUtils.getExtension("LOGIN");
        return getResponse(apiExtensions, body.toString(), null,"application/json", null, RequestMethodType.POST);
    }
    public Response logInUserFailed(String errorType){
        Map<Object, Object> body = new HashMap<>();
        String apiExtensions = propertyUtils.getExtension("LOGIN");
        switch (errorType){
            case "email":
                body.put("\"password\"", "\"" + propertyUtils.getValue("PASSWORD") + "\"");
                break;
            case "password":
                body.put("\"email\"", "\"" + propertyUtils.getValue("EMAIL") + "\"");
                break;
            case "user":
                body.put("\"email\"", "\"" + getRandomString() + "@reqres.in\"");
                body.put("\"password\"", "\"" + getRandomString() + "\"");
                break;
        }
        return getResponse(apiExtensions, body.toString(), null,"application/json", null, RequestMethodType.POST);
    }
    public Response listUsersDelayed(int delay){
        Map<String, String> queryParameters = new HashMap<String, String>();
        queryParameters.put("delay", String.valueOf(delay));
        String apiExtensions = propertyUtils.getExtension("LIST_USERS");
        return getResponse(apiExtensions, "", queryParameters, "application/json",
                null, RequestMethodType.GET);
    }
}
