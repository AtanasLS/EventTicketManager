package gui.model;

import be.Event;
import be.User;
import dal.dao.EventDAO;
import dal.dao.UserDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.SQLException;

public class MainViewModel {
         UserDAO userDAO = new UserDAO();
         EventDAO eventDAO = new EventDAO();


        public ObservableList<User> getAllManagers(){
            ObservableList<User>  managers = FXCollections.observableArrayList();;
            ObservableList<User> allUsers = UserDAO.getAllUsers();
            for (User u:
                 allUsers) {
                if (u.getType().equals("Event Coordinator")){
                    managers.add(u);
                }
            }
            return managers;
         }
         public ObservableList<Event> getAllEvents(){
        return    EventDAO.getAllEvents();
        }
        public void deleteUser(String index) throws SQLException {
                UserDAO.removeUser(index);
        }
        public void deleteEvent(String index) throws SQLException {
                EventDAO.removeEvent(index);
        }

}
