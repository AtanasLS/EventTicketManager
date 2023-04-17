package gui.model;

import be.Customer;
import be.Event;
import be.Ticket;
import be.User;
import bll.AppLogicManager;
import dal.dao.CustomerDAO;
import dal.dao.EventDAO;
import dal.dao.TicketDAO;
import javafx.collections.ObservableList;

import java.sql.SQLException;
import java.util.List;

public class AddNewTicketModel {

    MainViewModel mainViewModel;
    private AppLogicManager appLogicManager;
    public AddNewTicketModel(MainViewModel mainViewModel){
        this.mainViewModel = mainViewModel;
        this.appLogicManager = new AppLogicManager();
    }

    private Event selectedEvent;
    private Customer selectedCustomer;
    public boolean addTicket(int customerId, int eventId) throws Exception {
        List<Ticket> tickets = appLogicManager.getAllTickets();
        boolean token = false;


        if (!token) {
            if (tickets.size() != 0) {
                Ticket ticket = new Ticket(tickets
                        .get(tickets.size() - 1).getTicketId() + 1, customerId, eventId);
                appLogicManager.addNewTicket(ticket);
            }else {
                Ticket ticket = new Ticket(1, customerId, eventId);
                appLogicManager.addNewTicket(ticket);
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
