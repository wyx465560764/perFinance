package DAO;

import dbtool.DBHelper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class reviewDAO {
    public boolean isPersonal(int userid,int orderid) throws SQLException{
        String sql="select `user`.userid from `user` INNER JOIN product on `user`.userid=product.userid WHERE product.productid IN (SELECT productid FROM `order` WHERE `order`.orderid=?)";
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
    public boolean reviewPass(int orderid)throws SQLException{
        String ssql="select orderstatus,buynum,productid,buysum,buyprice from `order` where orderid=?";
        PreparedStatement sst=DBHelper.getConnection().prepareStatement(ssql);
        sst.setInt(1,orderid);
        ResultSet rs=sst.executeQuery();
        if(rs.next()){
            if(rs.getInt("orderstatus")==1){
                String sql="update `order` set orderstatus=orderstatus+1 where orderid=?";
                PreparedStatement st=DBHelper.getConnection().prepareStatement(sql);
                st.setInt(1,orderid);
                if(st.executeUpdate()>0){
                    return true;
                }else {
                    return false;
                }
            }else if(rs.getInt("orderstatus")==3){
                String sql="update `order` set orderstatus=orderstatus+1,sellprice=(SELECT nowprice FROM product WHERE productid=?) where orderid=?";
                PreparedStatement st=DBHelper.getConnection().prepareStatement(sql);
                st.setInt(1,rs.getInt("productid"));
                st.setInt(2,orderid);
                String sql2="update `product` set over=over+? where productid=?";
                PreparedStatement st2=DBHelper.getConnection().prepareStatement(sql2);
                st2.setDouble(1,rs.getDouble("buysum")/rs.getDouble("buyprice"));
                st2.setInt(2,rs.getInt("productid"));
                if((st.executeUpdate()>0)&&(st2.executeUpdate()>0)){
                    return true;
                }else {
                    return false;
                }
            }else {
                return false;
            }

        }else {
            return false;
        }
    }
    public boolean reviewDisagree(int orderid)throws SQLException{
        String ssql="select orderstatus,buynum,productid,buysum,buyprice from `order` where orderid=?";
        PreparedStatement sst=DBHelper.getConnection().prepareStatement(ssql);
        sst.setInt(1,orderid);
        ResultSet rs=sst.executeQuery();
        if(rs.next()){
            if(rs.getInt("orderstatus")==3){
                String sql="update `order` set orderstatus=orderstatus-1 where orderid=?";
                PreparedStatement st=DBHelper.getConnection().prepareStatement(sql);
                st.setInt(1,orderid);
                if(st.executeUpdate()>0){
                    return true;
                }else {
                    return false;
                }
            }else if(rs.getInt("orderstatus")==1){
                String sql="update `order` set orderstatus=5 where orderid=?";
                PreparedStatement st=DBHelper.getConnection().prepareStatement(sql);
                st.setInt(1,orderid);
                String sql2="update `product` set over=over+? where productid=?";
                PreparedStatement st2=DBHelper.getConnection().prepareStatement(sql2);
                st2.setDouble(1,rs.getDouble("buysum")/rs.getDouble("buyprice"));
                st2.setInt(2,rs.getInt("productid"));
                if((st.executeUpdate()>0)&&(st2.executeUpdate()>0)){
                    return true;
                }else {
                    return false;
                }
            }else {
                return false;
            }
        }else {
            return false;
        }
    }
}
