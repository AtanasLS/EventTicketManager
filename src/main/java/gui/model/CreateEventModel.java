package gui.model;

import be.Event;
import be.User;
import dal.dao.EventDAO;
import javafx.collections.ObservableList;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public class CreateEventModel {

    EventDAO eventDAO=new EventDAO();

    public void createEvent(String eventName, String eventType, Date startDate, Date endDate,String location) throws SQLException {
         List<Event> events= this.eventDAO.getAllEvents();
        Event event =new Event(events.size()+1,eventName,eventType,startDate,endDate,location);

        this.eventDAO.addNewEvent(event);
    }

}
