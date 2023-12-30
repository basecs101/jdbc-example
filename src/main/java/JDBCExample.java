import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCExample {

    public static void main(String[] args) {
        // JDBC URL, username, and password of the MySQL database
        String url = "jdbc:mysql://localhost:3306/ecommerce";
        String user = "root";
        String password = "root";

        // Establishing the connection
        try (Connection connection = DriverManager.getConnection(url, user, password)) {

            System.out.println("Connected to the database!");

            // Creating a statement
            Statement statement = connection.createStatement();

            // SQL query to retrieve data
            String query = "SELECT * FROM customers";

            // Executing the query
            ResultSet resultSet = statement.executeQuery(query);

            // Displaying the results
            while (resultSet.next()) {
                int customerId = resultSet.getInt("customerid");
                String customerName = resultSet.getString("customername");
                String city = resultSet.getString("city");

                System.out.println("customerId: " + customerId + ", customerName: " + customerName + "," +
                        " city: " + city);
            }
        } catch (SQLException e) {
            System.err.println("Error connecting to the database: " + e.getMessage());
        }
    }
}

