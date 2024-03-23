package person.nicholas.crm.entity;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import lombok.Data;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.DateTimeException;
import java.util.Date;
import java.util.List;

@Data
public class TransactionRecord {
    private SimpleIntegerProperty productId = new SimpleIntegerProperty();
    private SimpleIntegerProperty customerId = new SimpleIntegerProperty();
    private SimpleIntegerProperty quantity = new SimpleIntegerProperty();
    private SimpleIntegerProperty orderId = new SimpleIntegerProperty();
    private SimpleStringProperty shippingStatus = new SimpleStringProperty();
    private SimpleDateFormat transactionTime = new SimpleDateFormat();

}
