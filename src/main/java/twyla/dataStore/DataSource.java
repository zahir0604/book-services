package twyla.dataStore;

import org.springframework.stereotype.Repository;
import twyla.config.Text;

import java.sql.*;

@Repository
public class DataSource {

    private static final String DRIVER = "org.postgresql.Driver";
    private static final String URL = Text.get("url");
    private static final String USER_NAME = Text.get("username");
    private static final String PASSWORD = Text.get("password");

    public static final String BOOK_TABLE = "BOOK";
    public static final String COMMENTS_TABLE = "COMMENTS";

    private static Connection connection = null;

    public Connection getConnection() {
        try {
            if (connection == null || connection.isClosed()) {

                try {
                    Class.forName(DRIVER);
                    connection = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return connection;
    }

    public void createTableIfDoesNotExist(String table) {
        DatabaseMetaData databaseMetaData = null;
        ResultSet resultSet = null;
        Statement statement = null;
        try {
            databaseMetaData = getConnection().getMetaData();
            resultSet = databaseMetaData.getTables(null, null, table, null);
            if (!resultSet.next()) {
                resultSet = databaseMetaData.getTables(null, null, table.toLowerCase(), null);
            }
            if (!resultSet.next()) {
                statement = getConnection().createStatement();
                statement.executeUpdate(getQuery(table));
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                getConnection().close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

    }

    private String getQuery(String table) {

        String query;

        switch (table) {
            case COMMENTS_TABLE:
                query = "CREATE TABLE comments"
                    + "("
                    + "    book_id varchar(16),"
                    + "    comment varchar(255),"
                    + "    rating varchar(2),"
                    + "    user_name  varchar(16),"
                    + "    date_time timestamp"
                    + ")";

                break;

            case BOOK_TABLE:
                query = "CREATE TABLE book"
                    + "("
                    + "    isbn_id varchar(16),"
                    + "    title varchar(32),"
                    + "    user_name  varchar(16),"
                    + "    date_time timestamp,"
                    + " PRIMARY KEY ( isbn_id ))";

                break;

            default:
                throw new RuntimeException("Query not available for table : " + table);
        }

        return query;
    }

}
