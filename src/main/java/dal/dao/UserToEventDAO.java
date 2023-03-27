package dal.dao;

import be.UserEvent;
import dal.DataAccessManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javax.xml.crypto.Data;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UserToEventDAO {
    public static ObservableList<UserEvent> getUserToEvent() {
        DataAccessManager dataAccessManager = new DataAccessManager();
        //Opening connection
        try (Connection connection = dataAccessManager.getConnection()) {
            ObservableList<UserEvent> userEvents = FXCollections.observableArrayList();

            String sqlGetCategories = "SELECT * FROM user_to_event;";
            Statement statement = connection.createStatement();
            //Executing sql statement to get all movies
            if (statement.execute(sqlGetCategories)) {
                //Saving result set
                ResultSet resultSet = statement.getResultSet();
                //Saving result data as variables
                while (resultSet.next()) {
                    int eventId=resultSet.getInt("eventId");
                    int userId = resultSet.getInt("userId");

                    //Creating category object
                    UserEvent userEvent=new UserEvent(eventId,userId);


                    //Adding movie object to list of all movies
                    userEvents.add(userEvent);
                }
            }
            return userEvents;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static void addNewUserEvent(UserEvent userEvent) throws SQLException{
        //Creating dbConnector instance
        DataAccessManager dataAccessManager = new DataAccessManager();
        try(Connection connection = dataAccessManager.getConnection()) {
            String sql = "INSERT INTO [CSe2022B_Event_Ticket_Manager].[dbo].[user_to_event] VALUES ( '"+ userEvent.getEventId()+"','"+userEvent.getUserId()+ "')";
            Statement statement = connection.createStatement();
            if(statement.execute(sql)){
                ResultSet resultSet = statement.getResultSet();
                System.out.println("Inserted correctly");
            }
        }
    }

    public static void removeEvent(int eventId, int userId) throws SQLException{
        //Creating dbConnector instance
        DataAccessManager dataAccessManager = new DataAccessManager();
        try(Connection connection = dataAccessManager.getConnection()) {
            String sql = "Delete FROM [CSe2022B_Event_Ticket_Manager].[dbo].[user_to_event]  where eventId=" + eventId + "and userId="+userId+';';
            Statement statement = connection.createStatement();
            if(statement.execute(sql)){
                ResultSet resultSet = statement.getResultSet();
            }
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static void deleteEventForAllUsers(int eventId) throws SQLException{
        //Creating dbConnector instance
        DataAccessManager dataAccessManager = new DataAccessManager();
        try(Connection connection = dataAccessManager.getConnection()) {
            String sql = "Delete FROM [CSe2022B_Event_Ticket_Manager].[dbo].[user_to_event]  where eventId=" + eventId +';';
            Statement statement = connection.createStatement();
            if(statement.execute(sql)){
                ResultSet resultSet = statement.getResultSet();
            }
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
