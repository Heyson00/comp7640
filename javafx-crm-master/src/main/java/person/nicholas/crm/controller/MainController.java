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
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import person.nicholas.crm.HelloApplication;
import person.nicholas.crm.entity.Vendor;

import java.io.IOException;
import java.util.List;

/**
 * Vendor Controller
 * Manages the Add, Delete, Update, and Search buttons
 */
public class MainController {

    @FXML
    private Button addVendor;
    @FXML
    private TableView<Vendor> vendorView;
    @FXML
    private TextField addVendorField;
    @FXML
    private TableColumn<Vendor, String> businessName;
    @FXML
    private TableColumn<Vendor, String> geographicalPresence;
    @FXML
    private TableColumn<Vendor, Integer> feedbackScore;
    @FXML
    private TableColumn<Vendor, String> vendorId;
    private ObservableList<Vendor> vendorData = FXCollections.observableArrayList();

    @FXML
    private void initialize() {
        // Initialize the table columns
        businessName.setCellValueFactory(cellData -> cellData.getValue().businessNameProperty());
        geographicalPresence.setCellValueFactory(cellData -> cellData.getValue().geographicalPresenceProperty());
        feedbackScore.setCellValueFactory(cellData -> cellData.getValue().feedbackScoreProperty().asObject());
        vendorId.setCellValueFactory(cellData -> cellData.getValue().vendorIdProperty().asObject().asString());

        vendorView.setItems(vendorData);
    }

    @FXML
    protected void onAddButtonClick() throws IOException {
        //弹窗vendor-add
        FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("vendor-add.fxml"));
        Parent popupContent = loader.load();

        // Create the dialog Stage
        Stage dialogStage = new Stage();
        dialogStage.setTitle("Add Vendor");
        dialogStage.initOwner(addVendor.getScene().getWindow());
        dialogStage.setScene(new Scene(popupContent));

        dialogStage.showAndWait();


//        ObservableList<Vendor> data = vendorView.getItems();
//        Vendor vendor = new Vendor(data.size() +1,addVendorField.getText(), 0, " ");
//        data.add(vendor);
//        addVendorField.setText("");
//        vendorView.setItems(data);
    }
}