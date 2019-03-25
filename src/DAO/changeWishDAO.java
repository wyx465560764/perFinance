package DAO;

import dbtool.DBHelper;
import entity.wish;

import java.sql.*;

public class changeWishDAO {
    private static changeWishDAO instance=new changeWishDAO();
    public static changeWishDAO getInstance(){return instance;}
    public wish changeWishSelect(String wishid)throws SQLException {
        String sql="select * from wish where wishid=?";
        Connection con=DBHelper.getConnection();
        PreparedStatement st=con.prepareStatement(sql);
        st.setString(1,wishid);
        ResultSet rs=st.executeQuery();
        wish wish=new wish();
        if(rs.next()){
            wish=fromWishResultSet(rs);
        }
        st.close();
        rs.close();
        return wish;
    }
    public boolean changeWish(wish wish)throws SQLException {
        String sql="update wish set wishname=?,money=?,wishstatus=?,plantime=? where wishid=?";
        Connection con=DBHelper.getConnection();
        PreparedStatement st=con.prepareStatement(sql);
        st.setString(1,wish.getWishName());
        st.setDouble(2,wish.getMoney());
        st.setInt(3,wish.getWishstatus());

        Date plantime = new java.sql.Date(wish.getPlanTime().getTime());
        st.setDate(4,plantime);
        st.setInt(5,wish.getWishId());
//        if(wish.getBillName().equals("收入")){
//            st.setInt(5,1);
//        }else {
//            st.setInt(5,0);
//        }
        if(st.executeUpdate()>0){
            st.close();
            return true;
        }else {
            st.close();
            return false;
        }
    }
    private wish fromWishResultSet(ResultSet rs)throws SQLException{
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
