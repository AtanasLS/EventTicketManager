package gui.model;

import be.Event;
import dal.dao.EventDAO;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

public class CreateEventModel {

    MainViewModel mainViewModel;
    public CreateEventModel(MainViewModel mvm) {
        this.mainViewModel = mvm;
    }

    public void createEvent(String eventName, String eventType, LocalDateTime startDate, LocalDateTime endDate, String location) throws SQLException {
         List<Event> events= this.mainViewModel.getAllEvents();
         if (events.size() == 0){

             Event event = new Event(1,eventName, eventType, startDate, endDate, location);
             this.mainViewModel.addEvent(event);
             EventDAO.addNewEvent(event);
         }else {
             Event event = new Event(mainViewModel.getAllEvents()
                     .get(mainViewModel.getAllEvents().size() - 1).getId() + 1, eventName, eventType, startDate, endDate, location);
             EventDAO.addNewEvent(event);
             this.mainViewModel.addEvent(event);
         }
    }

}
