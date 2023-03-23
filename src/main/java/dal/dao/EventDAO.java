package dal.dao;

import be.Event;
import be.User;
import dal.DataAccessManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

public class EventDAO {
    public static ObservableList<Event> getAllEvents() {
        //creating the connector
        DataAccessManager dataAccessManager = new DataAccessManager();
        //opening connection
        try (Connection connection = dataAccessManager.getConnection()) {
            ObservableList<Event> allEvents = FXCollections.observableArrayList();

            String sqlGetUsers = "SELECT * FROM dbo.event;";

            Statement statement = connection.createStatement();

            //Executing sql statement to get all movies
            if (statement.execute(sqlGetUsers)) {
                //Saving result set
                ResultSet resultSet = statement.getResultSet();

                //Saving result data as variables
                while (resultSet.next()) {
                    int id = resultSet.getInt("eventId");
                    String name = resultSet.getString("event_name");
                    String type = resultSet.getString("event_type");
                    Date startDate = resultSet.getDate("event_date");
                    Date endDate = resultSet.getDate("event_end_date");
                    String location = resultSet.getString("location");

                    //Creating movie object
                    Event event= new Event(id, name,type,startDate, endDate, location );

                    //Adding movie object to list of all movies
                    allEvents.add(event);
                }
            }
            return allEvents;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static void addNewEvent(Event newEvent) throws SQLException{
        //Creating dbConnector instance
        DataAccessManager dataAccessManager = new DataAccessManager();
        try(Connection connection = dataAccessManager.getConnection()) {
            int eventId = newEvent.getId();
            String name = newEvent.getName();
            String type = newEvent.getType();
            Date startDate = (Date) newEvent.getStartDate();
            Date endDate = (Date) newEvent.getEndDate();
            String location = newEvent.getLocation();

            String sql = "INSERT INTO event VALUES" + "("+eventId+",'"+name+"', "+type+", '"+startDate+"', '"+endDate+"', '"+location+"')";
            Statement statement = connection.createStatement();
            if(statement.execute(sql)){
                ResultSet resultSet = statement.getResultSet();
                System.out.println("Inserted correctly");
            }
        }
    }
    public static void removeEvent(String index) throws SQLException {
        //Creating dbConnector instance
        DataAccessManager dataAccessManager = new DataAccessManager();
        try (Connection connection = dataAccessManager.getConnection()) {
            String sql = "Delete FROM CSe2022B_Event_Ticket_Manager].[dbo].[event] where event_name='" + index + "';";
            System.out.println(sql);
            Statement statement = connection.createStatement();
            if (statement.execute(sql)) {
                ResultSet resultSet = statement.getResultSet();
                System.out.println("Removed correctly");
            }
        }

    }
}
