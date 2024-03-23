package person.nicholas.crm.dao;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
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
                t.setOrderId(new SimpleIntegerProperty(rs.getInt("order_id")));
                t.setProductId(new SimpleIntegerProperty(rs.getInt("product_id")));
                t.setCustomerId(new SimpleIntegerProperty(rs.getInt("customer_id")));
                t.setQuantity(new SimpleIntegerProperty(rs.getInt("quantity")));
                t.setShippingStatus(new SimpleStringProperty(rs.getString("shipping_status")));

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
            pstmt.setInt(1, t.getProductId().get());
            pstmt.setInt(2, t.getCustomerId().get());
            pstmt.setInt(3, t.getQuantity().get());
            pstmt.setInt(4, t.getOrderId().get());
            pstmt.setString(5, t.getShippingStatus().get());
            pstmt.executeUpdate();
        }catch(SQLException se){
            // 处理 JDBC 错误
            se.printStackTrace();
        }catch(Exception e){
            // 处理 Class.forName 错误
            e.printStackTrace();
        }
    }

    public void batchAddTransaction(List<TransactionRecord> t){
        PreparedStatement pstmt = null;
        try{
            String sql;
            sql = "INSERT INTO transaction_record (product_id, customer_id, quantity, order_id, shipping_status) VALUES (?, ?, ?, ?, ?)";
            pstmt = dbConfig.getConnection().prepareStatement(sql);
            for(TransactionRecord transactionRecord : t){
                pstmt.setInt(1, transactionRecord.getProductId().get());
                pstmt.setInt(2, transactionRecord.getCustomerId().get());
                pstmt.setInt(3, transactionRecord.getQuantity().get());
                pstmt.setInt(4, transactionRecord.getOrderId().get());
                pstmt.setString(5, transactionRecord.getShippingStatus().get());
                pstmt.addBatch();
            }
            pstmt.executeBatch();
        }catch(SQLException se){
            // 处理 JDBC 错误
            se.printStackTrace();
        }catch(Exception e) {
            // 处理 Class.forName 错误
        }

    }
}
