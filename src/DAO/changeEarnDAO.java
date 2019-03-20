package DAO;

import dbtool.DBHelper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import entity.product;

public class changeEarnDAO {
    private static changeEarnDAO instance=new changeEarnDAO();
    public static changeEarnDAO getInstance(){return instance;}
    public product changeEarnSelect(String productid)throws SQLException {
        String sql="select * from product where productid=?";
        Connection con=DBHelper.getConnection();
        PreparedStatement st=con.prepareStatement(sql);
        st.setString(1,productid);
        ResultSet rs=st.executeQuery();
        product product=new product();
        if(rs.next()){
            product.setNowprice(rs.getDouble("nowprice"));
            product.setProductname(rs.getString("productname"));
        }
        return product;
    }
    public boolean changeEarn(product product)throws SQLException {
        String sqlp="UPDATE product set nowprice=? where productid=?";
        Connection con=DBHelper.getConnection();
        PreparedStatement stp=con.prepareStatement(sqlp);
        stp.setDouble(1,product.getNowprice());
        stp.setInt(2,product.getProductid());
        String sqle="insert into earn value (0,NOW(),?,?)";
        PreparedStatement ste=con.prepareStatement(sqle);
        ste.setDouble(1,product.getNowprice());
        ste.setDouble(2,product.getProductid());
        if((stp.executeUpdate()>0)&&(ste.executeUpdate()>0)){
            return true;
        }else {
            return false;
        }
    }
}
