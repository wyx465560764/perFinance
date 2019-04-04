package DAO;

import dbtool.DBHelper;
import entity.product;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class productChangeDAO {
    public boolean Permission(int userid)throws SQLException{
        String sql="select type from `user` where userid=?";
        PreparedStatement st=DBHelper.getConnection().prepareStatement(sql);
        st.setInt(1,userid);
        ResultSet rs=st.executeQuery();
        if(rs.next()){
            if(rs.getInt("type")==1){
                return true;
            }else {
                return false;
            }
        }else {
            return false;
        }
    }
    public boolean productChange(product product)throws SQLException{
        String sql="UPDATE product set productname=?,remark=?,over=over+(?-sum),sum=? where productid=?";
        PreparedStatement st=DBHelper.getConnection().prepareStatement(sql);
        st.setString(1,product.getProductname());
        st.setString(2,product.getRemark());
        st.setDouble(3,product.getSum());
        st.setDouble(4,product.getSum());
        st.setInt(5,product.getProductid());
        if(st.executeUpdate()>0){
            return true;
        }else {
            return false;
        }
    }
}
