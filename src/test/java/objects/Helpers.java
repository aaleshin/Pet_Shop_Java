package objects;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;

import java.util.List;

import static io.restassured.RestAssured.given;


public class Helpers {

    public static RequestSpecification requestSpecification() {
        RestAssured.baseURI = "https://petstore.swagger.io/v2/store/order";
        RequestSpecBuilder builder = new RequestSpecBuilder();
        builder.addHeader("Content-Type", "application/json");
        return builder.build();
    }
}