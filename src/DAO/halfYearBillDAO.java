package DAO;

import dbtool.DBHelper;
import entity.monthBill;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class halfYearBillDAO {
    private static halfYearBillDAO instance=new halfYearBillDAO();
    public static halfYearBillDAO getInstance(){return instance;}
    public ArrayList<monthBill> halfYearBill(String userid) throws SQLException,Exception {
        String sql="select DATE_FORMAT(spenttime,'%Y-%m') AS month,sum(money) as sum from bill where userid=? and type=0 group by DATE_FORMAT(spenttime,'%Y-%m') ORDER BY DATE_FORMAT(spenttime,'%Y-%m') ASC ";
        PreparedStatement st=DBHelper.getConnection().prepareStatement(sql);
        st.setString(1,userid);
        ResultSet rs=st.executeQuery();
        ArrayList<monthBill> monthBillArrayList=new ArrayList<>();
        while (rs.next()){
            monthBill monthBill=fromResultSet(rs);
            monthBillArrayList.add(monthBill);
        }
        st.close();
        rs.close();
        return monthBillArrayList;
    }
    private monthBill fromResultSet(ResultSet rs)throws SQLException,Exception{
        monthBill monthBill=new monthBill();
        monthBill.setMonth(rs.getString("month"));
        monthBill.setSum(rs.getString("sum"));
        return monthBill;
    }
}
