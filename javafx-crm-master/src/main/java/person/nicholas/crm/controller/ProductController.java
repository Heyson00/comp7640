package person.nicholas.crm.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import person.nicholas.crm.dao.ProductDao;
import person.nicholas.crm.entity.Product;

public class ProductController {
    @FXML
    private TextField addProductName;
    @FXML
    private TextField addListedPrice;
    @FXML
    private TextField addVendorId;
    @FXML
    private Button addProductOkButton;
    @FXML
    private TextField addProductTag;

    private ProductDao productDao = new ProductDao();

    @FXML
    protected void onOkButtonClick() {
        Product product = new Product();
        product.setProductName(addProductName.getText());
        product.setListedPrice(Integer.parseInt(addListedPrice.getText()));
        product.setVendorId(Integer.parseInt(addVendorId.getText()));
        productDao.addProduct(product);
        addProductOkButton.getScene().getWindow().hide();
    }
}
