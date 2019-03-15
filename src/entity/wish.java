package entity;

import java.util.Date;

public class wish {
    private int wishId;
    private int userId;
    private String wishName;
    private Date planTime;
    private double money;
    private int wishstatus;
    private Date pushTime;

    public int getWishId() {
        return wishId;
    }

    public void setWishId(int wishId) {
        this.wishId = wishId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getWishName() {
        return wishName;
    }

    public void setWishName(String wishName) {
        this.wishName = wishName;
    }

    public Date getPlanTime() {
        return planTime;
    }

    public void setPlanTime(Date planTime) {
        this.planTime = planTime;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public Date getPushTime() {
        return pushTime;
    }

    public void setPushTime(Date pushTime) {
        this.pushTime = pushTime;
    }

    public int getWishstatus() {
        return wishstatus;
    }

    public void setWishstatus(int wishstatus) {
        this.wishstatus = wishstatus;
    }


}
