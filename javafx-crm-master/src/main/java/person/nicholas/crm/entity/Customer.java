package person.nicholas.crm.entity;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class Customer {
    private SimpleIntegerProperty customerId;
    private SimpleStringProperty contactNum;
    private SimpleStringProperty customerName;
}


