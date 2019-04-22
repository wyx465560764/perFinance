package DAO;

import dbtool.DBHelper;
import entity.bill;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.sql.Date;

public class changeBillDAO {
    private static changeBillDAO instance=new changeBillDAO();
    public static changeBillDAO getInstance(){return instance;}
    public bill changeBillSelect(String billid)throws SQLException {
        String sql="select * from bill,bill_dictionary where billid=? and billnamenum=billname";
        Connection con=DBHelper.getConnection();
        PreparedStatement st=con.prepareStatement(sql);
        st.setString(1,billid);
        ResultSet rs=st.executeQuery();
        bill bill=new bill();
        if(rs.next()){
            bill=fromBillResultSet(rs);
        }
        st.close();
        rs.close();
        return bill;
    }
    public boolean changeBill(bill bill)throws SQLException {
        String sql="update bill set billname=?,money=?,remark=?,spenttime=? where billid=?";
        PreparedStatement st=DBHelper.getConnection().prepareStatement(sql);
        st.setInt(1,bill.getBillName());
        st.setDouble(2,bill.getMoney());
        st.setString(3,bill.getRemark());

        Date spenttime = new java.sql.Date(bill.getSpentTime().getTime());
        st.setDate(4,spenttime);
        st.setInt(5,bill.getBillId());
        if(st.executeUpdate()>0){
            st.close();
            return true;
        }else {
            st.close();
            return false;
        }
    }
    private bill fromBillResultSet(ResultSet rs)throws SQLException{
        bill e=new bill();
        e.setBillId(rs.getInt("billid"));
        e.setUserId(rs.getInt("userid"));
        e.setBillName(rs.getInt("billname"));
        e.setBillNameText(rs.getString("billnametext"));
        e.setRemark(rs.getString("remark"));
        e.setMoney(rs.getDouble("money"));
        e.setType(rs.getInt("type"));
        e.setSpentTime(rs.getDate("spenttime"));
        e.setPushTime(rs.getDate("pushtime"));
        return e;
    }
}
