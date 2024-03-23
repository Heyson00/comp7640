package person.nicholas.crm.controller;

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

public class ProductPurchaseTabController {
    @FXML
    private TableView<TransactionRecord> transactionRecordTableView;
    @FXML
    private TableColumn<TransactionRecord, Integer> orderId;
    @FXML
    private TableColumn<TransactionRecord, Integer> productId;
    @FXML
    private TableColumn<TransactionRecord, Integer> customerId;
    @FXML
    private TableColumn<TransactionRecord, Integer> quantity;
    @FXML
    private TableColumn<TransactionRecord, String> shippingStatus;
    @FXML
    private Button addTransaction;
//    private TableColumn<TransactionRecord, DateFormat> transactionTime;

    private final TransactionDao transactionDao = new TransactionDao();

    @FXML
    private void initialize() {
        orderId.setCellValueFactory(cellData -> cellData.getValue().getOrderId().asObject());
        productId.setCellValueFactory(cellData -> cellData.getValue().getProductId().asObject());
        customerId.setCellValueFactory(cellData -> cellData.getValue().getCustomerId().asObject());
        quantity.setCellValueFactory(cellData -> cellData.getValue().getQuantity().asObject());
        shippingStatus.setCellValueFactory(cellData -> cellData.getValue().getShippingStatus());
//      transactionTime.setCellValueFactory(cellData -> cellData.getValue());

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
