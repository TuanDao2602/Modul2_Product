package ra.bussiness.entity;

import java.io.Serializable;

public class TypeOfFlower implements Serializable {
    private int typeFlowerId;
    private String typeFlowerName;
    private String content;
    private boolean typeFlowerStatus;

    public TypeOfFlower() {
    }

    public TypeOfFlower(int typeFlowerId, String typeFlowerName, String content, boolean typeFlowerStatus) {
        this.typeFlowerId = typeFlowerId;
        this.typeFlowerName = typeFlowerName;
        this.content = content;
        this.typeFlowerStatus = typeFlowerStatus;
    }

    public int getTypeFlowerId() {
        return typeFlowerId;
    }

    public void setTypeFlowerId(int typeFlowerId) {
        this.typeFlowerId = typeFlowerId;
    }

    public String getTypeFlowerName() {
        return typeFlowerName;
    }

    public void setTypeFlowerName(String typeFlowerName) {
        this.typeFlowerName = typeFlowerName;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean isTypeFlowerStatus() {
        return typeFlowerStatus;
    }

    public void setTypeFlowerStatus(boolean typeFlowerStatus) {
        this.typeFlowerStatus = typeFlowerStatus;
    }
}
