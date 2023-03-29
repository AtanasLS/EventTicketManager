package gui.model;

import be.Event;
import be.User;
import be.UserEvent;
import dal.dao.EventDAO;
import dal.dao.UserDAO;
import dal.dao.UserToEventDAO;
import javafx.collections.ObservableList;

import java.sql.SQLException;
import java.util.List;

public class SetManagerModel {

    private UserDAO userDAO =new UserDAO();
    private EventDAO eventDAO =new EventDAO();
    private UserToEventDAO userToEventDAO = new UserToEventDAO();




    public ObservableList<User>getAllUsers(){
        return this.userDAO.getAllUsers();
    }

    public ObservableList<Event>getAllEvents(){
        return this.eventDAO.getAllEvents();
    }

    public boolean setManagerToEvent(User user,Event event) throws SQLException {

        final boolean[] token = new boolean[1];

        UserEvent userEvent=new UserEvent(user.getId(),event.getId());

        this.userToEventDAO.getUserToEvent().forEach(e->{
            if(e.getEventId()==userEvent.getEventId() && e.getUserId()==userEvent.getUserId()){
                token[0] = false;
                return;

            }
        });

        if (!token[0]) {


            userToEventDAO.addNewUserEvent(userEvent);
            return token[0];
        }else {
            return token[0];
        }



    }
}
