package entity;

import java.util.Date;

public class bill {
    private int billId;
    private int userId;
    private int billName;
    private String billNameText;
    private double money;
    private String remark;
    private Date spentTime;
    private Date pushTime;
    private int type;

    public String getBillNameText() {
        return billNameText;
    }

    public void setBillNameText(String billNameText) {
        this.billNameText = billNameText;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getBillId() {
        return billId;
    }

    public void setBillId(int billId) {
        this.billId = billId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getBillName() {
        return billName;
    }

    public void setBillName(int billName) {
        this.billName = billName;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Date getSpentTime() {
        return spentTime;
    }

    public void setSpentTime(Date spentTime) {
        this.spentTime = spentTime;
    }

    public Date getPushTime() {
        return pushTime;
    }

    public void setPushTime(Date pushTime) {
        this.pushTime = pushTime;
    }
}
