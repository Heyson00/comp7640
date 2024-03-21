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
import person.nicholas.crm.dao.VendorDao;
import person.nicholas.crm.entity.Vendor;

import java.io.IOException;

public class VendorTabController {
    @FXML
    private TableView<Vendor> vendorView;
    @FXML
    private TableColumn<Vendor, String> businessName;
    @FXML
    private TableColumn<Vendor, String> geographicalPresence;
    @FXML
    private TableColumn<Vendor, Integer> feedbackScore;
    @FXML
    private TableColumn<Vendor, String> vendorId;

    @FXML
    private Button addVendor;


    private final VendorDao vendorDao = new VendorDao();

    @FXML
    protected void onAddButtonClick() throws IOException {
        //弹窗vendor-add
        FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("vendor_add_view.fxml"));
        Parent popupContent = loader.load();

        // Create the dialog Stage
        Stage dialogStage = new Stage();
        dialogStage.setTitle("Add Vendor");
        dialogStage.initOwner(addVendor.getScene().getWindow());
        dialogStage.setScene(new Scene(popupContent));

        dialogStage.showAndWait();

        // Populate the table
        ObservableList<Vendor> data = FXCollections.observableArrayList(vendorDao.getVendorList());
        vendorView.setItems(data);
    }

    @FXML
    private void initialize() {
        // Initialize the table columns
        businessName.setCellValueFactory(cellData -> cellData.getValue().businessNameProperty());
        geographicalPresence.setCellValueFactory(cellData -> cellData.getValue().geographicalPresenceProperty());
        feedbackScore.setCellValueFactory(cellData -> cellData.getValue().feedbackScoreProperty().asObject());
        vendorId.setCellValueFactory(cellData -> cellData.getValue().vendorIdProperty().asObject().asString());

        // Populate the table
        ObservableList<Vendor> data = FXCollections.observableArrayList(vendorDao.getVendorList());
        vendorView.setItems(data);
    }
}
