package gui.model;

import be.Event;
import be.User;
import be.UserEvent;
import bll.AppLogicManager;
import dal.dao.EventDAO;
import dal.dao.UserDAO;
import dal.dao.UserToEventDAO;
import javafx.collections.ObservableList;

import java.sql.SQLException;
import java.util.List;

public class SetManagerModel {

    private AppLogicManager appLogicManager;

    MainViewModel mainViewModel;

    public SetManagerModel(MainViewModel mainViewModel) {
        this.appLogicManager = new AppLogicManager();
        this.mainViewModel = mainViewModel;
    }




    public boolean setManagerToEvent(User user,Event event) throws Exception {

        final boolean[] token = new boolean[1];

        UserEvent userEvent=new UserEvent(event.getId(),user.getId());

        mainViewModel.getAllUserEvents().forEach(e->{
            if(e.getEventId()==userEvent.getEventId() && e.getUserId()==userEvent.getUserId()){
                token[0] = false;
            }
        });

        if (!token[0]) {
            appLogicManager.addNewUserEvent(userEvent);
            mainViewModel.addUserEvent(userEvent);
            return token[0];
        }else {
            return token[0];
        }



    }
}
