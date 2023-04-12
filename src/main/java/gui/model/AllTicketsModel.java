package gui.model;

import be.Event;
import be.Ticket;
import bll.AppLogicManager;
import dal.dao.TicketDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class AllTicketsModel {

    private final ObservableList<Ticket> tickets;

    public AllTicketsModel(){
        this.tickets = FXCollections.observableArrayList();

    }

    public ObservableList<Ticket> loadFromDB() throws Exception {
        AppLogicManager appLogicManager = new AppLogicManager();
        this.tickets.clear();
        this.tickets.addAll(appLogicManager.getAllTickets());
        return this.tickets;
    }

}
