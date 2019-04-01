package DAO;

import dbtool.DBHelper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class reviewUserDAO {
    public boolean isPersonal(int userid,int orderid) throws SQLException {
        String sql="select userid from `order` where orderid=?";
        PreparedStatement st=DBHelper.getConnection().prepareStatement(sql);
        st.setInt(1,orderid);
        ResultSet rs=st.executeQuery();
        if(rs.next()){
            if(rs.getInt("userid")==userid){
                return true;
            }else {
                return false;
            }
        }else {
            return false;
        }
    }
    public boolean disReview(int orderid)throws SQLException{
        String sql="select productid,buynum from `order` where orderid=?";
        PreparedStatement st=DBHelper.getConnection().prepareStatement(sql);
        st.setInt(1,orderid);
        ResultSet rs=st.executeQuery();
        int productid=0;
        double buynum=0;
        if(rs.next()){
            productid=rs.getInt("productid");
            buynum=rs.getDouble("buynum");
        }
        String sql2="update `product` set over=over+? where productid=?";
        PreparedStatement st2=DBHelper.getConnection().prepareStatement(sql2);
        st2.setDouble(1,buynum);
        st2.setInt(2,productid);
        String sql3="update `order` set orderstatus=5 where orderid=?";
        PreparedStatement st3=DBHelper.getConnection().prepareStatement(sql3);
        st3.setInt(1,orderid);
        if(st2.executeUpdate()>0&&st3.executeUpdate()>0){
            return true;
        }else {
            return false;
        }
    }
}
