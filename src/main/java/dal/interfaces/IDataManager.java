package dal.interfaces;

import be.*;
import javafx.collections.ObservableList;

import java.sql.SQLException;

public interface IDataManager {
    ObservableList<Event> getAllEvents() throws Exception;

    void addNewEvent(Event newEvent) throws Exception;

    void  removeEvent(String index) throws Exception;

    ObservableList<Customer> getAllCustomers() throws Exception;

    void addNewCustomer(Customer newCustomer) throws Exception;

    void removeCustomer(String index) throws Exception;

    ObservableList<Ticket> getAllTickets() throws Exception;

    void addNewTicket(Ticket ticket) throws Exception;

    void removeTicket(int customerId,int eventID) throws Exception;

    void removeTicketWithThisEvent(int eventID) throws Exception;

    ObservableList<User> getAllUsers() throws Exception;

    void addNewUser(User newUser) throws Exception;

    void removeUser(String index) throws Exception;

    ObservableList<UserEvent> getUserToEvent() throws Exception;

    void addNewUserEvent(UserEvent userEvent) throws Exception;

    void removeEvent(int eventId, int userId) throws Exception;

    void deleteEventForAllUsers(int eventId) throws Exception;
}
