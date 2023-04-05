package bll;

import be.Event;
import dal.DataManagerFacade;
import dal.interfaces.IDataManager;
import javafx.collections.ObservableList;

public class EventLogicManager {
    private IDataManager dataManager;

    public EventLogicManager(){
        this.dataManager = DataManagerFacade.getInstance();
    }

    public ObservableList<Event> getAllEvents() throws Exception{
        try {
            return dataManager.getAllEvents();
        }catch (Exception exception){
            throw new Exception("Can't read all the events. Check the connection");
        }
    }
    public void addNewEvent(Event newEvent) throws Exception{
        try {
             dataManager.addNewEvent(newEvent);
        }catch (Exception exception){
            throw new Exception("Could not create a new event! Check the connection or the query!");
        }
    }
    public void removeEvent(String index) throws Exception{
        try {
            dataManager.removeEvent(index);
        }catch (Exception exception){
            throw new Exception("Could not remove this event! Check the connection or the query!");
        }
    }
}
