package person.nicholas.crm.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import person.nicholas.crm.dao.ProductDao;
import person.nicholas.crm.entity.Product;

public class ProductDiscoveryTabController {
    @FXML
    private TableView<Product> productTableView;
    @FXML
    private TableColumn<Product, Integer> productId;
    @FXML
    private TableColumn<Product, String> productName;
    @FXML
    private TableColumn<Product, Integer> listedPrice;
    @FXML
    private TableColumn<Product, Integer> vendorId;
    @FXML
    private TableColumn<Product, String> tags;

    private final ProductDao productDao = new ProductDao();

    @FXML
    private void initialize() {
        productId.setCellValueFactory(cellData -> cellData.getValue().productIdProperty().asObject());
        productName.setCellValueFactory(cellData -> cellData.getValue().productNameProperty());
        listedPrice.setCellValueFactory(cellData -> cellData.getValue().listedPriceProperty().asObject());
        vendorId.setCellValueFactory(cellData -> cellData.getValue().vendorIdProperty().asObject());
        tags.setCellValueFactory(cellData -> cellData.getValue().tagsProperty());

        ObservableList<Product> data = FXCollections.observableArrayList(productDao.getVendorList());
        productTableView.setItems(data);
    }


}
