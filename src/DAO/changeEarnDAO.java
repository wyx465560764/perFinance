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
        String sql="select * from product INNER JOIN product_dictionary ON product.type=product_dictionary.typeid where productid=?";
        PreparedStatement st=DBHelper.getConnection().prepareStatement(sql);
        st.setString(1,productid);
        ResultSet rs=st.executeQuery();
        product e=new product();
        if(rs.next()){
            e.setProductid(rs.getInt("productid"));
            e.setProductname(rs.getString("productname"));
            e.setType(rs.getString("product_dictionary.type"));
            e.setEarntype(rs.getInt("product_dictionary.earntype"));
            e.setSum(rs.getDouble("sum"));
            e.setOver(rs.getDouble("over"));
            e.setNowprice(rs.getDouble("nowprice"));
            e.setExpectedincome(rs.getInt("expectedincome"));
            e.setRemark(rs.getString("remark"));
            e.setUserid(rs.getInt("userid"));
        }
        st.close();
        rs.close();
        return e;
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
            ste.close();
            stp.close();
            return true;
        }else {
            ste.close();
            stp.close();
            return false;
        }
    }
}
