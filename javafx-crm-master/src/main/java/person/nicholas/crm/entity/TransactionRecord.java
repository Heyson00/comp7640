package person.nicholas.crm.entity;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import lombok.Data;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.DateTimeException;
import java.util.Date;
import java.util.List;

public class TransactionRecord {

//    private int transactionId;
//    private int customerId;
//    private List<Product> products;
//    private String transactionDate;
    private final SimpleIntegerProperty productId = new SimpleIntegerProperty();
    private final SimpleIntegerProperty customerId = new SimpleIntegerProperty();
    private final SimpleIntegerProperty quantity = new SimpleIntegerProperty();
    private final SimpleIntegerProperty orderId = new SimpleIntegerProperty();
    private final SimpleStringProperty shippingStatus = new SimpleStringProperty();
    private final SimpleDateFormat transactionTime = new SimpleDateFormat();

    public TransactionRecord(){}

    public TransactionRecord(int productId, int customerId, int quantity, int orderId, String shippingStatus) {
        this.productId.set(productId);
        this.customerId.set(customerId);
        this.quantity.set(quantity);
        this.orderId.set(orderId);
        this.shippingStatus.set(shippingStatus);
//        this.transactionTime.setDateFormatSymbols();//Correct?????
    }

    public int getProductId() {
        return productId.get();
    }

    public SimpleIntegerProperty productIdProperty() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId.set(productId);
    }

    public int getCustomerId() {
        return customerId.get();
    }

    public SimpleIntegerProperty customerIdProperty() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId.set(customerId);
    }

    public int getQuantity() {
        return quantity.get();
    }

    public SimpleIntegerProperty quantityProperty() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity.set(quantity);
    }

    public int getOrderId() {
        return orderId.get();
    }

    public SimpleIntegerProperty orderIdProperty() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId.set(orderId);
    }

    public String getShippingStatus() {
        return shippingStatus.get();
    }

    public SimpleStringProperty shippingStatusProperty() {
        return shippingStatus;
    }

    public void setShippingStatus(String shippingStatus) {
        this.shippingStatus.set(shippingStatus);
    }

}
