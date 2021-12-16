import io.restassured.specification.RequestSpecification;
import objects.Orders;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static objects.Helpers.requestSpecification;
import static org.hamcrest.CoreMatchers.equalTo;

public class PostOrderTests {

    RequestSpecification requestSpec;

    @BeforeClass
    public void setUp() {
        requestSpec = requestSpecification();
    }

    @AfterTest
    public void clear() {
//        deleteOrder();
    }

    @Test
    public void createOrder() {
        Orders orders = new Orders();
        orders.setOrder(1, 1, 1, "1", "placed", true);

        String orderID = given()
                .spec(requestSpec)
                .body(orders.getOrder().toString())
                .when().post().then()
                .statusCode(200)
                .extract().path("id");

        given().spec(requestSpec)
                .pathParam("orderID", orderID)
                .when().get("/{orderID}").then()
                .assertThat()
                .statusCode(200)
                .body("id", equalTo(orderID));
    }
}