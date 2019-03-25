package DAO;

import dbtool.DBHelper;
import entity.bill;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class deleteBillDAO {
    public boolean deleteBill(bill bill)throws SQLException,Exception{
        Connection con=DBHelper.getConnection();
        String sql="delete from bill where billid=?";
        PreparedStatement st=con.prepareStatement(sql);
        st.setInt(1,bill.getBillId());
        if(st.executeUpdate()>0){
            st.close();
            return true;
        }else {
            st.close();
            return false;
        }
    }
}
