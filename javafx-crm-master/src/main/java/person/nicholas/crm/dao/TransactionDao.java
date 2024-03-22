package person.nicholas.crm.dao;

import person.nicholas.crm.config.DatabaseConfig;
import person.nicholas.crm.entity.TransactionRecord;
import person.nicholas.crm.entity.Vendor;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TransactionDao {

    private final DatabaseConfig dbConfig;
    public TransactionDao() { dbConfig = DatabaseConfig.getInstance(); }


    public ArrayList<TransactionRecord> getTransactionList() {
        ArrayList<TransactionRecord> transactionRecordArrayList = new ArrayList<>();
        PreparedStatement pstmt = null;
        try{
            String sql;
            sql = "SELECT * FROM transaction_record";
            pstmt = dbConfig.getConnection().prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery(sql);

            while(rs.next()){
                TransactionRecord t = new TransactionRecord();
                t.setOrderId(rs.getInt("order_id"));
                t.setProductId(rs.getInt("product_id"));
                t.setCustomerId(rs.getInt("customer_id"));
                t.setQuantity(rs.getInt("quantity"));
                t.setShippingStatus(rs.getString("shipping_status"));

                transactionRecordArrayList.add(t);
            }

        }catch(SQLException se){
            // 处理 JDBC 错误
            se.printStackTrace();
        }catch(Exception e){
            // 处理 Class.forName 错误
            e.printStackTrace();
        }
        return transactionRecordArrayList;
    }


    public void addTransaction(TransactionRecord t){
        PreparedStatement pstmt = null;
        try{
            String sql;
            sql = "INSERT INTO transaction_record (product_id, customer_id, quantity, order_id, shipping_status) VALUES (?, ?, ?, ?, ?)";
            pstmt = dbConfig.getConnection().prepareStatement(sql);
            pstmt.setInt(1, t.getProductId());
            pstmt.setInt(2, t.getCustomerId());
            pstmt.setInt(3, t.getQuantity());
            pstmt.setInt(4, t.getOrderId());
            pstmt.setString(5, t.getShippingStatus());
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
