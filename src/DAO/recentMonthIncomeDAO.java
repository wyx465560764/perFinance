package DAO;

import dbtool.DBHelper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class recentMonthIncomeDAO {
    private static recentMonthIncomeDAO instance=new recentMonthIncomeDAO();
    public static recentMonthIncomeDAO getInstance(){return instance;}
    public double monthIncome (int productid,int time)throws SQLException{
        String sql="select price from earn where pushtime between date_sub(now(),interval ? MONTH) and now() and productid=? ORDER BY pushtime asc LIMIT 1;";
        PreparedStatement st=DBHelper.getConnection().prepareStatement(sql);
        st.setInt(1,time);
        st.setInt(2,productid);
        ResultSet rs=st.executeQuery();
        double Income =0;
        if(rs.next()){
            Income=rs.getDouble("price");
        }
        st.close();
        rs.close();
        return Income;
    }
}
