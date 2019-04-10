package DAO;

import dbtool.DBHelper;
import entity.bill;
import entity.wish;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class searchDAO {
    private static searchDAO instance=new searchDAO();
    public static searchDAO getInstance(){return instance;}
    public List<bill> searchBill(List list) throws SQLException,Exception {
        String remark = (String) list.get(0);
        String billName = (String) list.get(1);
        String billMonthMin = (String) list.get(2);
        String billMonthMax = (String) list.get(3);
        String priceMin = (String) list.get(4);
        String priceMax = (String) list.get(5);
        String pushMonthMin = (String) list.get(6);
        String pushMonthMax = (String) list.get(7);
        String userId = (String) list.get(8);
        StringBuffer sql=new StringBuffer();
        sql.append("select * from bill where userid='"+userId+"'");
        if(!remark.equals("请输入关键词")){
            sql.append("and remark like '%"+remark+"%'");
        }
        if (billName!=""){
            sql.append(" and billname='"+billName+"'");
        }
        if(billMonthMin!=""&&billMonthMax!=""){
            sql.append(" and spenttime BETWEEN '"+billMonthMin+"'and '"+billMonthMax+"'");
        }
        if(priceMin!=""){
            sql.append(" and money >'"+priceMin+"'");
        }
        if(priceMax!=""){
            sql.append(" and money <'"+priceMax+"'");
        }
        if(pushMonthMin!=""&&pushMonthMax!=""){
            sql.append(" and pushtime BETWEEN '"+pushMonthMin+"'and '"+pushMonthMax+"'");
        }
        sql.append("order by spenttime desc");
        Statement st=DBHelper.getConnection().createStatement();
        ResultSet rs=st.executeQuery(sql.toString());
        ArrayList<bill> billArrayList=new ArrayList<>();
        while (rs.next()) {
            bill e = fromBillResultSet(rs);
//            System.out.println(rs.getString("wishname"));
            billArrayList.add(e);
        }
        st.close();
        rs.close();
        return billArrayList;
    }
    public List<wish> searchWish(List list) throws SQLException,Exception{
        String wishName=(String) list.get(0);
        String status=(String) list.get(1);
        String planMonthMin=(String) list.get(2);
        String planMonthMax=(String) list.get(3);
        String priceMin=(String)list.get(4);
        String priceMax=(String)list.get(5);
        String wishMonthMin=(String) list.get(6);
        String wishMonthMax=(String) list.get(7);
        String userId=(String) list.get(8);
        Connection con=DBHelper.getConnection();
        StringBuffer sql=new StringBuffer();
        sql.append("select * from wish where userid='"+userId+"'");
        if(!wishName.equals("请输入关键词")){
            sql.append(" and wishname like '%"+wishName+"%'");
        }
        if (status!=""){
            sql.append(" and wishstatus="+status);
        }
        if(planMonthMin!=""&&planMonthMax!=""){
            sql.append(" and plantime BETWEEN '"+planMonthMin+"'and '"+planMonthMax+"'");
        }
        if(priceMin!=""){
            sql.append(" and money >'"+priceMin+"'");
        }
        if(priceMax!=""){
            sql.append(" and money <'"+priceMax+"'");
        }
        if(wishMonthMin!=""&&wishMonthMax!=""){
            sql.append(" and pushtime BETWEEN '"+wishMonthMin+"'and '"+wishMonthMax+"'");
        }
        sql.append(" order by plantime desc");
//        System.out.println(sql);
        Statement st=con.createStatement();
        ResultSet rs=st.executeQuery(sql.toString());
        ArrayList<wish> wishArrayList=new ArrayList<>();
        while (rs.next()) {
            wish e = fromWishResultSet(rs);
//            System.out.println(rs.getString("wishname"));
            wishArrayList.add(e);
        }
        st.close();
        rs.close();
        return wishArrayList;
    }
    private wish fromWishResultSet(ResultSet rs)throws SQLException{
        wish e=new wish();
        e.setWishId(rs.getInt("wishid"));
        e.setUserId(rs.getInt("userid"));
        e.setWishName(rs.getString("wishname"));
        e.setPlanTime(rs.getDate("plantime"));
        e.setMoney(rs.getDouble("money"));
        e.setWishstatus(rs.getInt("wishstatus"));
        e.setPushTime(rs.getTime("pushtime"));

        return e;
    }
    private bill fromBillResultSet(ResultSet rs)throws SQLException{
        bill e=new bill();
        e.setBillId(rs.getInt("billid"));
        e.setUserId(rs.getInt("userid"));
        e.setBillName(rs.getString("billname"));
        e.setRemark(rs.getString("remark"));
        e.setMoney(rs.getDouble("money"));
        e.setType(rs.getInt("type"));
        e.setSpentTime(rs.getDate("spenttime"));
        e.setPushTime(rs.getDate("pushtime"));
        return e;
    }
}
