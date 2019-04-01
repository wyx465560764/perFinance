package entity;

import java.util.Date;

public class position {
    String productname;
    String type;
    int earntype;
    double nowprice;
    double expectedincome;
    int orderid;
    int orderstatus;
    double buyprice;
    double buynum;
    double buysum;
    Date pushtime;
    Date selltime;

    public double getSellprice() {
        return sellprice;
    }

    public void setSellprice(double sellprice) {
        this.sellprice = sellprice;
    }

    double sellprice;

    public int getOrderid() {
        return orderid;
    }

    public void setOrderid(int orderid) {
        this.orderid = orderid;
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

    public int getOrderstatus() {
        return orderstatus;
    }

    public void setOrderstatus(int orderstatus) {
        this.orderstatus = orderstatus;
    }

    public double getBuyprice() {
        return buyprice;
    }

    public void setBuyprice(double buyprice) {
        this.buyprice = buyprice;
    }

    public double getBuynum() {
        return buynum;
    }

    public void setBuynum(double buynum) {
        this.buynum = buynum;
    }

    public double getBuysum() {
        return buysum;
    }

    public void setBuysum(double buysum) {
        this.buysum = buysum;
    }

    public Date getPushtime() {
        return pushtime;
    }

    public void setPushtime(Date pushtime) {
        this.pushtime = pushtime;
    }

    public Date getSelltime() {
        return selltime;
    }

    public void setSelltime(Date selltime) {
        this.selltime = selltime;
    }
}
