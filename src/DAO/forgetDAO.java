package DAO;

import entity.user;
import dbtool.DBHelper;
import java.sql.*;
public class forgetDAO {
    public boolean forget(user user)throws SQLException,Exception{
        Connection con=DBHelper.getConnection();
        String sql="update user set password=? where email=?";
        PreparedStatement st=con.prepareStatement(sql);
        st.setString(1,user.getPassword());
        st.setString(2,user.getEmail());
        if(st.executeUpdate()>0){
            return true;
        }else {
            return false;
        }
    }
}
