package person.nicholas.crm.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import person.nicholas.crm.HelloApplication;
import person.nicholas.crm.dao.ProductDao;
import person.nicholas.crm.entity.Product;

import java.io.IOException;

public class ProductCatologTabController {
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
    private Button addProduct;

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
    protected void onAddButtonClick() throws IOException {
        FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("product_add_view.fxml"));
        Parent popupContent = loader.load();

        // Create the dialog Stage
        Stage dialogStage = new Stage();
        dialogStage.setTitle("Add Product");
        dialogStage.initOwner(addProduct.getScene().getWindow());
        dialogStage.setScene(new Scene(popupContent));

        dialogStage.showAndWait();

        // Populate the table
        ObservableList<Product> data = FXCollections.observableArrayList(productDao.getProductList());
        productTableView.setItems(data);
    }
}
