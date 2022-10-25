package ra.bussiness.entity;

import java.io.Serializable;

public class FlowerOfProduct implements Serializable {
    private Flower flower;
    private int quantity;

    public FlowerOfProduct() {
    }

    public Flower getFlower() {
        return flower;
    }

    public void setFlower(Flower flower) {
        this.flower = flower;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
