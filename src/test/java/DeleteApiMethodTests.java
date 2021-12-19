import io.restassured.specification.RequestSpecification;
import objects.Orders;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static objects.Helpers.requestSpecification;

public class DeleteApiMethodTests {
    RequestSpecification requestSpec;

    @BeforeClass
    public void setUp() {
        requestSpec = requestSpecification();
    }

    @Test
    public void deleteOrder() {
        Orders orders = new Orders();
        orders.setOrder(1, 1, 1, "placed", true);

        int orderID = given()
                .spec(requestSpec)
                .body(orders.getOrder().toString())
                .when().post().then()
                .statusCode(200)
                .extract().path("id");

        given().spec(requestSpec)
                .pathParam("orderID", orderID)
                .when().delete("/{orderID}").then()
                .assertThat()
                .statusCode(200);
    }

    @Test
    public void deleteNonexistentOrder() {
        given().spec(requestSpec)
                .pathParam("orderID", 1154334564)
                .when().delete("/{orderID}").then()
                .assertThat()
                .statusCode(404);
    }
}