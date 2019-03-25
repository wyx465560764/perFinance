package DAO;

import dbtool.DBHelper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import entity.wish;

public class deleteWishDAO {
    public boolean deleteWish(wish wish)throws SQLException,Exception{
        Connection con=DBHelper.getConnection();
        String sql="delete from wish where wishid=?";
        PreparedStatement st=con.prepareStatement(sql);
        st.setInt(1,wish.getWishId());
        if(st.executeUpdate()>0){
            st.close();
            return true;
        }else {
            st.close();
            return false;
        }
    }
}