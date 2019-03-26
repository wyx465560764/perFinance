package DAO;

import dbtool.DBHelper;
import entity.user;
import java.sql.*;

public class signUpDAO {
    public boolean userIsExist(user user) throws SQLException,Exception {
        Connection con=DBHelper.getConnection();
        String sql="select userid from user where username=?";
        PreparedStatement st=con.prepareStatement(sql);
        st.setString(1,user.getUsername());
        ResultSet rs=st.executeQuery();
        if(rs.next()){
            return false;
        }
        else {
            return true;
        }
    }
    public boolean emailIsExist(user user)throws SQLException,Exception {
        Connection con=DBHelper.getConnection();
        String sql="select userid from user where email=?";
        PreparedStatement st=con.prepareStatement(sql);
        st.setString(1,user.getEmail());
        ResultSet rs=st.executeQuery();
        if(rs.next()){
            return false;
        }
        else {
            return true;
        }
    }
    public boolean signUp(user user)throws SQLException,Exception{
        Connection con=DBHelper.getConnection();
        String sql="insert into user value(0,?,?,?,2,null,null)";
        PreparedStatement st=con.prepareStatement(sql);
        st.setString(1,user.getUsername());
        st.setString(2,user.getPassword());
        st.setString(3,user.getEmail());
        if(st.executeUpdate()>0){
            return true;
        }else {
            return false;
        }
    }
}
