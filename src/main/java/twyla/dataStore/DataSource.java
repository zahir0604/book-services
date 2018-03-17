package twyla.dataStore;

import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.DriverManager;

@Repository
public class DataSource {

    private static final String DRIVER = "org.hsqldb.jdbc.JDBCDriver";
    private static final String URL = "jdbc:hsqldb:file:C:/Workspace/Twyla/book-services/books";
    private static final String USER_NAME = "sa";
    private static final String PASSWORD = "";

    public Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName(DRIVER);
            connection = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }

        return connection;
    }

}
