package gui.model;

import be.Event;
import be.Ticket;
import dal.dao.TicketDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class AllTicketsModel {

    private final ObservableList<Ticket> tickets;

    public AllTicketsModel(){
        this.tickets = FXCollections.observableArrayList();

    }

    public ObservableList<Ticket> loadFromDB(){
        TicketDAO ticketDAO=new TicketDAO();
        this.tickets.clear();
        this.tickets.addAll(ticketDAO.getAllTickets());
        return this.tickets;
    }

}
