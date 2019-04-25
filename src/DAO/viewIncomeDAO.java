package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dbtool.DBHelper;
import entity.product;
import org.omg.Messaging.SYNC_WITH_TRANSPORT;

public class viewIncomeDAO {
    private static viewIncomeDAO instance=new viewIncomeDAO();
    public static viewIncomeDAO getInstance(){return instance;}
    public List<product> selectUnfixedIcome(String type)throws SQLException {
        String sql="select * from product INNER JOIN product_dictionary ON product.type=product_dictionary.typeid where typeid=?";
        PreparedStatement st=DBHelper.getConnection().prepareStatement(sql);
        st.setString(1,type);
        ResultSet rs=st.executeQuery();
        ArrayList<product> list=new ArrayList<>();
        while (rs.next()){
            product e=fromProductResultSet(rs);
            list.add(e);
        }
        st.close();
        rs.close();
        return list;
    }
    private product fromProductResultSet(ResultSet rs)throws SQLException{
        product e=new product();
        e.setProductid(rs.getInt("productid"));
        e.setProductname(rs.getString("productname"));
        e.setType(rs.getString("product_dictionary.type"));
        e.setEarntype(rs.getInt("product_dictionary.earntype"));
        e.setSum(rs.getDouble("sum"));
        e.setOver(rs.getDouble("over"));
        e.setNowprice(rs.getDouble("nowprice"));
        e.setExpectedincome(rs.getInt("expectedincome"));
        e.setRemark(rs.getString("remark"));
        e.setUserid(rs.getInt("userid"));
        return e;
    }
}
