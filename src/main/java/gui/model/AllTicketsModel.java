package gui.model;

import be.Customer;
import be.Event;
import be.Ticket;
import be.TicketObs;
import bll.AppLogicManager;
import dal.dao.EventDAO;
import dal.dao.TicketDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;

public class AllTicketsModel {

    private final ObservableList<Ticket> tickets;

    private ObservableList<Event> events;
    private  ObservableList<Customer> customers;

    private ObservableList<TicketObs> ticketsWithName;

    public AllTicketsModel(){
        this.tickets = FXCollections.observableArrayList();
        this.events = FXCollections.observableArrayList();
        this.customers = FXCollections.observableArrayList();
        this.ticketsWithName = FXCollections.observableArrayList();

    }

    public void loadFromDB() throws Exception {
        AppLogicManager appLogicManager = new AppLogicManager();
        this.customers.clear();
        this.customers.addAll(appLogicManager.getAllCustomers());
        this.events.clear();
        this.events.addAll(appLogicManager.getAllEvents());
        this.tickets.clear();
        this.tickets.addAll(appLogicManager.getAllTickets());
        this.ticketsWithName.clear();

    }

    private void setNames() throws Exception {
        this.loadFromDB();

        int currentEvent=-1;
        int currentCustomer=-1;

        String cName="";
        String eName="";

        for (Ticket t:this.tickets) {
             currentEvent=t.getEventId();
             currentCustomer=t.getCustomerId();

            for (Customer c:this.customers) {
                if(c.getId()==currentCustomer){
                    cName=c.getName();
                }
            }

            for (Event e:this.events) {
                if(e.getId()==currentEvent){
                    eName=e.getName();
                }
            }

            this.ticketsWithName.add(new TicketObs(cName,eName));
        }
    }

    public ObservableList<TicketObs> getTickets() throws Exception {
        this.setNames();
        return this.ticketsWithName;
    }

    public Event getEvent(String name){
        for (Event e :this.events) {
            if (e.getName().equals(name)){
                return e;
            }
        }
        return null;
    }

    public Customer getCustomer(String name){
        for (Customer c :this.customers) {
            if (c.getName().equals(name)){
                return c;
            }
        }
        return null;
    }

}
