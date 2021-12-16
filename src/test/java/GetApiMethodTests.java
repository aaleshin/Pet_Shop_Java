import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import objects.Orders;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static objects.Helpers.*;

public class GetApiMethodTests {

    RequestSpecification requestSpec;

    @BeforeClass
    public void setUp() {
        requestSpec = requestSpecification();
    }

    @AfterTest
    public void clear() {
//        deleteAllTriangles();
    }

    @Test
    public void GetNonexistentTriangle() {
        given().spec(requestSpec)
                .pathParam("orderID", "orderID")
                .when().get("/{orderID}").then()
                .assertThat()
                .statusCode(404);
    }
}