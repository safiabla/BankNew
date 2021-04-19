package utilities.db;

import junit.framework.Assert;
import org.apache.commons.dbutils.BeanProcessor;
import sun.plugin.util.UserProfile;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class
DBUtils2 {
    //TODO
    // If it is required to deal with DB more than as in DBUtils2, if you work with API,
    // you need to deal with Java Objects. So, here you should use this version with BEANS
    // We don`t create models for DataBase, for DB we create queries. Models (beans) will
    // be created for API`s. So, we`ll create models and convert weird format into our model.
    // From DB side we need to match that model with the query, and have this method which
    // convert ResultSet to the model.
    // We can create specific methods for each model.
    // It is easier to work with objects than with HashMaps.

    private static Connection connection;
    private static Statement statement;
    private static final String JDBC_URL = "jdbc:mysql://3.131.35.165:3306/digitalbank?user=dbank&password=MyCOMPleaxPasSW0rd!12X";
    private static BeanProcessor beanProcessor; //=> processing our models

    public static void open() {
        try {
            if (connection == null) {
                connection = DriverManager.getConnection(JDBC_URL);
                statement = connection.createStatement();
                beanProcessor = new BeanProcessor();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            Assert.fail("Can`t establish connection to DB");
        }
    }

    // Closing connection. Very important to close connection in order to avoid memory leak
    public static void close() {
        try {
            if (statement != null) statement.close();
            if (connection != null) connection.close();
            connection = null;
            statement = null;
        } catch (SQLException e) {
            e.printStackTrace();
            Assert.fail("Can't close connection to DB");
        }
    }

    // Get Column names by using Result Set metadata
    public static List<String> getColumnNames(ResultSet rs) {
        List<String> columnNames = new ArrayList<>();
        try {
            ResultSetMetaData metaData = rs.getMetaData();
            int columnCount = metaData.getColumnCount();

            for (int i = 1; i <= columnCount; i++) {
                columnNames.add(metaData.getColumnName(i));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            Assert.fail(e.getLocalizedMessage());
        }
        return columnNames;
    }

    // Object... - VarArgs => we can use in Array as many object as we need or Non of objects
    // we use it in order to have more control over queries
    //SELECT * FROM digitalbank.user_profile WHERE id = ?;
    public static ResultSet query(String query, Object... params) {
        if (connection == null) open();
        try {
            if (params.length == 0)
                return statement.executeQuery(query);
            else {
                // this class help us to insert params instead "?", it is tracking ? mark
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                // Replace "?" with params:
                for (int i = 0; i < params.length; i++) {
                    preparedStatement.setObject(i + 1, params[i]);
                }
                return preparedStatement.executeQuery();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            Assert.fail("Not able to execute a query");
        }
        return null;
    }

    //T- generic data type, it postpones the Data type declaration;
    public static <T> List<T> convertResultSetToBeans(ResultSet rs, Class<T> beanClass) {
        try {
            return beanProcessor.toBeanList(rs, beanClass);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return new ArrayList<>();
    }

    //key = column name, value - data in that column
    public static List<Map<String, Object>> convertResultSetToListOfMaps(ResultSet rs) {
        List<Map<String, Object>> table = new ArrayList<>();
        List<String> columnNames = getColumnNames(rs);

        // Populate table From result set
        // Iterate through each Row

        while (true) {
            // Map is created for each row
            Map<String, Object> row = new HashMap<>();
            try {
                if (!rs.next()) break;
                // Iterate through each column in order to populate the map
                for (String columnName : columnNames) {
                    row.put(columnName, rs.getObject(columnName));
                }
                table.add(row);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        try {
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return table;
    }


    public static void main(String[] args) {
        String query = "SELECT * FROM user_profile WHERE country = ? AND dob <= ?;";
        ResultSet rs = DBUtils2.query(query, "US", "2002-01-01 00:00:00");
        DBUtils2.convertResultSetToBeans(rs, UserProfile.class).forEach(System.out::println);
//        System.out.println(UserProfile.getById(5));
//        UserProfile.getByFirstName("Alfa");
    }
}


