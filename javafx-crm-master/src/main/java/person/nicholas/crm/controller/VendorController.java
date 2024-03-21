package person.nicholas.crm.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import person.nicholas.crm.dao.VendorDao;
import person.nicholas.crm.entity.Vendor;


public class VendorController {
    @FXML
    private TextField businessName;
    @FXML
    private TextField geographicalPresence;
    @FXML
    private TextField customerScore;
    @FXML
    private Button addVendor;
    private final VendorDao vendorDao = new VendorDao();

    @FXML
    protected void onAddButtonClick() {
        //Close dialog
        Vendor vendor = new Vendor();
        vendor.setBusinessName(businessName.getText());
        vendor.setGeographicalPresence(geographicalPresence.getText());
        vendor.setCustomerScore(Integer.parseInt(customerScore.getText()));
        vendorDao.addVendor(vendor);
        addVendor.getScene().getWindow().hide();
    }

    //Close dialog
    @FXML
    protected void onCancelButtonClick() {
        addVendor.getScene().getWindow().hide();

    }
}
