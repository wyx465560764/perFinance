package entity;

public class product {
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
    private int typeid;
    public int getTypeid() {
        return typeid;
    }

    public void setTypeid(int typeid) {
        this.typeid = typeid;
    }
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
}
