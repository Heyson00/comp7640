package person.nicholas.crm.controller;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.TextField;
import javafx.util.Pair;
import person.nicholas.crm.dao.ProductDao;
import person.nicholas.crm.dao.VendorDao;
import person.nicholas.crm.entity.Product;
import person.nicholas.crm.entity.Vendor;

import java.util.stream.Collectors;

public class ProductController {
    @FXML
    private TextField addProductName;
    @FXML
    private TextField addListedPrice;
    @FXML
    private TextField addVendorId;
    @FXML
    private ComboBox<Pair<Integer,String>> vendorNameCombo = new ComboBox<>();
    @FXML
    private Button addProductOkButton;
    @FXML
    private TextField addProductTag;

    private final ProductDao productDao = new ProductDao();
    private final VendorDao vendorDao = new VendorDao();

    //初始化数据
    @FXML
    protected void initialize() {
        vendorNameCombo.getItems().addAll(vendorDao.getVendorList().stream().map(vendor -> new Pair<>(vendor.getVendorId(), vendor.getBusinessName())).collect(Collectors.toList()));
        vendorNameCombo.setCellFactory(param -> new javafx.scene.control.ListCell<>() {
            @Override
            protected void updateItem(Pair<Integer, String> item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null || item.getKey() == null) {
                    setText(null);
                } else {
                    setText(item.getValue());
                }
            }
        });

        vendorNameCombo.setButtonCell(new ListCell<>() {
            @Override
            protected void updateItem(Pair<Integer, String> item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null || item.getKey() == null) {
                    setText(null);
                } else {
                    setText(item.getValue());
                }
            }
        });

        vendorNameCombo.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal != null) {
                System.out.println("选中的键: " + newVal.getKey());
                System.out.println("选中的值: " + newVal.getValue());
            }
        });
    }

    @FXML
    protected void onOkButtonClick() {
        Product product = new Product();
        product.setProductName(addProductName.getText());
        product.setListedPrice(Integer.parseInt(addListedPrice.getText()));
        product.setVendorId(vendorNameCombo.getSelectionModel().getSelectedItem().getKey());
        productDao.addProduct(product);
        addProductOkButton.getScene().getWindow().hide();
    }
}
