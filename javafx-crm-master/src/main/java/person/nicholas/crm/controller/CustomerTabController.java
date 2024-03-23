package person.nicholas.crm.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import person.nicholas.crm.dao.CustomerDao;
import person.nicholas.crm.entity.Customer;

public class CustomerTabController {
    @FXML
    TableView<Customer> customerTableView;
    @FXML
    TableColumn<Customer, Integer> customerId;
    @FXML
    TableColumn<Customer, String> contactNum;
    @FXML
    TableColumn<Customer, String> customerName;

    @FXML
    Button addCustomer;

    @FXML
    Button deleteCustomer;

    @FXML
    Button searchCustomer;

    @FXML
    Button updateCustomer;

    private final CustomerDao customerDao = new CustomerDao();

    @FXML
    private void initialize() {
        customerId.setCellValueFactory(cellData -> cellData.getValue().getCustomerId().asObject());
        contactNum.setCellValueFactory(cellData -> cellData.getValue().getContactNum());
        customerName.setCellValueFactory(cellData -> cellData.getValue().getCustomerName());

        // Populate the table
        ObservableList<Customer> data = FXCollections.observableArrayList(customerDao.getCustomerList());
        customerTableView.setItems(data);

    }

    @FXML
    private void addCustomer() {

    }

    @FXML
    private void deleteCustomer() {

    }

    @FXML
    private void searchCustomer() {

    }

    @FXML
    private void updateCustomer() {

    }



}
