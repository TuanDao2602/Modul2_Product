package ra.bussiness.entity;

import java.io.Serializable;

public class Catalog implements Serializable {
    private String catalogId;
    private String catalogName;
    private String content;
    private int priority;
    private boolean catalogStatus;

    public Catalog() {
    }

    public Catalog(String catalogId, String catalogName, String content, int priority, boolean catalogStatus) {
        this.catalogId = catalogId;
        this.catalogName = catalogName;
        this.content = content;
        this.priority = priority;
        this.catalogStatus = catalogStatus;
    }

    public String getCatalogId() {
        return catalogId;
    }

    public void setCatalogId(String catalogId) {
        this.catalogId = catalogId;
    }

    public String getCatalogName() {
        return catalogName;
    }

    public void setCatalogName(String catalogName) {
        this.catalogName = catalogName;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public boolean isCatalogStatus() {
        return catalogStatus;
    }

    public void setCatalogStatus(boolean catalogStatus) {
        this.catalogStatus = catalogStatus;
    }
}
