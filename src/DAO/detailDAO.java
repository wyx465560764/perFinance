package DAO;

import dbtool.DBHelper;
import entity.bill;
import entity.wish;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class detailDAO {
    private static detailDAO instance=new detailDAO();
    public static detailDAO getInstance(){return instance;}
    public List<wish> selectWishByFirst(String userid)throws SQLException{
        String sql = "select * from wish where userid=? order by pushtime desc";
        PreparedStatement st=DBHelper.getConnection().prepareStatement(sql);
        st.setString(1,userid);
        ResultSet rs=st.executeQuery();
        ArrayList<wish> wishArrayList=new ArrayList<>();
        while (rs.next()){
            wish e=fromWishResultSet(rs);
            wishArrayList.add(e);
        }
        st.close();
        rs.close();
        return wishArrayList;
    }
    public List<bill> selectBillByFirst(String userid)throws SQLException{
        String sql = "select * from bill where userid=? order by spenttime desc";
        PreparedStatement st=DBHelper.getConnection().prepareStatement(sql);
        st.setString(1,userid);
        ResultSet rs=st.executeQuery();
        ArrayList<bill> billArrayList=new ArrayList<>();
        while (rs.next()){
            bill e=fromBillResultSet(rs);
            billArrayList.add(e);
        }
        st.close();
        rs.close();
        return billArrayList;
    }
    private bill fromBillResultSet(ResultSet rs)throws SQLException{
        bill e=new bill();
        e.setBillId(rs.getInt("billid"));
        e.setUserId(rs.getInt("userid"));
        e.setBillName(rs.getString("billname"));
        e.setRemark(rs.getString("remark"));
        e.setMoney(rs.getDouble("money"));
        e.setType(rs.getInt("type"));
        e.setSpentTime(rs.getDate("spenttime"));
        e.setPushTime(rs.getDate("pushtime"));
        return e;
    }
    private wish fromWishResultSet(ResultSet rs)throws SQLException{
        wish e=new wish();
        e.setWishId(rs.getInt("wishid"));
        e.setUserId(rs.getInt("userid"));
        e.setWishName(rs.getString("wishname"));
        e.setPlanTime(rs.getDate("plantime"));
        e.setMoney(rs.getDouble("money"));
        e.setWishstatus(rs.getInt("wishstatus"));
        e.setPushTime(rs.getDate("pushtime"));

        return e;
    }
}
