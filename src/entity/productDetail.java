package entity;

import java.util.Date;

public class productDetail {
    private int productid;
    private String productname;
    private String type;
    private int earntype;
    private double sum;
    private double over;
    private double nowprice;
    private double expectedincome;
    private String remark;
    private int userid;
    private Date pushtime;
    private double price;
    private String username;
    private String managercontext;

    public int getProductid() {
        return productid;
    }

    public void setProductid(int productid) {
        this.productid = productid;
    }

    public String getProductname() {
        return productname;
    }

    public void setProductname(String productname) {
        this.productname = productname;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getEarntype() {
        return earntype;
    }

    public void setEarntype(int earntype) {
        this.earntype = earntype;
    }

    public double getSum() {
        return sum;
    }

    public void setSum(double sum) {
        this.sum = sum;
    }

    public double getOver() {
        return over;
    }

    public void setOver(double over) {
        this.over = over;
    }

    public double getNowprice() {
        return nowprice;
    }

    public void setNowprice(double nowprice) {
        this.nowprice = nowprice;
    }

    public double getExpectedincome() {
        return expectedincome;
    }

    public void setExpectedincome(double expectedincome) {
        this.expectedincome = expectedincome;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public Date getPushtime() {
        return pushtime;
    }

    public void setPushtime(Date pushtime) {
        this.pushtime = pushtime;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getManagercontext() {
        return managercontext;
    }

    public void setManagercontext(String managercontext) {
        this.managercontext = managercontext;
    }
}
