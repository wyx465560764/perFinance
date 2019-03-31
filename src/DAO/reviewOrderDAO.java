package DAO;

import dbtool.DBHelper;
import entity.position;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class reviewOrderDAO {
    private static reviewOrderDAO instance=new reviewOrderDAO();
    public static reviewOrderDAO getInstance(){return instance;}
    public List<position> viewReview(int status, int userid)throws SQLException{
        String sql="SELECT\n" +
                "order.orderstatus," +
                "order.buyprice," +
                "order.buynum," +
                "order.buysum," +
                "order.pushtime," +
                "order.selltime," +
                "product.productname" +
                " FROM `order`,product" +
                " WHERE product.productid in(SELECT productid FROM product WHERE userid=?) and orderstatus=? and product.productid=`order`.productid";
        PreparedStatement st=DBHelper.getConnection().prepareStatement(sql);
        st.setInt(1,userid);
        st.setInt(2,status);
        ResultSet rs=st.executeQuery();
        ArrayList<position> list=new ArrayList<>();
        while (rs.next()){
            position p=fromOrderResultset(rs);
            list.add(p);
        }
        return list;
    }
    private position fromOrderResultset(ResultSet rs)throws SQLException{
        position order=new position();
        order.setProductname(rs.getString("productname"));
        order.setBuyprice(rs.getDouble("buyprice"));
        order.setBuynum(rs.getDouble("buynum"));
        order.setBuysum(rs.getDouble("buysum"));
        order.setPushtime(rs.getDate("pushtime"));
        order.setSelltime(rs.getDate("selltime"));
        order.setOrderstatus(rs.getInt("orderstatus"));
        return order;
    }
}
