package person.nicholas.crm.dao;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import person.nicholas.crm.config.DatabaseConfig;
import person.nicholas.crm.entity.TransactionRecord;
import person.nicholas.crm.entity.Vendor;

import java.sql.Date;
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
            sql = "SELECT transaction_record.*, product.product_name, customer.customer_name FROM transaction_record\n" +
                    "left join product on transaction_record.product_id = product.product_id\n" +
                    "left join customer_profiles as customer on transaction_record.customer_id = customer.customer_id";
            pstmt = dbConfig.getConnection().prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery(sql);

            while(rs.next()){
                TransactionRecord t = new TransactionRecord();
                t.setTransactionId(rs.getInt("transaction_id"));
                t.setOrderId(new SimpleStringProperty(rs.getString("order_id")));
                t.setProductId(new SimpleIntegerProperty(rs.getInt("product_id")));
                t.setCustomerId(new SimpleIntegerProperty(rs.getInt("customer_id")));
                t.setQuantity(new SimpleIntegerProperty(rs.getInt("quantity")));
                t.setShippingStatus(new SimpleStringProperty(rs.getString("shipping_status")));

                t.setCustomerName(new SimpleStringProperty(rs.getString("customer_name")));
                t.setProductName(new SimpleStringProperty(rs.getString("product_name")));
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
            pstmt.setString(4, t.getOrderId().get());
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
            sql = "INSERT INTO transaction_record (product_id, customer_id, quantity, order_id, shipping_status,transaction_time) VALUES (?, ?, ?, ?, ?, ?)";
            pstmt = dbConfig.getConnection().prepareStatement(sql);
            for(TransactionRecord transactionRecord : t){
                pstmt.setInt(1, transactionRecord.getProductId().get());
                pstmt.setInt(2, transactionRecord.getCustomerId().get());
                pstmt.setInt(3, transactionRecord.getQuantity().get());
                pstmt.setString(4, transactionRecord.getOrderId().get());
                pstmt.setString(5, transactionRecord.getShippingStatus().get());
                pstmt.setDate(6, transactionRecord.getTransactionTime());
                pstmt.addBatch();
            }
            pstmt.executeBatch();
        }catch(SQLException se){
            // 处理 JDBC 错误
            se.printStackTrace();
        }catch(Exception e) {
            // 处理 Class.forName 错误
            e.printStackTrace();
            // 处理 Class.forName 错误
        }

    }

    //update
    public void cancelTransaction(int transactionId) {
        PreparedStatement pstmt = null;
        try{
            String sql;
            sql = "UPDATE transaction_record SET shipping_status = ? WHERE transaction_id = ?";
            pstmt = dbConfig.getConnection().prepareStatement(sql);
            pstmt.setString(1, "Cancelled");
            pstmt.setInt(2, transactionId);
            pstmt.executeUpdate();
        }catch(SQLException se){
            // 处理 JDBC 错误
            se.printStackTrace();
        }catch(Exception e){
            // 处理 Class.forName 错误
            e.printStackTrace();
        }
    }

    public void cancelOrder(String orderId) {
        PreparedStatement pstmt = null;
        try{
            String sql;
            sql = "UPDATE transaction_record SET shipping_status = ? WHERE order_id = ?";
            pstmt = dbConfig.getConnection().prepareStatement(sql);
            pstmt.setString(1, "Cancelled");
            pstmt.setString(2, orderId);
            pstmt.executeUpdate();
        }catch(SQLException se){
            // 处理 JDBC 错误
            se.printStackTrace();
        }catch(Exception e){
            // 处理 Class.forName 错误
            e.printStackTrace();
        }
    }

    public void deleteOrder(String orderId) {
        PreparedStatement pstmt = null;
        try {
            String sql;
            sql = "DELETE FROM transaction_record WHERE order_id = ?";
            pstmt = dbConfig.getConnection().prepareStatement(sql);
            pstmt.setString(1, orderId);
            pstmt.executeUpdate();
        } catch (SQLException se) {
            // 处理 JDBC 错误
            se.printStackTrace();
        } catch (Exception e) {
            // 处理 Class.forName 错误
            e.printStackTrace();
        }
    }
}
