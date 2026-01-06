package order;

import java.util.UUID;

public class Payment {

    private UUID id;
    private UUID orderId;
    private String status;
    private int amount;
    private String description;

    public Payment() {
    }

    public Payment(UUID id, UUID orderId, String status, int amount, String description) {
        this.id = id;
        this.orderId = orderId;
        this.status = status;
        this.amount = amount;
        this.description = description;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getOrderId() {
        return orderId;
    }

    public void setOrderId(UUID orderId) {
        this.orderId = orderId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}