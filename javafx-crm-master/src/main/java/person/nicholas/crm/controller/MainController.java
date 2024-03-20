package person.nicholas.crm.controller;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import person.nicholas.crm.entity.Vendor;

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

    @FXML
    private void initialize() {
        // Initialize the table columns
        businessName.setCellValueFactory(cellData -> cellData.getValue().businessNameProperty());
        geographicalPresence.setCellValueFactory(cellData -> cellData.getValue().geographicalPresenceProperty());
        feedbackScore.setCellValueFactory(cellData -> cellData.getValue().feedbackScoreProperty().asObject());
        vendorId.setCellValueFactory(cellData -> cellData.getValue().vendorIdProperty().asObject().asString());
    }

    @FXML
    protected void onAddButtonClick() {
        ObservableList<Vendor> data = vendorView.getItems();
        Vendor vendor = new Vendor(data.size() +1,addVendorField.getText(), 0, " ");
        data.add(vendor);
        addVendorField.setText("");
        vendorView.setItems(data);
    }
}