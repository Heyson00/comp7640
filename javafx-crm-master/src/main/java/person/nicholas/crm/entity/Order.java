package person.nicholas.crm.entity;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Order {
    private SimpleIntegerProperty productId;
    private SimpleStringProperty productName;
    private SimpleStringProperty vendorName;
    private SimpleIntegerProperty qty;
    private SimpleDoubleProperty price;
    private SimpleDoubleProperty total;
}
