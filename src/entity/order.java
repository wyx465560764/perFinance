package entity;

import java.util.Date;

public class order {
    int orderid;
    int productid;
    int userid;
    int status;
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

    public int getProductid() {
        return productid;
    }

    public void setProductid(int productid) {
        this.productid = productid;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
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
