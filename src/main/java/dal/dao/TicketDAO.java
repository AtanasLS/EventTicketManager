package dal.dao;

import be.Customer;
import be.Ticket;
import dal.DataAccessManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TicketDAO {
    public static ObservableList<Ticket> getAllTickets() {
        //creating the connector
        DataAccessManager dataAccessManager = new DataAccessManager();
        //opening connection
        try (Connection connection = dataAccessManager.getConnection()) {
            ObservableList<Ticket> allTickets = FXCollections.observableArrayList();

            String sqlGetUsers = "SELECT * FROM ticket;";

            Statement statement = connection.createStatement();

            //Executing sql statement to get all movies
            if (statement.execute(sqlGetUsers)) {
                //Saving result set
                ResultSet resultSet = statement.getResultSet();

                //Saving result data as variables
                while (resultSet.next()) {
                    int ticketId = resultSet.getInt("ticketId");
                    int customerId = resultSet.getInt("customerId");
                    int eventId = resultSet.getInt("eventId");

                    //Creating movie object
                    Ticket ticket = new Ticket(ticketId,customerId,eventId);

                    //Adding movie object to list of all movies
                    allTickets.add(ticket);
                }
            }
            return allTickets;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static void addNewTicket(Ticket ticket) throws SQLException{
        //Creating dbConnector instance
        DataAccessManager dataAccessManager = new DataAccessManager();
        try(Connection connection = dataAccessManager.getConnection()) {
            String sql = "INSERT INTO ticket VALUES ("+ticket.getTicketId()+ " ,"+ ticket.getCustomerId()+",'"+ticket.getEventId()+"')";
            Statement statement = connection.createStatement();
            if(statement.execute(sql)){
                ResultSet resultSet = statement.getResultSet();
                System.out.println("Inserted correctly");
            }
        }
    }
    public static void removeTicket(int customerId,int eventID) throws SQLException{
        //Creating dbConnector instance
        DataAccessManager dataAccessManager = new DataAccessManager();
        try(Connection connection = dataAccessManager.getConnection()) {
            String sql = "Delete FROM [CSe2022B_Event_Ticket_Manager].[dbo].[ticket] where customerId=" + customerId + "and eventId="+eventID+';';
            System.out.println(sql);
            Statement statement = connection.createStatement();
            if(statement.execute(sql)){
                ResultSet resultSet = statement.getResultSet();
                System.out.println("Removed correctly");
            }
        }
    }
    public static void removeTicketWithThisEvent(int eventID) throws SQLException{
        //Creating dbConnector instance
        DataAccessManager dataAccessManager = new DataAccessManager();
        try(Connection connection = dataAccessManager.getConnection()) {
            String sql = "Delete FROM [CSe2022B_Event_Ticket_Manager].[dbo].[ticket] where eventId=" + eventID +';';
            System.out.println(sql);
            Statement statement = connection.createStatement();
            if(statement.execute(sql)){
                ResultSet resultSet = statement.getResultSet();
                System.out.println("Removed correctly");
            }
        }
    }
}
