package person.nicholas.crm.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import person.nicholas.crm.dao.TransactionDao;
import person.nicholas.crm.entity.TransactionRecord;
import person.nicholas.crm.entity.Vendor;

public class TransactionController {
    @FXML
    private TextField addProductId;
    @FXML
    private TextField addCustomerId;
    @FXML
    private TextField addOrderId;
    @FXML
    private TextField addQuantity;
    @FXML
    private TextField addShippingStatus;
//    @FXML
//    private TextField addTransactionTime;
    @FXML
    private Button addTransactionOkButton;

    private final TransactionDao transactionDao = new TransactionDao();

    @FXML
    protected void onOkButtonClick() {
        //Close dialog
        TransactionRecord transactionRecord = new TransactionRecord();
        transactionRecord.setProductId(Integer.parseInt(addProductId.getText()));
        transactionRecord.setCustomerId(Integer.parseInt(addCustomerId.getText()));
        transactionRecord.setOrderId(Integer.parseInt(addOrderId.getText()));
        transactionRecord.setQuantity(Integer.parseInt(addQuantity.getText()));
        transactionRecord.setShippingStatus(addShippingStatus.getText());
        transactionDao.addTransaction(transactionRecord);
        addTransactionOkButton.getScene().getWindow().hide();
    }
}
