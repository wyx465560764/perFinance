package DAO;

import dbtool.DBHelper;
import entity.earn;
import entity.productDetail;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class unfixedIncomedetailDAO {
    private static unfixedIncomedetailDAO instance=new unfixedIncomedetailDAO();
    public static unfixedIncomedetailDAO getInstance(){return instance;}
    public List<earn> selectUnfixedIncome(int productid,int time ) throws SQLException {
        String sql="select pushtime,price from earn where pushtime between date_sub(now(),interval ? MONTH) and now() and productid=? ORDER BY pushtime asc;";
        PreparedStatement st=DBHelper.getConnection().prepareStatement(sql);
        st.setInt(1,time);
        st.setInt(2,productid);
        ArrayList<earn> list=new ArrayList<>();
        ResultSet rs=st.executeQuery();
        while (rs.next()){
            earn e=new earn();
            e.setPrice(rs.getDouble("price"));
            e.setPushtime(rs.getDate("pushtime"));
            list.add(e);
        }
        rs.close();
        st.close();
        return list;
    }
    public productDetail selecteproductDetail (int productid) throws SQLException {
        String sql="SELECT" +
                " product.productid," +
                "product.productname," +
                "product_dictionary.earntype," +
                "product_dictionary.type," +
                "product.sum," +
                "product.over," +
                "product.nowprice," +
                "product.expectedincome," +
                "product.remark," +
                "product.userid," +
                "earn.pushtime," +
                "earn.price," +
                "`user`.username," +
                "`user`.managercontext" +
                " FROM (((product inner join product_dictionary on product_dictionary.typeid=product.type) INNER JOIN earn on product.productid=earn.productid) INNER JOIN user ON `user`.userid=product.userid)" +
                " WHERE product.productid=? and pushtime in(SELECT MIN(pushtime) from earn WHERE productid=?)";
        PreparedStatement st=DBHelper.getConnection().prepareStatement(sql);
        st.setInt(1,productid);
        st.setInt(2,productid);
        ResultSet rs=st.executeQuery();
        productDetail productDetail=new productDetail();
        if(rs.next()){
            productDetail.setProductid(rs.getInt("productid"));
            productDetail.setProductname(rs.getString("productname"));
            productDetail.setType(rs.getString("type"));
            productDetail.setEarntype(rs.getInt("earntype"));
            productDetail.setSum(rs.getDouble("sum"));
            productDetail.setOver(rs.getDouble("over"));
            productDetail.setNowprice(rs.getDouble("nowprice"));
            productDetail.setExpectedincome(rs.getDouble("expectedincome"));
            productDetail.setRemark(rs.getString("remark"));
            productDetail.setUserid(rs.getInt("userid"));
            productDetail.setPushtime(rs.getDate("pushtime"));
            productDetail.setPrice(rs.getDouble("price"));
            productDetail.setUsername(rs.getString("username"));
            productDetail.setManagercontext(rs.getString("managercontext"));
        }
        return productDetail;
    }
}
