package DAO;

import dbtool.DBHelper;
import entity.assetAllocation;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class assetAllocationDAO {
    private static assetAllocationDAO instance=new assetAllocationDAO();
    public static assetAllocationDAO getInstance(){return instance;}
    public List<assetAllocation> selectAssetAllocation(int userid)throws SQLException{
        String sql="SELECT product.type,sum(`order`.buysum) as sum FROM `order`,product WHERE `order`.userid=? and orderstatus=2 and product.productid=`order`.productid GROUP BY product.type";
        PreparedStatement st=DBHelper.getConnection().prepareStatement(sql);
        st.setInt(1,userid);
        ResultSet rs=st.executeQuery();
        ArrayList<assetAllocation> list=new ArrayList<>();
        while (rs.next()){
            assetAllocation e=new assetAllocation();
            e.setType(rs.getString("type"));
            e.setSum(rs.getDouble("sum"));
            list.add(e);
        }
        return list;
    }
}
