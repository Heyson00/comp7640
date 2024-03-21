package person.nicholas.crm.entity;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

import java.util.List;

public class Product {
//    private int productId;
//    private String name;
//    private double price;
//    private List<String> tags;
    private final SimpleIntegerProperty productId = new SimpleIntegerProperty();
    private final SimpleStringProperty productName = new SimpleStringProperty();
    private final SimpleIntegerProperty listedPrice = new SimpleIntegerProperty();
    private final SimpleIntegerProperty vendorId = new SimpleIntegerProperty();

    public Product() {
    }

    public Product(int productId, String productName, int listedPrice, int vendorId) {
        this.productId.set(productId);
        this.productName.set(productName);
        this.listedPrice.set(listedPrice);
        this.vendorId.set(vendorId);
    }

    public int getProductId() { return productId.get(); }
    public void setProductId(int productId) { this.productId.set(productId); }
    public SimpleIntegerProperty productIdProperty() { return productId; }
    public String getProductName() { return productName.get(); }
    public void setProductName(String productName) { this.productName.set(productName); }
    public SimpleStringProperty productNameProperty() { return productName; }
    public int getListedPrice() { return listedPrice.get(); }
    public void setListedPrice(int listedPrice) { this.listedPrice.set(listedPrice); }
    public SimpleIntegerProperty listedPriceProperty() { return listedPrice; }
    public int getVendorId() { return  vendorId.get(); }
    public void setVendorId(int vendorId) { this.vendorId.set(vendorId); }
    public SimpleIntegerProperty vendorIdProperty() { return vendorId; }



    // Constructor, getters, and setters
}