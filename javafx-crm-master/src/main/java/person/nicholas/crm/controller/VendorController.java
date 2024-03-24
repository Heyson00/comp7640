package person.nicholas.crm.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import person.nicholas.crm.dao.VendorDao;
import person.nicholas.crm.entity.Vendor;

import java.io.IOException;


public class VendorController {
    @FXML
    private TextField addBusinessName;
    @FXML
    private TextField addGeographicalPresence;
    @FXML
    private TextField addCustomerScore;
    @FXML
    private Button addVendorOkButton;


    private final VendorDao vendorDao = new VendorDao();

    @FXML
    protected void onOkButtonClick() {
        //Close dialog
        Vendor vendor = new Vendor();
        vendor.setBusinessName(addBusinessName.getText());
        vendor.setGeographicalPresence(addGeographicalPresence.getText());
        vendor.setCustomerScore(Integer.parseInt(addCustomerScore.getText()));
        vendorDao.addVendor(vendor);
        addVendorOkButton.getScene().getWindow().hide();
    }


}
