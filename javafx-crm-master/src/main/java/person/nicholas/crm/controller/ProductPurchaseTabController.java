package person.nicholas.crm.controller;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import person.nicholas.crm.HelloApplication;
import person.nicholas.crm.dao.TransactionDao;
import person.nicholas.crm.entity.TransactionRecord;

import java.io.IOException;
import java.sql.Date;
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
    private Button cancelButton;
    @FXML
    private Button deleteButton;
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
            Date transactionTime = cellData.getValue().getTransactionTime();
            if (transactionTime != null) {
                DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                // 确保格式化操作不会被多线程共享的DateFormat实例影响
                String formattedDate = df.format(transactionTime);
                return new SimpleStringProperty(formattedDate);
            } else {
                return new SimpleStringProperty("");
            }
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

    @FXML
    protected void onCancelButtonClick() {
        if (transactionRecordTableView.getSelectionModel().isEmpty()) {
            DialogPane dialogPane = new DialogPane();
            dialogPane.setContentText("Please select a record.");
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Cancel");
            dialogStage.initOwner(cancelButton.getScene().getWindow());
            dialogStage.setScene(new Scene(dialogPane));
            dialogStage.showAndWait();
            return;
        }
        String orderId = transactionRecordTableView.getSelectionModel().getSelectedItem().getOrderId().get();
        int transactionId = transactionRecordTableView.getSelectionModel().getSelectedItem().getTransactionId();
        DialogPane dialogPane = new DialogPane();
        Label warningText = new Label("Are you sure you want to cancel?");
        Button cancelProductButton = new Button("Cancel product");
        Button cancelOrderButton = new Button("Cancel order");
        VBox vBox = new VBox(warningText, cancelProductButton, cancelOrderButton);
        vBox.setAlignment(Pos.CENTER);
        dialogPane.setContent(vBox);

        Stage dialogStage = new Stage();
        dialogStage.setTitle("Cancel");
        dialogStage.initOwner(cancelButton.getScene().getWindow());
        dialogStage.setScene(new Scene(dialogPane));


        cancelProductButton.setOnAction(e -> {
            transactionDao.cancelTransaction(transactionId);
            transactionRecordTableView.setItems(FXCollections.observableArrayList(transactionDao.getTransactionList()));
            dialogStage.close();
        });

        cancelOrderButton.setOnAction(e -> {
            transactionDao.cancelOrder(orderId);
            transactionRecordTableView.setItems(FXCollections.observableArrayList(transactionDao.getTransactionList()));
            dialogStage.close();
        });

        dialogStage.showAndWait();
    }

    @FXML
    protected void onDeleteButtonClick() {
        if (transactionRecordTableView.getSelectionModel().isEmpty()) {
            DialogPane dialogPane = new DialogPane();
            dialogPane.setContentText("Please select a record.");
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Delete");
            dialogStage.initOwner(deleteButton.getScene().getWindow());
            dialogStage.setScene(new Scene(dialogPane));
            dialogStage.showAndWait();
            return;
        }
        DialogPane dialogPane = new DialogPane();
        Label warningLabel = new Label("Warning: This action will delete the order and all related transactions.");
        Label warningLabel2 = new Label("Are you sure you want to delete?");
        Button okButton = new Button("OK");
        Button cancelButton = new Button("Cancel");
        VBox vBox = new VBox(warningLabel, warningLabel2, okButton, cancelButton);
        vBox.setAlignment(Pos.CENTER);
        vBox.setSpacing(10);
        dialogPane.setContent(vBox);

        Stage dialogStage = new Stage();
        dialogStage.setTitle("Delete");
        dialogStage.initOwner(deleteButton.getScene().getWindow());
        dialogStage.setScene(new Scene(dialogPane));
        cancelButton.setOnAction(e -> {
            dialogStage.close();
        });

        okButton.setOnAction(e -> {
            transactionDao.deleteOrder(transactionRecordTableView.getSelectionModel().getSelectedItem().getOrderId().get());
            transactionRecordTableView.setItems(FXCollections.observableArrayList(transactionDao.getTransactionList()));
            dialogStage.close();
        });
        dialogStage.showAndWait();
    }
}
