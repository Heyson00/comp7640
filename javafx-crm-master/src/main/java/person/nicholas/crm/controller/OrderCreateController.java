package person.nicholas.crm.controller;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import person.nicholas.crm.HelloApplication;
import person.nicholas.crm.dao.CustomerDao;
import person.nicholas.crm.dao.TransactionDao;
import person.nicholas.crm.entity.Customer;
import person.nicholas.crm.entity.Order;
import person.nicholas.crm.entity.TransactionRecord;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class OrderCreateController {
    @FXML
    private ComboBox<Customer> customerComboBox;
    @FXML
    private Button purchaseProductButton;
    @FXML
    private Button deleteProductButton;
    @FXML
    private Button confirmOrderButton;
    @FXML
    private TableView<Order> tableView;
    @FXML
    private TableColumn<Order, Integer> productId;
    @FXML
    private TableColumn<Order, String> productName;
    @FXML
    private TableColumn<Order, String> vendorName;
    @FXML
    private TableColumn<Order, Integer> qty;
    @FXML
    private TableColumn<Order, Double> price;
    @FXML
    private TableColumn<Order, Double> total;

    private final TransactionDao transactionDao = new TransactionDao();
    private final CustomerDao customerDao = new CustomerDao();


    @FXML
    private void  initialize() {
        productId.setCellValueFactory(cellData -> cellData.getValue().getProductId().asObject());
        productName.setCellValueFactory(cellData -> cellData.getValue().getProductName());
        vendorName.setCellValueFactory(cellData -> cellData.getValue().getVendorName());
        qty.setCellValueFactory(cellData -> cellData.getValue().getQty().asObject());
        price.setCellValueFactory(cellData -> cellData.getValue().getPrice().asObject());
        total.setCellValueFactory(cellData -> cellData.getValue().getTotal().asObject());

        customerComboBox.setItems(FXCollections.observableArrayList(customerDao.getCustomerList()));
        customerComboBox.setCellFactory(param -> new ListCell<>() {
            @Override
            protected void updateItem(Customer item, boolean empty) {
                super.updateItem(item, empty);
                if (item == null || empty) {
                    setText(null);
                } else {
                    setText(item.getCustomerName().get());
                }
            }

        });
        customerComboBox.setButtonCell(new ListCell<>() {
            @Override
            protected void updateItem(Customer item, boolean empty) {
                super.updateItem(item, empty);
                if (item == null || empty) {
                    setText(null);
                } else {
                    setText(item.getCustomerName().get());
                }
            }
        });

    }

    @FXML
    protected void onPurchaseProductButtonClick() throws IOException {
        //弹窗product-purchase
        FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("order_product_add_view.fxml"));
        Parent popupContent = loader.load();

        TransactionController controller = loader.getController();

        // Create the dialog Stage
        Stage dialogStage = new Stage();
        dialogStage.setTitle("Add Product");
        dialogStage.initOwner(purchaseProductButton.getScene().getWindow());
        dialogStage.setScene(new Scene(popupContent));
        dialogStage.setOnHidden(event -> {
            Order order = controller.getOrder();
            if (order != null) {
                tableView.getItems().add(order);
            }
        });
        dialogStage.showAndWait();

    }

    @FXML
    protected void onDeleteProductButtonClick() {

        Order order = tableView.getSelectionModel().getSelectedItem();
        if (order != null) {
            tableView.getItems().remove(order);
        }
    }

    @FXML
    protected void onConfirmOrderButtonClick() {
        //Before Transaction Create Check
        //Customer not selected
        if (customerComboBox.getSelectionModel().isEmpty()) {
            //弹窗
            DialogPane dialogPane = new DialogPane();
            dialogPane.getButtonTypes().add(ButtonType.OK);
            dialogPane.setContentText("Please select customer.");
            Button okButton = (Button) dialogPane.lookupButton(ButtonType.OK);

            // Create the dialog Stage
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Confirm Order");
            dialogStage.initOwner(confirmOrderButton.getScene().getWindow());
            dialogStage.setScene(new Scene(dialogPane));
            okButton.setOnAction(e -> {
                dialogStage.close();
            });
            dialogStage.showAndWait();

            return;
        }
        //Order List is empty
        if (tableView.getItems().isEmpty()) {
            //弹窗
            DialogPane dialogPane = new DialogPane();
            dialogPane.getButtonTypes().add(ButtonType.OK);
            dialogPane.setContentText("Please add product.");
            Button okButton = (Button) dialogPane.lookupButton(ButtonType.OK);

            // Create the dialog Stage
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Confirm Order");
            dialogStage.initOwner(confirmOrderButton.getScene().getWindow());
            dialogStage.setScene(new Scene(dialogPane));

            okButton.setOnAction(e -> {
                dialogStage.close();
            });

            dialogStage.showAndWait();
            return;
        }
        //Dialog Confirm
        //弹窗
        DialogPane dialogPane = new DialogPane();
        //Show Order Create Confirm
        VBox vBox = new VBox();
        vBox.getChildren().add(new Label("Please confirm your product list."));
        vBox.getChildren().add(new Label(""));
        vBox.getChildren().add(new Label("Customer: " + customerComboBox.getSelectionModel().getSelectedItem().getCustomerName().get()));
        double total = 0;
        tableView.getItems().forEach(order -> {
            Label productInfo = new Label("Product: " + order.getProductName().get() + ", Quantity: " + order.getQty().get());
            vBox.getChildren().add(productInfo);
        });

        for (Order order : tableView.getItems()) {
            total += order.getTotal().get();
        }

        vBox.getChildren().add(new Label("Total: " + total));

        dialogPane.setGraphic(vBox);
        dialogPane.getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);


        // Create the dialog Stage
        Stage dialogStage = new Stage();
        dialogStage.setTitle("Confirm Order");
        dialogStage.initOwner(confirmOrderButton.getScene().getWindow());
        dialogStage.setScene(new Scene(dialogPane));
        Button okButton = (Button) dialogPane.lookupButton(ButtonType.OK);
        okButton.setOnAction(e -> {
            //Transaction Create
            List<TransactionRecord> transactionRecords = new ArrayList<>();
            //Generate OrderId
            String orderId = generateOrderId();
            tableView.getItems().forEach(order -> {
                TransactionRecord transactionRecord = new TransactionRecord();
                transactionRecord.setProductId(new SimpleIntegerProperty(order.getProductId().get()));
                transactionRecord.setCustomerId(customerComboBox.getSelectionModel().getSelectedItem().getCustomerId());
                transactionRecord.setQuantity(new SimpleIntegerProperty(order.getQty().get()));
                transactionRecord.setOrderId(new SimpleStringProperty(orderId));
                transactionRecord.setShippingStatus(new SimpleStringProperty("Not Shipped"));
                transactionRecord.setTransactionTime(new java.sql.Date(System.currentTimeMillis()));
                transactionRecords.add(transactionRecord);
            });

            transactionDao.batchAddTransaction(transactionRecords);

            dialogStage.close();

            //Clear Table
            tableView.getItems().clear();
        });

        Button cancelButton = (Button) dialogPane.lookupButton(ButtonType.CANCEL);
        cancelButton.setOnAction(e -> {
            dialogStage.close();
        });

        dialogStage.showAndWait();
    }

    private String generateOrderId() {
        Date date = new Date();
        DateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        return dateFormat.format(date);
    }

}
