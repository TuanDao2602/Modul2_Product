package ra.bussiness.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Order implements Serializable {
    private int orderId;
    private float totalMoney;
    private boolean orderStatus;
    private User user;
    private List<OderDetail> listOderDetail = new ArrayList<>();

    public Order() {
    }

    public Order(int orderId, float totalMoney, boolean orderStatus, User user, List<OderDetail> listOderDetail) {
        this.orderId = orderId;
        this.totalMoney = totalMoney;
        this.orderStatus = orderStatus;
        this.user = user;
        this.listOderDetail = listOderDetail;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public float getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(float totalMoney) {
        this.totalMoney = totalMoney;
    }

    public boolean isOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(boolean orderStatus) {
        this.orderStatus = orderStatus;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<OderDetail> getListOderDetail() {
        return listOderDetail;
    }

    public void setListOderDetail(List<OderDetail> listOderDetail) {
        this.listOderDetail = listOderDetail;
    }
}
