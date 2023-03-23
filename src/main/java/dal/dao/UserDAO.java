package dal.dao;

import be.User;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import dal.DataAccessManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

public class UserDAO {

    public static ObservableList<User> getAllUsers() {
        //creating the connector
        DataAccessManager dataAccessManager = new DataAccessManager();
        //opening connection
        try (Connection connection = dataAccessManager.getConnection()) {
            ObservableList<User> allUsers = FXCollections.observableArrayList();

            String sqlGetUsers = "SELECT * FROM event_users;";

            Statement statement = connection.createStatement();

            //Executing sql statement to get all movies
            if (statement.execute(sqlGetUsers)) {
                //Saving result set
                ResultSet resultSet = statement.getResultSet();

                //Saving result data as variables
                while (resultSet.next()) {
                    int id = resultSet.getInt("userId");
                    String username = resultSet.getString("userName");
                    String password = resultSet.getString("password");
                    String type = resultSet.getString("type");

                    //Creating movie object
                    User user = new User(id, username, password, type);

                    //Adding movie object to list of all movies
                    allUsers.add(user);
                }
            }
            return allUsers;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static void addNewUser(User newUser) throws SQLException{
        //Creating dbConnector instance
        DataAccessManager dataAccessManager = new DataAccessManager();
        try(Connection connection = dataAccessManager.getConnection()) {
            int userId = newUser.getId();
            String userName = newUser.getUsername();
            String password = newUser.getPassword();
            String type = newUser.getType();
            String sql = "INSERT INTO event_user VALUES" + "("+userId+",'"+userName+"', "+password+", '"+type+"')";
            Statement statement = connection.createStatement();
            if(statement.execute(sql)){
                ResultSet resultSet = statement.getResultSet();
                System.out.println("Inserted correctly");
            }
        }
    }

    public static void removeUser(String index) throws SQLException {
        //Creating dbConnector instance
        DataAccessManager dataAccessManager = new DataAccessManager();
        try (Connection connection = dataAccessManager.getConnection()) {
            String sql = "Delete FROM [CSe2022B_Event_Ticket_Manager].[dbo].[event_users] where userName ='" + index + "';";
            System.out.println(sql);
            Statement statement = connection.createStatement();
            if (statement.execute(sql)) {
                ResultSet resultSet = statement.getResultSet();
                System.out.println("Removed correctly");
            }
        }

    }
}

