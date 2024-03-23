package person.nicholas.crm.entity;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import lombok.Data;

import java.text.DateFormat;
import java.time.DateTimeException;
import java.sql.Date;
import java.util.List;

@Data
public class TransactionRecord {
    private SimpleIntegerProperty productId;
    private SimpleIntegerProperty customerId;
    private SimpleIntegerProperty quantity;
    private SimpleStringProperty orderId;
    private SimpleStringProperty shippingStatus;
    private Date transactionTime;

    private SimpleStringProperty productName;
    private SimpleStringProperty customerName;

}
