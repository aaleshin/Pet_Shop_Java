import io.restassured.specification.RequestSpecification;
import objects.Orders;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static objects.Helpers.deleteOrder;
import static objects.Helpers.requestSpecification;

public class GetApiMethodTests {

    RequestSpecification requestSpec;

    @BeforeClass
    public void setUp() {
        requestSpec = requestSpecification();
    }

    @Test
    public void GetOrder() {
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
                .when().get("/{orderID}").then()
                .assertThat()
                .statusCode(200);

        deleteOrder(orderID);
    }

    @Test
    public void GetNonexistentOrder() {
        given().spec(requestSpec)
                .pathParam("orderID", 1154334567)
                .when().get("/{orderID}").then()
                .assertThat()
                .statusCode(404);
    }
}