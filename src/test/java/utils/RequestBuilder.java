package utils;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;

import java.util.Map;

public class RequestBuilder {

    public static RequestSpecification buildRequest(Map<String, String> headers, Object body) {
        RequestSpecBuilder builder = new RequestSpecBuilder();

        if (headers != null) {
            builder.addHeaders(headers);
        }
        if (body != null) {
            builder.setBody(body);
        }

        return builder.build();
    }
}
