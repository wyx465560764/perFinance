package DAO;

import dbtool.DBHelper;
import entity.bill;
import entity.wish;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class addBillWishDAO {
    private static addBillWishDAO instance=new addBillWishDAO();
    public static addBillWishDAO getInstance(){return instance;}
    public boolean addBill(bill bill)throws SQLException,Exception{
        Connection con=DBHelper.getConnection();
        String sql="insert into bill value (0,?,?,?,?,?,NOW(),?)";
        PreparedStatement st=con.prepareStatement(sql);
        st.setInt(1,bill.getUserId());
        st.setString(2,bill.getBillName());
        st.setDouble(3,bill.getMoney());
        st.setString(4,bill.getRemark());
        Date spentTime = new java.sql.Date(bill.getSpentTime().getTime());
        Date pushTime = new java.sql.Date(bill.getSpentTime().getTime());
        st.setDate(5,spentTime);
        st.setInt(6,bill.getType());
        if(st.executeUpdate()>0){
            return true;
        }else {
            return false;
        }
    }
    public boolean addWish(wish wish)throws SQLException,Exception{
        Connection con=DBHelper.getConnection();
        String sql="insert into wish value (0,?,?,?,?,?,NOW())";
        PreparedStatement st=con.prepareStatement(sql);
        st.setInt(1,wish.getUserId());
        st.setString(2,wish.getWishName());
        Date planTime = new java.sql.Date(wish.getPlanTime().getTime());
        st.setDate(3,planTime);
        st.setDouble(4,wish.getMoney());
//        Date pushTime = new java.sql.Date(wish.getPushTime().getTime());
        st.setInt(5,wish.getWishstatus());
        if(st.executeUpdate()>0){
            return true;
        }else {
            return false;
        }
    }
}
