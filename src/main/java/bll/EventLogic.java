package bll;

import be.Event;
import dal.DataManagerFacade;
import dal.interfaces.IDataManager;
import javafx.collections.ObservableList;

public class EventLogic {
    private IDataManager dataManager;

    public EventLogic(){
        this.dataManager = DataManagerFacade.getInstance();
    }

    public ObservableList<Event> getAllEvents() throws Exception{
        try {
            return dataManager.getAllEvents();
        }catch (Exception exception){
            throw new Exception("Can't read all the events. Check the connection");
        }
    }
}
