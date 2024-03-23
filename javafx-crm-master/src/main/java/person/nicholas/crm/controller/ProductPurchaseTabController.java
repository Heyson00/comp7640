package person.nicholas.crm.controller;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import person.nicholas.crm.HelloApplication;
import person.nicholas.crm.dao.TransactionDao;
import person.nicholas.crm.entity.TransactionRecord;
import person.nicholas.crm.entity.Vendor;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class ProductPurchaseTabController {
    @FXML
    private TableView<TransactionRecord> transactionRecordTableView;
    @FXML
    private TableColumn<TransactionRecord, String> orderId;
    @FXML
    private TableColumn<TransactionRecord, String> productName;
    @FXML
    private TableColumn<TransactionRecord, String> customerName;
    @FXML
    private TableColumn<TransactionRecord, Integer> quantity;
    @FXML
    private TableColumn<TransactionRecord, String> shippingStatus;

    @FXML
    private Button addTransaction;
    @FXML
    private TableColumn<TransactionRecord, String> transactionTime;

    private final TransactionDao transactionDao = new TransactionDao();

    @FXML
    private void initialize() {
        orderId.setCellValueFactory(cellData -> cellData.getValue().getOrderId());
        productName.setCellValueFactory(cellData -> cellData.getValue().getProductName());
        customerName.setCellValueFactory(cellData -> cellData.getValue().getCustomerName());
        quantity.setCellValueFactory(cellData -> cellData.getValue().getQuantity().asObject());
        shippingStatus.setCellValueFactory(cellData -> cellData.getValue().getShippingStatus());
        transactionTime.setCellValueFactory(cellData -> {
            if (cellData.getValue().getTransactionTime() == null) {
                return new SimpleStringProperty("");
            }
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            return new SimpleStringProperty(df.format(cellData.getValue().getTransactionTime()));
        });
        ObservableList<TransactionRecord> data = FXCollections.observableArrayList(transactionDao.getTransactionList());
        transactionRecordTableView.setItems(data);
    }

    @FXML
    protected void onPurchaseButtonClick() throws IOException {
        //弹窗transaction-add
        FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("order_create_view.fxml"));
        Parent popupContent = loader.load();

        // Create the dialog Stage
        Stage dialogStage = new Stage();
        dialogStage.setTitle("Add Transaction");
        dialogStage.initOwner(addTransaction.getScene().getWindow());
        dialogStage.setScene(new Scene(popupContent));

        dialogStage.showAndWait();

        // Populate the table
        ObservableList<TransactionRecord> data = FXCollections.observableArrayList(transactionDao.getTransactionList());
        transactionRecordTableView.setItems(data);
    }
}
