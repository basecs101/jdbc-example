import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAO {
    private static final String URL = "jdbc:mysql://localhost:3306/ecommerce";
    private static final String USER = "root";
    private static final String PASSWORD = "root";

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    // Create (Insert a new customer)
    public void addCustomer(Customer customer) {
        String insertQuery = """
                INSERT INTO CUSTOMERS
                (
                CustomerID,
                CustomerName,
                ContactName,
                Address,
                City,
                PostalCode,
                Country
                )
                VALUES
                (?,?,?,?,?,?,?);""";
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {

            preparedStatement.setInt(1, customer.getId());
            preparedStatement.setString(2, customer.getName());
            preparedStatement.setString(3, customer.getContactName());
            preparedStatement.setString(4, customer.getAddress());
            preparedStatement.setString(5, customer.getCity());
            preparedStatement.setInt(6, customer.getPostalCode());
            preparedStatement.setString(7, customer.getCountry());

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Customer added successfully!");
            } else {
                System.out.println("Failed to add customer.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Read (Retrieve all customers)
    public List<Customer> getAllCustomers() {
        List<Customer> customers = new ArrayList<>();
        String selectQuery = "SELECT * FROM customers";
        try (Connection connection = getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(selectQuery)) {

            while (resultSet.next()) {
                int id = resultSet.getInt("CustomerID");
                String name = resultSet.getString("CustomerName");
                String contactName = resultSet.getString("ContactName");
                String address = resultSet.getString("Address");
                String city = resultSet.getString("City");
                int postalCode = resultSet.getInt("PostalCode");
                String country = resultSet.getString("Country");


                Customer customer = new Customer(id, name, contactName, address,
                        city, postalCode, country);
                customers.add(customer);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customers;
    }

    // Update (Update customer information)
    public void updateCustomer(int id, String newName) {
        String updateQuery = "UPDATE customers SET CustomerName=? WHERE CustomerID=?";
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {

            preparedStatement.setString(1, newName);
            preparedStatement.setInt(2, id);

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Customer " + id + " updated successfully!");
            } else {
                System.out.println("Failed to update customer.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Delete (Delete a customer)
    public void deleteCustomer(int id) {
        String deleteQuery = "DELETE FROM customers WHERE CustomerID=?";
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery)) {

            preparedStatement.setInt(1, id);

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Customer " + id + " deleted successfully!");
            } else {
                System.out.println("Failed to delete customer.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        CustomerDAO customerDAO = new CustomerDAO();

        Customer customer = new Customer(101, "Mike", "Ali", "NYC",
                "NYC", 1010101, "USA");
        // Add a new customer
        customerDAO.addCustomer(customer);

        // Get all customers
        List<Customer> allCustomers = customerDAO.getAllCustomers();
        System.out.println("All Customers:");
        for (Customer customer1 : allCustomers) {
            System.out.println("CustomerID : "+customer1.getId());
        }

        // Update a customer
        customerDAO.updateCustomer(101, "Random Person Name");

        // Delete a customer
        customerDAO.deleteCustomer(101);
    }
}
