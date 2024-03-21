package person.nicholas.crm.dao;

import person.nicholas.crm.config.DatabaseConfig;
import person.nicholas.crm.entity.Vendor;

import java.sql.Connection;
import java.sql.*;
import java.sql.SQLException;
import java.util.ArrayList;


public class VendorDao{
    private final DatabaseConfig dbConfig;
    public VendorDao(){
        dbConfig = DatabaseConfig.getInstance();
    }

    public ArrayList<Vendor> getVendorList(){
        ArrayList<Vendor> vendorList = new ArrayList<>();
        PreparedStatement pstmt = null;
        try{
            String sql;
            sql = "SELECT * FROM vendor";
            pstmt = dbConfig.getConnection().prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery(sql);

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
        }catch(SQLException se){
            // 处理 JDBC 错误
            se.printStackTrace();
        }catch(Exception e){
            // 处理 Class.forName 错误
            e.printStackTrace();
        }
        return vendorList;
    }

    //Add Vendor
    public void addVendor(Vendor v){
        PreparedStatement pstmt = null;
        try{
            String sql;
            sql = "INSERT INTO vendor (business_name, geo_presence, customer_feedback_score) VALUES (?, ?, ?)";
            pstmt = dbConfig.getConnection().prepareStatement(sql);
            pstmt.setString(1, v.getBusinessName());
            pstmt.setString(2, v.getGeographicalPresence());
            pstmt.setInt(3, v.getFeedbackScore());
            pstmt.executeUpdate();
        }catch(SQLException se){
            // 处理 JDBC 错误
            se.printStackTrace();
        }catch(Exception e){
            // 处理 Class.forName 错误
            e.printStackTrace();
        }
    }

}
