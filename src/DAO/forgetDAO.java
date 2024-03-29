package DAO;

import entity.user;
import dbtool.DBHelper;
import java.sql.*;
public class forgetDAO {
    public boolean forget(user user)throws SQLException,Exception{
        String sql="update user set password=? where email=?";
        PreparedStatement st=DBHelper.getConnection().prepareStatement(sql);
        st.setString(1,user.getPassword());
        st.setString(2,user.getEmail());
        if(st.executeUpdate()>0){
            st.close();
            return true;
        }else {
            st.close();
            return false;
        }
    }
}
