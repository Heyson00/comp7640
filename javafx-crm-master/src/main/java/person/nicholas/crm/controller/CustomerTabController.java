package person.nicholas.crm.controller;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import person.nicholas.crm.dao.CustomerDao;
import person.nicholas.crm.entity.Customer;
import person.nicholas.crm.entity.Vendor;

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
    TextField searchCustomerField;

    @FXML
    Button addCustomer;

    @FXML
    Button deleteCustomer;

    @FXML
    Button searchCustomer;

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
        HBox hBox1 = new HBox();
        Label label = new Label("Customer Name");
        TextField customerNameField = new TextField();
        hBox1.getChildren().addAll(label, customerNameField);

        HBox hBox2 = new HBox();
        Label label2 = new Label("Contact Number");
        TextField contactNumberField = new TextField();
        hBox2.getChildren().addAll(label2, contactNumberField);

        Button button = new Button("Add");

        VBox vBox = new VBox();
        vBox.getChildren().addAll(hBox1, hBox2, button);
        vBox.setAlignment(Pos.CENTER);

        DialogPane dialogPane = new DialogPane();
        dialogPane.setContent(vBox);

        Stage stage = new Stage();
        stage.setTitle("Add Customer");
        stage.setScene(new Scene(dialogPane));
        stage.show();


        button.setOnAction(e -> {
            Customer customer = new Customer();
            customer.setCustomerName(new SimpleStringProperty(customerNameField.getText()));
            customer.setContactNum(new SimpleStringProperty(contactNumberField.getText()));
            customerDao.addCustomer(customer);

            ObservableList<Customer> data = FXCollections.observableArrayList(customerDao.getCustomerList());
            customerTableView.setItems(data);

            stage.close();
        });



    }

    @FXML
    private void deleteCustomer(){
        if (customerTableView.getSelectionModel().isEmpty()) {
            DialogPane dialogPane = new DialogPane();
            dialogPane.setContentText("Please select a record.");
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Delete");
            dialogStage.initOwner(deleteCustomer.getScene().getWindow());
            dialogStage.setScene(new Scene(dialogPane));
            dialogStage.showAndWait();
            return;
        }
        Customer customer = customerTableView.getSelectionModel().getSelectedItem();
        customerDao.deleteCustomer(customer);
        ObservableList<Customer> data = FXCollections.observableArrayList(customerDao.getCustomerList());
        customerTableView.setItems(data);
    }

    @FXML
    private void searchCustomer() {
        String searchText = searchCustomerField.getText();

        if (searchText.isEmpty()) {
            ObservableList<Customer> data = FXCollections.observableArrayList(customerDao.getCustomerList());
            customerTableView.setItems(data);
            return;
        }

        ObservableList<Customer> data = FXCollections.observableArrayList(customerDao.getCustomerByName(searchText));
        customerTableView.setItems(data);
    }


}
