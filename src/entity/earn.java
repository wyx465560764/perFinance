package entity;

import java.util.Date;

public class earn {
    private int earnid;
    private Date pushtime;
    private double price;
    private int productid;

    public int getEarnid() {
        return earnid;
    }

    public void setEarnid(int earnid) {
        this.earnid = earnid;
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

    public int getProductid() {
        return productid;
    }

    public void setProductid(int productid) {
        this.productid = productid;
    }
}
