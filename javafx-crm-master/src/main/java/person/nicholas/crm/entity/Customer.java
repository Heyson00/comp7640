package person.nicholas.crm.entity;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Customer {
    private SimpleIntegerProperty customerId = new SimpleIntegerProperty(this, "customerId");
    private SimpleStringProperty contactNum = new SimpleStringProperty(this, "contactNum");
    private SimpleStringProperty customerName = new SimpleStringProperty(this, "customerName");
//    private final SimpleStringProperty shippingStatus = new SimpleStringProperty(this, "shippingStatus");

}


