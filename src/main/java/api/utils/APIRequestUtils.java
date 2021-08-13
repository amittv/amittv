package api.utils;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static api.utils.PropertyUtils.getValue;
import static io.restassured.RestAssured.given;

import java.util.Map;

public class APIRequestUtils {

    public enum RequestMethodType {
        POST, GET, DELETE, PUT
    }

    public Response getResponse(String url, RequestSpecification request, RequestMethodType methodType) {
        switch (methodType) {
            case POST:
                return request.post(url);
            case GET:
                return request.get(url);
            case DELETE:
                return request.delete(url);
            case PUT:
                return request.put(url);
            default:
                return null;
        }
    }

    public Response getResponse(String url, String jsonBody, Map<String, ? extends Object> queryParam,
                                String contentType, Map<String, String> headers, RequestMethodType methodType) {
        String requestURL = getValue("BaseURL") + url;
        RequestSpecification request = given();

        if (jsonBody != null) {
            if (!jsonBody.equals(""))
                request = request.body(jsonBody);
        }

        if (queryParam != null) {
            if (!queryParam.isEmpty()) {
                request = request.queryParams(queryParam);
            }
        }

        request = request.when();

        if (contentType != null) {
            if (!contentType.equals(""))
                request = request.contentType(contentType);
        }

        if (headers != null) {
            if (!headers.isEmpty())
                headers.put("Content-Type",contentType);
            request = request.headers(headers);
        }
        Response res = getResponse(requestURL, request, methodType);

        return res;
    }
}
