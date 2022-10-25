package ra.bussiness.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Product implements Serializable {
    private String productId;
    private String productName;
    private float exportPrice;
    private String content;
    private boolean productStatus;
    private Catalog catalog;
    private ArrayList<FlowerOfProduct> listFlower = new ArrayList<>();

    public Product() {
    }

    public Product(String productId, String productName, float exportPrice, String content, boolean productStatus, Catalog catalog, ArrayList<FlowerOfProduct> listFlower) {
        this.productId = productId;
        this.productName = productName;
        this.exportPrice = exportPrice;
        this.content = content;
        this.productStatus = productStatus;
        this.catalog = catalog;
        this.listFlower = listFlower;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public float getExportPrice() {
        return exportPrice;
    }

    public void setExportPrice(float exportPrice) {
        this.exportPrice = exportPrice;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean isProductStatus() {
        return productStatus;
    }

    public void setProductStatus(boolean productStatus) {
        this.productStatus = productStatus;
    }

    public Catalog getCatalog() {
        return catalog;
    }

    public void setCatalog(Catalog catalog) {
        this.catalog = catalog;
    }

    public List<FlowerOfProduct> getListFlower() {
        return listFlower;
    }

    public void setListFlower(ArrayList<FlowerOfProduct> listFlower) {
        this.listFlower = listFlower;
    }
}
