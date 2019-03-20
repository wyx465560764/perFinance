package DAO;

import dbtool.DBHelper;
import entity.product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class addProductDAO {
    private static addProductDAO instance=new addProductDAO();
    public static addProductDAO getInstance(){return instance;}
    public boolean addProduct(product product)throws SQLException,Exception{
        Connection con=DBHelper.getConnection();
        String sql="insert into product value (0,?,?,?,?,?,?,?,?,?)";
        PreparedStatement st=con.prepareStatement(sql);
        st.setString(1,product.getProductname());
        st.setString(2,product.getType());
        st.setInt(3,product.getEarntype());
        st.setDouble(4,product.getSum());
        st.setDouble(5,product.getSum());
        st.setDouble(6,product.getNowprice());
        st.setDouble(7,product.getExpectedincome());
        st.setString(8,product.getRemark());
        st.setInt(9,product.getUserid());
//        String sqle="insert into earn value (0,NOW(),?,?)";
//        PreparedStatement ste=con.prepareStatement(sqle);
//        ste.setDouble(1,product.getNowprice());
//        ste.setDouble(2,product.getProductid());
        if((st.executeUpdate()>0)){
            return true;
        }else {
            return false;
        }
    }
}
