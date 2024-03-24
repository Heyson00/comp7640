package person.nicholas.crm.dao;

import person.nicholas.crm.config.DatabaseConfig;
import person.nicholas.crm.entity.Product;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDao {

    private final DatabaseConfig dbConfig;
    public ProductDao() { dbConfig = DatabaseConfig.getInstance(); }

    public List<Product> getProductList() {
        String sql = "SELECT pd.*, GROUP_CONCAT(DISTINCT t.tag_name ORDER BY t.tag_name SEPARATOR ',') as tags, v.business_name\n" +
                "FROM product pd\n" +
                "         LEFT JOIN product_tag pt ON pd.product_id = pt.product_id\n" +
                "         LEFT JOIN tag t ON pt.tag_id = t.tag_id\n" +
                "         left join vendor v on v.vendor_id = pd.vendor_id\n" +
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
                product.setVendorName(resultSet.getString("business_name"));
                products.add(product);
            }

            return products;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Product> getProductListByProductName(String productName) {
        String sql = "SELECT pd.*, GROUP_CONCAT(DISTINCT t.tag_name ORDER BY t.tag_name SEPARATOR ',') as tags, v.business_name\n" +
                "FROM product pd\n" +
                "         LEFT JOIN product_tag pt ON pd.product_id = pt.product_id\n" +
                "         LEFT JOIN tag t ON pt.tag_id = t.tag_id\n" +
                "         left join vendor v on v.vendor_id = pd.vendor_id\n" +
                "WHERE t.tag_name LIKE ? \n" +
                "GROUP BY pd.product_id";
        PreparedStatement statement;
        try {
            statement = DatabaseConfig.getInstance().getConnection().prepareStatement(sql);
            statement.setString(1, "%" + productName + "%");
            ResultSet resultSet = statement.executeQuery();
            List<Product> products = new ArrayList<>();
            while (resultSet.next()) {
                Product product = new Product();
                product.setProductId(resultSet.getInt("product_id"));
                product.setProductName(resultSet.getString("product_name"));
                product.setListedPrice(resultSet.getInt("listed_price"));
                product.setVendorId(resultSet.getInt("vendor_id"));
                product.setTags(resultSet.getString("tags"));
                product.setVendorName(resultSet.getString("business_name"));

                products.add(product);
            }

            return products;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Product> getProductListByVendorName(String productName) {
        String sql = "SELECT pd.*, GROUP_CONCAT(DISTINCT t.tag_name ORDER BY t.tag_name SEPARATOR ',') as tags, v.business_name\n" +
                "FROM product pd\n" +
                "         LEFT JOIN product_tag pt ON pd.product_id = pt.product_id\n" +
                "         LEFT JOIN tag t ON pt.tag_id = t.tag_id\n" +
                "         left join vendor v on v.vendor_id = pd.vendor_id\n" +
                "WHERE v.business_name LIKE ? \n" +
                "GROUP BY pd.product_id";
        PreparedStatement statement;
        try {
            statement = DatabaseConfig.getInstance().getConnection().prepareStatement(sql);
            statement.setString(1, "%" + productName + "%");
            ResultSet resultSet = statement.executeQuery();
            List<Product> products = new ArrayList<>();
            while (resultSet.next()) {
                Product product = new Product();
                product.setProductId(resultSet.getInt("product_id"));
                product.setProductName(resultSet.getString("product_name"));
                product.setListedPrice(resultSet.getInt("listed_price"));
                product.setVendorId(resultSet.getInt("vendor_id"));
                product.setTags(resultSet.getString("tags"));
                product.setVendorName(resultSet.getString("business_name"));

                products.add(product);
            }

            return products;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void addProduct(Product p){
        PreparedStatement pstmtP;
        PreparedStatement pstmtT;
        try{
            String sqlProduct;
            String sqlTag;
            sqlProduct = "INSERT INTO product(product_name, listed_price, vendor_id) VALUES (?, ?, ?)";

            pstmtP = dbConfig.getConnection().prepareStatement(sqlProduct, PreparedStatement.RETURN_GENERATED_KEYS);
            pstmtP.setString(1, p.getProductName());
            pstmtP.setInt(2, p.getListedPrice());
            pstmtP.setInt(3, p.getVendorId());
            pstmtP.executeUpdate();
            ResultSet rs = pstmtP.getGeneratedKeys();
            if(rs.next()){
                p.setProductId(rs.getInt(1));
            }
            
            //set tag
            String[] tags = p.getTags().split(",");
            for(String tag : tags){
                String queryTag = "SELECT tag_id FROM tag WHERE tag_name = ?";
                pstmtT = dbConfig.getConnection().prepareStatement(queryTag);
                pstmtT.setString(1, tag);
                ResultSet resultSet = pstmtT.executeQuery();
                if(resultSet.next()){
                    int tagId = resultSet.getInt("tag_id");
                    sqlTag = "INSERT INTO product_tag(product_id, tag_id) VALUES (?, ?)";
                    pstmtT = dbConfig.getConnection().prepareStatement(sqlTag);
                    pstmtT.setInt(1, p.getProductId());
                    pstmtT.setInt(2, tagId);
                    pstmtT.executeUpdate();
                }else{
                    sqlTag = "INSERT INTO tag(tag_name) VALUES (?)";
                    pstmtT = dbConfig.getConnection().prepareStatement(sqlTag);
                    pstmtT.setString(1, tag);
                    pstmtT.executeUpdate();
                    sqlTag = "SELECT tag_id FROM tag WHERE tag_name = ?";
                    pstmtT = dbConfig.getConnection().prepareStatement(sqlTag);
                    pstmtT.setString(1, tag);
                    resultSet = pstmtT.executeQuery();
                    if(resultSet.next()){
                        int tagId = resultSet.getInt("tag_id");
                        sqlTag = "INSERT INTO product_tag(product_id, tag_id) VALUES (?, ?)";
                        pstmtT = dbConfig.getConnection().prepareStatement(sqlTag);
                        pstmtT.setInt(1, p.getProductId());
                        pstmtT.setInt(2, tagId);
                        pstmtT.executeUpdate();
                    }
                }
            }

        }catch(SQLException se){
            // 处理 JDBC 错误
            se.printStackTrace();
        }catch(Exception e){
            // 处理 Class.forName 错误
            e.printStackTrace();
        }
    }


}
