import io.restassured.specification.RequestSpecification;
import objects.Orders;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static objects.Helpers.*;

public class DeleteApiMethodTests {
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
    public void deleteNonexistentTriangle() {
        given().spec(requestSpec)
                .pathParam("orderID", "orderID")
                .when().delete("/{orderId}").then()
                .assertThat()
                .statusCode(404);
    }
}