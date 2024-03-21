package person.nicholas.crm.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import person.nicholas.crm.dao.VendorDAO;


public class VendorController {
    @FXML
    private TextField businessName;
    @FXML
    private TextArea geographicalPresence;
    @FXML
    private Button addVendor;

    @FXML
    protected void onAddButtonClick() {
        //Save vendor
        System.out.println(businessName.getText());
        System.out.println(geographicalPresence.getText());
        //Close dialog
        addVendor.getScene().getWindow().hide();
    }
}
