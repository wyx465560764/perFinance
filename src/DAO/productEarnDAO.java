package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dbtool.DBHelper;
import entity.product;

public class productEarnDAO {
    private static productEarnDAO instance=new productEarnDAO();
    public static productEarnDAO getInstance(){return instance;}
    public List<product> SelectProductEarnDetail(String userid) throws SQLException,Exception {
        String sql = "select * from product where userid=? and earntype=2 order by productid desc";
        Connection con=DBHelper.getConnection();
        PreparedStatement st=con.prepareStatement(sql);
        st.setString(1,userid);
        ResultSet rs=st.executeQuery();
        ArrayList<product> wishArrayList=new ArrayList<>();
        while (rs.next()){
            product e=fromProductResultSet(rs);
            wishArrayList.add(e);
        }
        return wishArrayList;
    }
    private product fromProductResultSet(ResultSet rs)throws SQLException{
        product e=new product();
        e.setProductid(rs.getInt("productid"));
        e.setProductname(rs.getString("productname"));
        e.setType(rs.getString("type"));
        e.setEarntype(rs.getInt("earntype"));
        e.setSum(rs.getDouble("sum"));
        e.setOver(rs.getDouble("over"));
        e.setNowprice(rs.getDouble("nowprice"));
        e.setExpectedincome(rs.getInt("expectedincome"));
        e.setRemark(rs.getString("remark"));
        e.setUserid(rs.getInt("userid"));
        return e;
    }
}
