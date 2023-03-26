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
         if (events.size() == 0){
             Event event = new Event(1,eventName, eventType, startDate, endDate, location);
             this.eventDAO.addNewEvent(event);
         }else {
             Event event = new Event(eventDAO.getAllEvents()
                     .get(eventDAO.getAllEvents().size() - 1).getId() + 1, eventName, eventType, startDate, endDate, location);

             this.eventDAO.addNewEvent(event);
         }
    }

}
