package dal;

import be.*;
import dal.dao.*;
import dal.interfaces.IDataManager;
import javafx.collections.ObservableList;

public class DataManagerFacade  implements IDataManager {

    private static DataManagerFacade instance; //Singleton instance

    UserDAO userDAO;
    TicketDAO ticketDAO;
    EventDAO eventDAO;
    CustomerDAO customerDAO;
    UserToEventDAO userToEventDAO;
    private DataManagerFacade() {
        userDAO = new UserDAO();
        eventDAO = new EventDAO();
        customerDAO = new CustomerDAO();
        ticketDAO = new TicketDAO();
        userToEventDAO = new UserToEventDAO();
    }

    public static DataManagerFacade getInstance() {
        return instance == null ? instance = new DataManagerFacade() : instance;
    }


    @Override
    public ObservableList<Event> getAllEvents() throws Exception {
        return eventDAO.getAllEvents();
    }

    @Override
    public void addNewEvent(Event newEvent) throws Exception {
         eventDAO.addNewEvent(newEvent);
    }

    @Override
    public void removeEvent(String index) throws Exception {
        eventDAO.removeEvent(index);
    }

    @Override
    public ObservableList<Customer> getAllCustomers() throws Exception {
        return customerDAO.getAllCustomers();
    }

    @Override
    public void addNewCustomer(Customer newCustomer) throws Exception {
        customerDAO.addNewCustomer(newCustomer);
    }

    @Override
    public void removeCustomer(String index) throws Exception {
        customerDAO.removeCustomer(index);
    }

    @Override
    public ObservableList<Ticket> getAllTickets() throws Exception {
        return ticketDAO.getAllTickets();
    }

    @Override
    public void addNewTicket(Ticket ticket) throws Exception {
        ticketDAO.addNewTicket(ticket);
    }

    @Override
    public void removeTicket(int customerId, int eventID) throws Exception {
        ticketDAO.removeTicket(customerId, eventID);
    }

    @Override
    public void removeTicketWithThisEvent(int eventID) throws Exception {
        ticketDAO.removeTicketWithThisEvent(eventID);
    }

    @Override
    public ObservableList<User> getAllUsers() throws Exception {
        return userDAO.getAllUsers();
    }

    @Override
    public void addNewUser(User newUser) throws Exception {
        userDAO.addNewUser(newUser);
    }

    @Override
    public void removeUser(String index) throws Exception {
        userDAO.removeUser(index);
    }

    @Override
    public ObservableList<UserEvent> getUserToEvent() throws Exception {
        return userToEventDAO.getUserToEvent();
    }

    @Override
    public void addNewUserEvent(UserEvent userEvent) throws Exception {
        userToEventDAO.addNewUserEvent(userEvent);
    }

    @Override
    public void removeEvent(int eventId, int userId) throws Exception {
        userToEventDAO.removeEvent(eventId,userId);
    }

    @Override
    public void deleteEventForAllUsers(int eventId) throws Exception {
        userToEventDAO.deleteEventForAllUsers(eventId);
    }
}
