import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import objects.Orders;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static objects.Helpers.*;
import static org.hamcrest.CoreMatchers.equalTo;

public class PostOrderTests {

    RequestSpecification requestSpec;

    @BeforeClass
    public void setUp() {
        requestSpec = requestSpecification();
    }

    @Test
    public void createOrder() {
        Orders orders = new Orders();
        orders.setOrder(1, 1, 1, "placed", true);

        int orderID = given()
                .spec(requestSpec)
                .body(orders.getOrder().toString())
                .when().post().then()
                .statusCode(200)
                .extract().path("id");

        deleteOrder(orderID);
    }

    @Test
    public void createOrderWithSigns() {
        Orders order = new Orders();
        order.setFailOrder("~!@#$%^&*()?>,./<][ /*<!—«»♣☺♂", 2, 2, "placed", true);

        ValidatableResponse failedOrder = tryCreateOrder(order);
        failedOrder.assertThat()
                .statusCode(422); // its bug: Expected status code <422> but was <500>.
    }
}