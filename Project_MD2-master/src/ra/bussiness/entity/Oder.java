package ra.bussiness.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Oder implements Serializable {
    private int oderId;
    private float totalMoney;
    private boolean oderStatus;
    private User user;
    private List<OderDetail> listOderDetail = new ArrayList<>();

    public Oder() {
    }

    public Oder(int oderId, float totalMoney, boolean oderStatus, List<OderDetail> listOderDetail) {
        this.oderId = oderId;
        this.totalMoney = totalMoney;
        this.oderStatus = oderStatus;
        this.listOderDetail = listOderDetail;
    }

    public int getOderId() {
        return oderId;
    }

    public void setOderId(int oderId) {
        this.oderId = oderId;
    }

    public float getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(float totalMoney) {
        this.totalMoney = totalMoney;
    }

    public boolean isOderStatus() {
        return oderStatus;
    }

    public void setOderStatus(boolean oderStatus) {
        this.oderStatus = oderStatus;
    }

    public List<OderDetail> getListOderDetail() {
        return listOderDetail;
    }

    public void setListOderDetail(List<OderDetail> listOderDetail) {
        this.listOderDetail = listOderDetail;
    }
}
