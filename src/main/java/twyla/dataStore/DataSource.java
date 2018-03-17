package twyla.dataStore;

import org.springframework.stereotype.Repository;

import java.sql.*;

@Repository
public class DataSource {

    private static final String DRIVER = "org.hsqldb.jdbc.JDBCDriver";
    private static final String URL = "jdbc:hsqldb:file:C:/Workspace/Twyla/book-services/books";
    private static final String USER_NAME = "sa";
    private static final String PASSWORD = "";

    public static final String BOOK_TABLE = "BOOK";
    public static final String COMMENTS_TABLE = "COMMENTS";

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

    public void createTableIfDoesNotExist(String table) {
        DatabaseMetaData databaseMetaData = null;
        ResultSet resultSet = null;
        Statement statement = null;
        try {

            databaseMetaData = getConnection().getMetaData();
            resultSet = databaseMetaData.getTables(null, null, table, null);
            if (!resultSet.next()) {
                statement = getConnection().createStatement();
                statement.executeUpdate(getQuery(table));
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }

    private String getQuery(String table) {

        String query;

        switch (table) {
            case COMMENTS_TABLE:
                query = "CREATE TABLE COMMENTS"
                    + "("
                    + "    BOOK_ID varchar(16),"
                    + "    COMMENT varchar(255),"
                    + "    RATING varchar(2),"
                    + "    USER varchar(16),"
                    + "    DATE_TIME timestamp"
                    + ")";
                break;

            case BOOK_TABLE:
                query = "CREATE TABLE BOOK"
                    + "("
                    + "    ISBN_ID varchar(16),"
                    + "    TITLE varchar(32),"
                    + "    USER varchar(16),"
                    + "    DATE_TIME timestamp,"
                    + " PRIMARY KEY ( ISBN_ID ))";

                break;

            default:
                throw new RuntimeException("Query not available for table : " + table);
        }

        return query;
    }

}
