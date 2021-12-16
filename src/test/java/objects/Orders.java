package objects;

import org.json.JSONObject;
import java.time.LocalDate;

public class Orders {
    private JSONObject order;
    private int id;
    private int petId;
    private int quantity;
    private String shipDate;
    private String status;
    private boolean complete;

    public Orders() {
        order = new JSONObject();
    }

    public void setOrder(int id, int petId, int quantity, String status, boolean complete) {
        this.id = id;
        this.petId = petId;
        this.quantity = quantity;
        this.shipDate = LocalDate.now().toString();
        this.status = status;
        this.complete = complete;
    }

    public JSONObject getOrder() {
        order.put(id, petId, quantity, shipDate, status, complete);
        return this.order;
    }
}