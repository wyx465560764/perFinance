package DAO;

import dbtool.DBHelper;
import entity.order;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class submitOrderDAO {
    public String isVerified(int userid) throws SQLException{
        String sql="select identity from `user` where userid=?";
        PreparedStatement st=DBHelper.getConnection().prepareStatement(sql);
        st.setInt(1,userid);
        ResultSet rs=st.executeQuery();
        rs.next();
        return rs.getString("identity");
    }
    public boolean submitOrder(order order) throws SQLException{
        String ssql="select nowprice from product where productid=?";
        PreparedStatement sst=DBHelper.getConnection().prepareStatement(ssql);
        sst.setInt(1,order.getProductid());
        ResultSet rs=sst.executeQuery();
        if(rs.next()){
            order.setBuyprice(rs.getDouble("nowprice"));
            order.setBuynum(order.getBuysum()/order.getBuyprice());
            String isql="insert into `order` values(0,?,?,1,?,?,?,now(),null,null)";
            PreparedStatement st=DBHelper.getConnection().prepareStatement(isql);
            st.setInt(1,order.getProductid());
            st.setInt(2,order.getUserid());
            st.setDouble(3,order.getBuyprice());
            st.setDouble(4,order.getBuynum());
            st.setDouble(5,order.getBuysum());
            String sql2="update `product` set over=over-? where productid=?";
            PreparedStatement st2=DBHelper.getConnection().prepareStatement(sql2);
            st2.setDouble(1,order.getBuynum());
            st2.setInt(2,order.getProductid());
            if((st.executeUpdate()>0)&&(st2.executeUpdate()>0)){
                return true;
            }else {
                return false;
            }
        }else {
            return false;
        }
    }
}
