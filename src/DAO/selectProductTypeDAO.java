package DAO;

import dbtool.DBHelper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class selectProductTypeDAO {
    private static selectProductTypeDAO instance=new selectProductTypeDAO();
    public static selectProductTypeDAO getInstance(){ return instance; }
    public String getProductTypeDAO(int typeid)throws SQLException {
        String sql="select type from product_dictionary where typeid=?";
        PreparedStatement st=DBHelper.getConnection().prepareStatement(sql);
        st.setInt(1,typeid);
        ResultSet rs=st.executeQuery();
        if(rs.next()){
            return rs.getString("type");
        }else {
            return null;
        }
    }
}
