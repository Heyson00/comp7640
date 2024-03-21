package person.nicholas.crm.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConfig {

    private static final String DB_URL = "jdbc:mysql://hkg1.clusters.zeabur.com:31183/vendor_system?useSSL=false";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "a6L15HnBQImgxt3zG2b0cUJ8Ks4R7Z9p";
    private Connection connection;
    private static DatabaseConfig instance;

    private DatabaseConfig() {
        try {
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static DatabaseConfig getInstance() {
        if (instance == null) {
            instance = new DatabaseConfig();
        }else {
            try{
                if(instance.getConnection().isClosed()){
                    instance = new DatabaseConfig();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        return instance;
    }

    public Connection getConnection() {
        return connection;
    }
}
