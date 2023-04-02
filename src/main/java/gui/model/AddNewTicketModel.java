package gui.model;

import be.Customer;
import be.Event;
import be.Ticket;
import be.User;
import dal.dao.CustomerDAO;
import dal.dao.EventDAO;
import dal.dao.TicketDAO;
import javafx.collections.ObservableList;

import java.sql.SQLException;
import java.util.List;

public class AddNewTicketModel {

    TicketDAO ticketDAO = new TicketDAO();
    CustomerDAO customerDAO = new CustomerDAO();
    EventDAO eventDAO = new EventDAO();

    public AddNewTicketModel(){

    }
    public ObservableList<Ticket> getAllTickets(){
        return ticketDAO.getAllTickets();
    }

    public ObservableList<Customer> getAllCustomers(){
      return   customerDAO.getAllCustomers();
    }
    public ObservableList<Event> getAllEvents(){
        return eventDAO.getAllEvents();
    }

    private Event selectedEvent;
    private Customer selectedCustomer;
    public boolean addTicket(int customerId, int eventId) throws SQLException {
        List<Ticket> tickets = getAllTickets();
        boolean token = false;


        if (!token) {
            if (tickets.size() != 0) {
                Ticket ticket = new Ticket(tickets
                        .get(tickets.size() - 1).getTicketId() + 1, customerId, eventId);
                this.ticketDAO.addNewTicket(ticket);
            }else {
                Ticket ticket = new Ticket(1, customerId, eventId);
                this.ticketDAO.addNewTicket(ticket);
            }
            return token;
        } else {
            return token;
        }
    }
    public void setCustomerAndEvent(Customer customer, Event event){
        this.selectedCustomer = customer;
        this.selectedEvent = event;
    }
    public Event getSelectedEvent(){
        return selectedEvent;
    }public Customer getSelectedCustomer(){
        return selectedCustomer;
    }


}
