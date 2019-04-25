package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dbtool.DBHelper;
import entity.product;

public class productDAO {
    private static productDAO instance=new productDAO();
    public static productDAO getInstance(){return instance;}
    public List<product> SelectProductEarnDetail(String userid) throws SQLException,Exception {
        String sql = "select * from product INNER JOIN product_dictionary ON product.type=product_dictionary.typeid where userid=? and earntype=2 order by productid desc";
        PreparedStatement st=DBHelper.getConnection().prepareStatement(sql);
        st.setString(1,userid);
        ResultSet rs=st.executeQuery();
        ArrayList<product> productArrayList=new ArrayList<>();
        while (rs.next()){
            product e=fromProductResultSet(rs);
            productArrayList.add(e);
        }
        st.close();
        rs.close();
        return productArrayList;
    }
    public List<product> SelectProductDetail(String userid) throws SQLException,Exception {
        String sql = "select * from product INNER JOIN product_dictionary ON product.type=product_dictionary.typeid where userid=? order by productid desc";
        PreparedStatement st=DBHelper.getConnection().prepareStatement(sql);
        st.setString(1,userid);
        ResultSet rs=st.executeQuery();
        ArrayList<product> productArrayList=new ArrayList<>();
        while (rs.next()){
            product e=fromProductResultSet(rs);
            productArrayList.add(e);
        }
        st.close();
        rs.close();
        return productArrayList;
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
