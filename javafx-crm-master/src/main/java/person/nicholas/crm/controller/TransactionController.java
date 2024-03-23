package person.nicholas.crm.controller;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.TextField;
import lombok.Getter;
import person.nicholas.crm.dao.ProductDao;
import person.nicholas.crm.dao.TransactionDao;
import person.nicholas.crm.entity.Order;
import person.nicholas.crm.entity.Product;
import person.nicholas.crm.entity.TransactionRecord;

public class TransactionController {
    @FXML
    private ComboBox<Product> productComboBox;
    @FXML
    private TextField addQuantity;
    @FXML
    private TextField addShippingStatus;
    @FXML
    private Button addTransactionOkButton;

    private final TransactionDao transactionDao = new TransactionDao();
    private final ProductDao productDao = new ProductDao();
    @Getter
    private Order order;


    @FXML
    private void initialize() {
        // Initialize the table columns
        productComboBox.setItems(FXCollections.observableArrayList(productDao.getProductList()));

        productComboBox.setCellFactory(param -> new javafx.scene.control.ListCell<>() {
            @Override
            protected void updateItem(Product item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                } else {
                    setText(item.getProductName()+"("+item.getVendorName()+")");
                }
            }
        });
        productComboBox.setButtonCell(new ListCell<>() {
            @Override
            protected void updateItem(Product item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                } else {
                    setText(item.getProductName()+"("+item.getVendorName()+")");
                }
            }
        });
    }

    @FXML
    protected void onOkButtonClick() {
        //Get values
        int productId = productComboBox.getSelectionModel().getSelectedItem().getProductId();
        int quantity = Integer.parseInt(addQuantity.getText());
        Order order = new Order();
        order.setProductId(new SimpleIntegerProperty(productId));
        order.setQty(new SimpleIntegerProperty(quantity));
        order.setTotal(new SimpleDoubleProperty(quantity * productComboBox.getSelectionModel().getSelectedItem().getListedPrice()));
        order.setPrice(new SimpleDoubleProperty(productComboBox.getSelectionModel().getSelectedItem().getListedPrice()));
        order.setVendorName(new SimpleStringProperty(productComboBox.getSelectionModel().getSelectedItem().getVendorName()));
        order.setProductName(new SimpleStringProperty(productComboBox.getSelectionModel().getSelectedItem().getProductName()));
        this.order = order;
        //Close dialog
        addTransactionOkButton.getScene().getWindow().hide();
    }
}
