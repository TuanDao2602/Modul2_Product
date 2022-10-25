package ra.bussiness.entity;

import java.io.Serializable;

public class OderDetail implements Serializable {
    private int oderDetailId;
    private Product product;
    private Oder oder;
    private int quantity;
    private float price;
    private float totalMoney;

    public OderDetail() {
    }

    public OderDetail(int oderDetailId, Product product, Oder oder, int quantity, float price, float totalMoney) {
        this.oderDetailId = oderDetailId;
        this.product = product;
        this.oder = oder;
        this.quantity = quantity;
        this.price = price;
        this.totalMoney = totalMoney;
    }

    public int getOderDetailId() {
        return oderDetailId;
    }

    public void setOderDetailId(int oderDetailId) {
        this.oderDetailId = oderDetailId;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Oder getOder() {
        return oder;
    }

    public void setOder(Oder oder) {
        this.oder = oder;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public float getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(float totalMoney) {
        this.totalMoney = totalMoney;
    }
}
