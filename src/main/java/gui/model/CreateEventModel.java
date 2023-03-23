package gui.model;

import be.Event;
import dal.dao.EventDAO;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class CreateEventModel {

    EventDAO eventDAO=new EventDAO();

    public void createEvent(String eventName, String eventType, LocalDate startDate, LocalDate endDate, String location) throws SQLException {
         List<Event> events= this.eventDAO.getAllEvents();
        Event event =new Event(events.size()+1,eventName,eventType,startDate,endDate,location);

        this.eventDAO.addNewEvent(event);
    }

}
