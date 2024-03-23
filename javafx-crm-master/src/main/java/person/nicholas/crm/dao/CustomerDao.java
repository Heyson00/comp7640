package person.nicholas.crm.dao;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import person.nicholas.crm.config.DatabaseConfig;
import person.nicholas.crm.entity.Customer;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerDao {

    public List<Customer> getCustomerList() {
        String sql = "SELECT * FROM customer_profiles";
        PreparedStatement statement;
        try {
            statement = DatabaseConfig.getInstance().getConnection().prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            List<Customer> customers = new ArrayList<>();
            while (resultSet.next()) {
                Customer customer = new Customer();
                customer.setCustomerId(new SimpleIntegerProperty(resultSet.getInt("customer_id")));
                customer.setContactNum(new SimpleStringProperty(resultSet.getString("contact_num")));
                customer.setCustomerName(new SimpleStringProperty(resultSet.getString("customer_name")));
                customers.add(customer);
            }
            return customers;
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void addCustomer(Customer customer) {
        String sql = "INSERT INTO customer_profiles (contact_num, customer_name) VALUES (?, ?)";
        PreparedStatement statement;
        try {
            statement = DatabaseConfig.getInstance().getConnection().prepareStatement(sql);
            statement.setString(1, customer.getContactNum().get());
            statement.setString(2, customer.getContactNum().get());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateCustomer(Customer customer) {
        String sql = "UPDATE customer_profiles SET contact_num = ?, customer_name = ? WHERE customer_id = ?";
        PreparedStatement statement;
        try {
            statement = DatabaseConfig.getInstance().getConnection().prepareStatement(sql);
            statement.setString(1, customer.getContactNum().get());
            statement.setString(2, customer.getContactNum().get());
            statement.setInt(3, customer.getCustomerId().get());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteCustomer(Customer customer) {
        String sql = "DELETE FROM customer_profiles WHERE customer_id = ?";
        PreparedStatement statement;
        try {
            statement = DatabaseConfig.getInstance().getConnection().prepareStatement(sql);
            statement.setInt(1, customer.getCustomerId().get());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
