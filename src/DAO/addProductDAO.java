package DAO;

import dbtool.DBHelper;
import entity.product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class addProductDAO {
    private static addProductDAO instance=new addProductDAO();
    public static addProductDAO getInstance(){return instance;}
    public boolean addProduct(product product)throws SQLException,Exception{
        String usql="select type from user where userid=?";
        PreparedStatement stu=DBHelper.getConnection().prepareStatement(usql);
        stu.setInt(1,product.getUserid());
        ResultSet rs=stu.executeQuery();
        if(rs.next()){
            if(rs.getInt("type")!=1)
            return false;
        }
        stu.close();
        String sql="insert into product value (0,?,?,?,?,?,?,?,?)";
        PreparedStatement st=DBHelper.getConnection().prepareStatement(sql);
        st.setString(1,product.getProductname());
        st.setInt(2,product.getTypeid());
//        st.setInt(3,product.getEarntype());
        st.setDouble(3,product.getSum());
        st.setDouble(4,product.getSum());
        st.setDouble(5,product.getNowprice());
        st.setDouble(6,product.getExpectedincome());
        st.setString(7,product.getRemark());
        st.setInt(8,product.getUserid());
        DBHelper.getConnection().setAutoCommit(false);
        int stStatus=st.executeUpdate();
        DBHelper.getConnection().commit();
        DBHelper.getConnection().setAutoCommit(true);
        String sqlp="select productid from product where userid=? order by productid desc";
        PreparedStatement stp=DBHelper.getConnection().prepareStatement(sqlp);
        stp.setDouble(1,product.getUserid());
        ResultSet rsp=stp.executeQuery();
        rsp.next();
        product.setProductid(rsp.getInt("productid"));
        stp.close();
        rsp.close();
        String sqle="insert into earn value (0,NOW(),?,?)";
        PreparedStatement ste=DBHelper.getConnection().prepareStatement(sqle);
        ste.setDouble(1,product.getNowprice());
        ste.setDouble(2,product.getProductid());
        if(stStatus>0&&(ste.executeUpdate()>0)){
            st.close();
            stu.close();
            return true;
        }else {
            st.close();
            stu.close();
            return false;
        }
    }
}
