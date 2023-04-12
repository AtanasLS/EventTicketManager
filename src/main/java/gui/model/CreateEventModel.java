package gui.model;

import be.Event;
import bll.AppLogicManager;
import dal.dao.EventDAO;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

public class CreateEventModel {

    MainViewModel mainViewModel;
    private AppLogicManager appLogicManager;
    public CreateEventModel(MainViewModel mvm) {
        this.appLogicManager = new AppLogicManager();
        this.mainViewModel = mvm;
    }

    public void createEvent(String eventName, String eventType, LocalDateTime startDate, LocalDateTime endDate, String location) throws Exception {
         List<Event> events= this.mainViewModel.getAllEvents();
         if (events.size() == 0){

             Event event = new Event(1,eventName, eventType, startDate, endDate, location);
             this.mainViewModel.addEvent(event);
             appLogicManager.addNewEvent(event);
         }else {
             Event event = new Event(mainViewModel.getAllEvents()
                     .get(mainViewModel.getAllEvents().size() - 1).getId() + 1, eventName, eventType, startDate, endDate, location);
             appLogicManager.addNewEvent(event);
             this.mainViewModel.addEvent(event);
         }
    }

}
