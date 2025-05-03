package Service;

import java.sql.Connection;
import java.sql.DriverManager;

public class DbService {
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/first-jsp";
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "root123";

    public Connection getConnection() {
        try {
            Class.forName("org.postgresql.Driver");
            return DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
