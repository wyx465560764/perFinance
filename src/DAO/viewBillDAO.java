package DAO;

import dbtool.DBHelper;
import entity.bill;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class viewBillDAO {
    private static viewBillDAO instance=new viewBillDAO();
    public static viewBillDAO getInstance(){return instance;}
    public List<bill> selectByUser(String userid)throws SQLException {
        String sql = "select * from bill where userid=? and type=0 order by spenttime desc LIMIT 10";
        PreparedStatement st=DBHelper.getConnection().prepareStatement(sql);
        st.setString(1, userid);
        ResultSet rs = st.executeQuery();
        ArrayList<bill> billArrayList = new ArrayList<>();
        while (rs.next()) {
            bill e = fromResultSet(rs);
            billArrayList.add(e);
        }
        st.close();
        rs.close();
        return billArrayList;
    }
    private bill fromResultSet(ResultSet rs)throws SQLException{
        bill e=new bill();
        e.setBillId(rs.getInt("billid"));
        e.setUserId(rs.getInt("userid"));
        e.setBillName(rs.getString("billname"));
        e.setRemark(rs.getString("remark"));
        e.setMoney(rs.getDouble("money"));
        e.setSpentTime(rs.getDate("spenttime"));
        e.setPushTime(rs.getTime("pushtime"));
        return e;
    }
}
