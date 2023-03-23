package dal.dao;

import be.Customer;
import be.Event;
import dal.DataAccessManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

public class CustomerDAO {
    public static ObservableList<Customer> getAllCustomers() {
        //creating the connector
        DataAccessManager dataAccessManager = new DataAccessManager();
        //opening connection
        try (Connection connection = dataAccessManager.getConnection()) {
            ObservableList<Customer> allCustomers = FXCollections.observableArrayList();

            String sqlGetUsers = "SELECT * FROM dbo.event;";

            Statement statement = connection.createStatement();

            //Executing sql statement to get all movies
            if (statement.execute(sqlGetUsers)) {
                //Saving result set
                ResultSet resultSet = statement.getResultSet();

                //Saving result data as variables
                while (resultSet.next()) {
                    int id = resultSet.getInt("customerId");
                    String email = resultSet.getString("email");
                    String name = resultSet.getString("customer_name");


                    //Creating movie object
                    Customer customer = new Customer(id,email,name);

                    //Adding movie object to list of all movies
                    allCustomers.add(customer);
                }
            }
            return allCustomers;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static void addNewCustomer(Customer newCustomer) throws SQLException{
        //Creating dbConnector instance
        DataAccessManager dataAccessManager = new DataAccessManager();
        try(Connection connection = dataAccessManager.getConnection()) {
            int eventId = newCustomer.getId();
            String email = newCustomer.getEmail();
            String name = newCustomer.getName();

            String sql = "INSERT INTO customers VALUES" + "("+eventId+",'"+email+"', '"+ name+ "')";
            Statement statement = connection.createStatement();
            if(statement.execute(sql)){
                ResultSet resultSet = statement.getResultSet();
                System.out.println("Inserted correctly");
            }
        }
    }
    public static void removeCustomer(String index) throws SQLException {
        //Creating dbConnector instance
        DataAccessManager dataAccessManager = new DataAccessManager();
        try (Connection connection = dataAccessManager.getConnection()) {
            String sql = "Delete FROM CSe2022B_Event_Ticket_Manager].[dbo].[customers] where customer_name='" + index + "';";
            System.out.println(sql);
            Statement statement = connection.createStatement();
            if (statement.execute(sql)) {
                ResultSet resultSet = statement.getResultSet();
                System.out.println("Removed correctly");
            }
        }

    }
}
