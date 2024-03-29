package DAO;

import dbtool.DBHelper;
import entity.position;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class viewPositionDAO {
    private static viewPositionDAO instance=new viewPositionDAO();
    public static viewPositionDAO getInstance(){return instance;}
    public List<position> selectPosition(int userid)throws SQLException {
        String sql="SELECT" +
                " product.productname," +
                " product_dictionary.type," +
                " product_dictionary.earntype," +
                " product.nowprice," +
                " product.expectedincome," +
                " order.orderid," +
                " order.orderstatus," +
                " order.selltime," +
                " order.pushtime," +
                " order.buysum," +
                " order.buyprice," +
                " order.buynum," +
                " order.sellprice" +
                " FROM product INNER JOIN product_dictionary ON product.type=product_dictionary.typeid,`order`" +
                " WHERE `order`.userid=? and product.productid=`order`.productid order by orderid desc ";
        PreparedStatement st=DBHelper.getConnection().prepareStatement(sql);
        st.setInt(1,userid);
        ResultSet rs=st.executeQuery();
        ArrayList<position> list=new ArrayList<>();
        while (rs.next()){
            position position=fromResultSet(rs);
            list.add(position);
        }
        st.close();
        rs.close();
        return list;
    }
    private position fromResultSet(ResultSet rs) throws SQLException{
        position position=new position();
        position.setProductname(rs.getString("productname"));
        position.setType(rs.getString("product_dictionary.type"));
        position.setEarntype(rs.getInt("product_dictionary.earntype"));
        position.setNowprice(rs.getDouble("nowprice"));
        position.setExpectedincome(rs.getDouble("expectedincome"));
        position.setOrderid(rs.getInt("orderid"));
        position.setOrderstatus(rs.getInt("orderstatus"));
        position.setSelltime(rs.getDate("selltime"));
        position.setPushtime(rs.getDate("pushtime"));
        position.setBuysum(rs.getDouble("buysum"));
        position.setBuyprice(rs.getDouble("buyprice"));
        position.setBuynum(rs.getDouble("buynum"));
        position.setSellprice(rs.getDouble("sellprice"));
        return position;
    }
}
