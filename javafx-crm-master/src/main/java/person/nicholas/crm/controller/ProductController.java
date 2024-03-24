package person.nicholas.crm.controller;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.TextField;
import person.nicholas.crm.dao.ProductDao;
import person.nicholas.crm.dao.VendorDao;
import person.nicholas.crm.entity.Product;
import person.nicholas.crm.entity.Vendor;

public class ProductController {
    @FXML
    private TextField addProductName;
    @FXML
    private TextField addListedPrice;
    @FXML
    private ComboBox<Vendor> vendorNameCombo;
    @FXML
    private Button addProductOkButton;
    @FXML
    private TextField addProductTag;

    private final ProductDao productDao = new ProductDao();
    private final VendorDao vendorDao = new VendorDao();

    //初始化数据
    @FXML
    protected void initialize() {
        vendorNameCombo.setItems(FXCollections.observableArrayList(vendorDao.getVendorList()));
        vendorNameCombo.setButtonCell(new ListCell<>() {
            @Override
            protected void updateItem(Vendor item, boolean empty) {
                super.updateItem(item, empty);
                if (item == null || empty) {
                    setText(null);
                } else {
                    setText(item.getBusinessName());
                }
            }
        });

        vendorNameCombo.setCellFactory(param -> new ListCell<>() {
            @Override
            protected void updateItem(Vendor item, boolean empty) {
                super.updateItem(item, empty);
                if (item == null || empty) {
                    setText(null);
                } else {
                    setText(item.getBusinessName());
                }
            }
        });
    }

    @FXML
    protected void onOkButtonClick() {
        Product product = new Product();
        product.setProductName(addProductName.getText());
        product.setListedPrice(Integer.parseInt(addListedPrice.getText()));
        product.setVendorId(vendorNameCombo.getSelectionModel().getSelectedItem().getVendorId());
        product.setTags(addProductTag.getText());
        productDao.addProduct(product);
        addProductOkButton.getScene().getWindow().hide();
    }
}
