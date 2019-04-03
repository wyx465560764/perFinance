package DAO;

import dbtool.DBHelper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class identityDAO {
    public boolean isRealName(int userid)throws SQLException{
        String sql="select identity from `user` where userid=?";
        PreparedStatement st=DBHelper.getConnection().prepareStatement(sql);
        st.setInt(1,userid);
        ResultSet rs=st.executeQuery();
        if(rs.next()){
            if(rs.getString("identity").equals("")){
                return false;
            }else {
                return true;
            }
        }else {
            return true;
        }
    }
    public boolean identity(int userid,String identity)throws SQLException{
        String sql="UPDATE `user` set identity=? where userid=?";
        PreparedStatement st=DBHelper.getConnection().prepareStatement(sql);
        st.setString(1,identity);
        st.setInt(2,userid);
        if(st.executeUpdate()>0){
            return true;
        }else {
            return false;
        }
    }
}
