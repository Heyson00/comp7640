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
import javafx.stage.Stage;
import person.nicholas.crm.HelloApplication;
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
