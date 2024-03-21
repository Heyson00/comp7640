package person.nicholas.crm.dao;

import person.nicholas.crm.config.DatabaseConfig;
import person.nicholas.crm.entity.Product;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDao {


    public List<Product> getVendorList() {
        String sql = "SELECT pd.*, GROUP_CONCAT(DISTINCT t.tag_name ORDER BY t.tag_name SEPARATOR ',') as tags\n" +
                "FROM product pd\n" +
                "         LEFT JOIN product_tag pt ON pd.product_id = pt.product_id\n" +
                "         LEFT JOIN tag t ON pt.tag_id = t.tag_id\n" +
                "GROUP BY pd.product_id";
        PreparedStatement statement;
        try {
            statement = DatabaseConfig.getInstance().getConnection().prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            List<Product> products = new ArrayList<>();
            while (resultSet.next()) {
                Product product = new Product();
                product.setProductId(resultSet.getInt("product_id"));
                product.setProductName(resultSet.getString("product_name"));
                product.setListedPrice(resultSet.getInt("listed_price"));
                product.setVendorId(resultSet.getInt("vendor_id"));
                product.setTags(resultSet.getString("tags"));

                products.add(product);
            }

            return products;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Product> getProductListByProductName(String productName) {
        return null;
    }


}
