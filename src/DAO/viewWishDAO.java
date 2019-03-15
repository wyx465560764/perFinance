package DAO;

import dbtool.DBHelper;
import entity.wish;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class viewWishDAO {
    private static viewWishDAO instance=new viewWishDAO();
    public static viewWishDAO getInstance(){return instance;}
    public List<wish> selectByUser(String userid)throws SQLException{
        String sql = "select * from wish where userid=? and wishstatus=0 order by plantime desc LIMIT 10";
        Connection con=DBHelper.getConnection();
        PreparedStatement st=con.prepareStatement(sql);
        st.setString(1,userid);
        ResultSet rs=st.executeQuery();
        ArrayList<wish> wishArrayList=new ArrayList<>();
        while (rs.next()){
            wish e=fromResultSet(rs);
            wishArrayList.add(e);
        }
        return wishArrayList;
    }
    private wish fromResultSet(ResultSet rs)throws SQLException{
        wish e=new wish();
        e.setWishId(rs.getInt("wishid"));
        e.setUserId(rs.getInt("userid"));
        e.setWishName(rs.getString("wishname"));
        e.setPlanTime(rs.getDate("plantime"));
        e.setMoney(rs.getDouble("money"));
        e.setWishstatus(rs.getInt("wishstatus"));
        e.setPushTime(rs.getTime("pushtime"));

        return e;
    }
}
