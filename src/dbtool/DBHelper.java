package dbtool;

import java.io.IOException;
import java.sql.*;
public class DBHelper{
    //Static instance of connection, only one will ever exist
    private static Connection connection = null;
    //Returns single instance of connection
    public static Connection getConnection(){
        //If instance has not been created yet, create it
        if(DBHelper.connection == null){
            initConnection();
        }
        return DBHelper.connection;
    }
    //Gets JDBC connection instance
    private static void initConnection(){
        try{
            Class.forName("com.mysql.jdbc.Driver");

            String url = "jdbc:mysql://localhost/perfinance?useSSL=false&amp;serverTimezone=UTC&amp;characterEncoding=utf-8";
            String user = "root";
            String pw = "123456";
            DBHelper.connection =
                    DriverManager.getConnection(url, user, pw);
        }
        catch (ClassNotFoundException e){
            System.out.println(e.getMessage());
            System.exit(0);
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
            System.exit(0);
        }
        catch (Exception e){
        }
    }
}
    //Gets JDBC connection instance
//    private static void initConnection(){
//        try{
//            Class.forName("com.mysql.jdbc.Driver");
//            String url = "jdbc:mysql://localhost:3306/perfinance?useSSL=false&amp;serverTimezone=UTC&amp;characterEncoding=utf-8";
//            String user = "root";
//            String pw = "123456";
//            DBHelper.connection = DriverManager.getConnection(url, user, pw);
//        }
//        catch (ClassNotFoundException e){
//            System.out.println(e.getMessage());
//            System.exit(0);
//        }
//        catch (SQLException e){
////            System.out.println(e.getMessage());
//            System.exit(0);
//        }
//        catch (Exception e){
//
//        }
//    }
//    public static void main(String[] args) {
//        try{
//            Connection con=DBHelper.getConnection();
//            String sql="SELECT * FROM user";
//            Statement st=con.createStatement();
//            ResultSet rs=st.executeQuery(sql);
//            if(rs.next()){
//                user u=new user();
//                u.setUserid(rs.getString("userid"));
//                u.setPassword(rs.getString("password"));
//                System.out.println(rs.getString("userid"));
//                System.out.println(rs.getString("password"));
//            }
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//        try {
//            Connection con=DBHelper.getConnection();
//            if(con!=null){
//                System.out.println("连接正常！");
//            }else{
//                System.out.println("连接异常！");
//            }
//        } catch (Exception e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//    }

//public class DBHelper {
//
//    public DBHelper() {
//        // TODO Auto-generated constructor stub
//    }
//    private static final String driver="com.mysql.jdbc.Driver";
//    private static final String url="jdbc:mysql://localhost:3306/perfinance?useSSL=false&amp;serverTimezone=UTC&amp;characterEncoding=utf-8";
//    private static final String username="root";
//    private static final String password="123456";
//
//    private static Connection con;
//    //加载驱动
//    static
//    {
//        try{
//            Class.forName(driver);
//        }catch (Exception e) {
//            e.printStackTrace( );
//        }
//    }
//
//
//    //单例模式返回数据库对象
//    public static Connection getConnection() throws Exception {
//        if(con==null){
//            con=DriverManager.getConnection(url,username,password);
//            return con;
//        }
//        return con;
//    }

//}
