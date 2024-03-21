package person.nicholas.crm.entity;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Customer {

//    private int customerId;
//    private String name;
//    private String contactNumber;
//    private String shippingAddress;
    private final SimpleIntegerProperty customerId = new SimpleIntegerProperty(this, "customerId");
    private final SimpleIntegerProperty contactNum = new SimpleIntegerProperty(this, "contactNum");
    private final SimpleStringProperty shippingStatus = new SimpleStringProperty(this, "shippingStatus");

    public Customer() {
    }

    public Customer(int customerId, int contactNum, String shippingStatus) {
        this.customerId.set(customerId);
        this.contactNum.set(contactNum);
        this.shippingStatus.set(shippingStatus);
    }
    public int getCustomerId() { return customerId.get(); }
    public void setCustomerId (int customerId) { this.customerId.set(customerId); }
    public SimpleIntegerProperty customerIdProperty() { return customerId; }
    public int getContactNum() { return contactNum.get(); }
    public void setContactNum(int contactNum) { this.contactNum.set(contactNum); }
    public SimpleIntegerProperty contactNumProperty() { return contactNum; }
    public String getShippingStatus() { return shippingStatus.get(); }
    public void setShippingStatus(String shippingStatus) { this.shippingStatus.set(shippingStatus); }
    public SimpleStringProperty shippingStatusProperty() { return shippingStatus; }


}


