package person.nicholas.crm.dao;

import person.nicholas.crm.entity.Vendor;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.*;
import java.sql.SQLException;
import java.util.ArrayList;

//import net.javaguides.usermanagement.model.User;

public class VendorDAO{
    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://hkg1.clusters.zeabur.com:31183/vendor_system?useSSL=false";
    // 数据库的用户名与密码，需要根据自己的设置
    static final String USER = "root";
    static final String PASS = "a6L15HnBQImgxt3zG2b0cUJ8Ks4R7Z9p";

    public static void main(String[] args) {
        ArrayList<Vendor> list = getVendorSql();
        if(list.size() == 0){
            System.out.println("暂无数据");
        }else{
            for(Vendor v: list){  //遍历集合数据
                System.out.println(v.getVendorId()+"\t"+v.getBusinessName()+"\t"+v.getFeedbackScore()+"\t"+v.getGeographicalPresence());
            }
        }

    }
    public static ArrayList<Vendor> getVendorSql(){
        ArrayList<Vendor> vendorList = new ArrayList<Vendor>();
        Connection conn = null;
        Statement stmt = null;
        try{
            // 注册 JDBC 驱动
            Class.forName(JDBC_DRIVER);

            // 打开链接
            conn = DriverManager.getConnection(DB_URL,USER,PASS);

            // 执行查询
            stmt = conn.createStatement();
            String sql;
            sql = "SELECT * FROM vendor";
            ResultSet rs = stmt.executeQuery(sql);

            // 展开结果集数据库
            while(rs.next()){
                // 通过字段检索
                Vendor v = new Vendor();
                v.setVendorId(rs.getInt("vendor_id"));
                v.setBusinessName(rs.getString("business_name"));
                v.setCustomerScore(rs.getInt("customer_feedback_score"));
                v.setGeographicalPresence(rs.getString("geo_presence"));

                vendorList.add(v);
            }
            // 完成后关闭
            rs.close();
            stmt.close();
            conn.close();
        }catch(SQLException se){
            // 处理 JDBC 错误
            se.printStackTrace();
        }catch(Exception e){
            // 处理 Class.forName 错误
            e.printStackTrace();
        }finally{
            // 关闭资源
            try{
                if(stmt!=null) stmt.close();
            }catch(SQLException se2){
            }// 什么都不做
            try{
                if(conn!=null) conn.close();
            }catch(SQLException se){
                se.printStackTrace();
            }
        }
        return vendorList;
    }
}
