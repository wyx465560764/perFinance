package DAO;

import dbtool.DBHelper;
import entity.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class userDAO {
    public user login(user user) throws SQLException,Exception {

//        Class.forName("com.mysql.jdbc.Driver");
//        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/perfinance?useSSL=false&amp;serverTimezone=UTC&amp;characterEncoding=utf-8","root","123456");
//        System.out.println(con);
        String sql="select * from user where username=? and password=?";
        PreparedStatement st=DBHelper.getConnection().prepareStatement(sql);
        st.setString(1,user.getUsername());
        st.setString(2,user.getPassword());
        ResultSet rs=st.executeQuery();
        if(rs.next()){
            user.setUserid(rs.getString("userid"));
            user.setEmail(rs.getString("email"));
            user.setType(rs.getString("type"));
        }
        return user;
    }
}
