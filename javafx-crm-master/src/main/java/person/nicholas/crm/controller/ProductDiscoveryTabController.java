package person.nicholas.crm.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import person.nicholas.crm.dao.ProductDao;
import person.nicholas.crm.entity.Product;

public class ProductDiscoveryTabController {
    @FXML
    private TextField searchProductField;
    @FXML
    private TableView<Product> productTableView;
    @FXML
    private TableColumn<Product, Integer> productId;
    @FXML
    private TableColumn<Product, String> productName;
    @FXML
    private TableColumn<Product, Integer> listedPrice;
    @FXML
    private TableColumn<Product, String> vendorName;
    @FXML
    private TableColumn<Product, String> tags;
    @FXML
    private Button searchButton;

    private final ProductDao productDao = new ProductDao();

    @FXML
    private void initialize() {
        //Banding data
        productId.setCellValueFactory(cellData -> cellData.getValue().productIdProperty().asObject());
        productName.setCellValueFactory(cellData -> cellData.getValue().productNameProperty());
        listedPrice.setCellValueFactory(cellData -> cellData.getValue().listedPriceProperty().asObject());
        vendorName.setCellValueFactory(cellData -> cellData.getValue().vendorNameProperty());
        tags.setCellValueFactory(cellData -> cellData.getValue().tagsProperty());

        ObservableList<Product> data = FXCollections.observableArrayList(productDao.getProductList());
        productTableView.setItems(data);
    }

    @FXML
    private void onSearchButtonClick() {
        String searchText = searchProductField.getText();
        ObservableList<Product> data = FXCollections.observableArrayList(productDao.getProductListByProductName(searchText));
        productTableView.setItems(data);
    }




}
