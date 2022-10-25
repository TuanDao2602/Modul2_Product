package ra.bussiness.entity;

import java.io.Serializable;
import java.util.Date;

public class FeedBack implements Serializable {
    private User user;
    private int feedBackId;
    private String feedBack;
    private Date date;

    public FeedBack() {
    }

    public FeedBack(User user, int feedBackId, String feedBack, Date date) {
        this.user = user;
        this.feedBackId = feedBackId;
        this.feedBack = feedBack;
        this.date = date;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getFeedBackId() {
        return feedBackId;
    }

    public void setFeedBackId(int feedBackId) {
        this.feedBackId = feedBackId;
    }

    public String getFeedBack() {
        return feedBack;
    }

    public void setFeedBack(String feedBack) {
        this.feedBack = feedBack;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
