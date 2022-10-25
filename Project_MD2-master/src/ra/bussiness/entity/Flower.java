package ra.bussiness.entity;

import java.io.Serializable;

public class Flower implements Serializable {
    private String flowerId;
    private String flowerName;
    private TypeOfFlower typeOfFlower;
    private float importPrince;
    private float exportPrice;
    private String content;
    private boolean flowerStatus;

    public Flower() {
    }

    public Flower(String flowerId, String flowerName, TypeOfFlower typeOfFlower, float importPrince, float exportPrice, String content, boolean flowerStatus) {
        this.flowerId = flowerId;
        this.flowerName = flowerName;
        this.typeOfFlower = typeOfFlower;
        this.importPrince = importPrince;
        this.exportPrice = exportPrice;
        this.content = content;
        this.flowerStatus = flowerStatus;
    }

    public String getFlowerId() {
        return flowerId;
    }

    public void setFlowerId(String flowerId) {
        this.flowerId = flowerId;
    }

    public String getFlowerName() {
        return flowerName;
    }

    public void setFlowerName(String flowerName) {
        this.flowerName = flowerName;
    }

    public TypeOfFlower getTypeOfFlower() {
        return typeOfFlower;
    }

    public void setTypeOfFlower(TypeOfFlower typeOfFlower) {
        this.typeOfFlower = typeOfFlower;
    }

    public float getImportPrince() {
        return importPrince;
    }

    public void setImportPrince(float importPrince) {
        this.importPrince = importPrince;
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

    public boolean isFlowerStatus() {
        return flowerStatus;
    }

    public void setFlowerStatus(boolean flowerStatus) {
        this.flowerStatus = flowerStatus;
    }
}
