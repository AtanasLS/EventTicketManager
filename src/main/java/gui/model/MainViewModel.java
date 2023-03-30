package gui.model;

import be.Customer;
import be.Event;
import be.User;
import be.UserEvent;
import dal.dao.CustomerDAO;
import dal.dao.EventDAO;
import dal.dao.UserDAO;
import dal.dao.UserToEventDAO;
import gui.controller.MainViewController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.SQLException;
import java.util.List;
import java.util.NoSuchElementException;

public class MainViewModel {
         UserDAO userDAO = new UserDAO();
         EventDAO eventDAO = new EventDAO();
         UserToEventDAO userToEventDAO = new UserToEventDAO();
         LoginPageModel model = new LoginPageModel();
         CustomerDAO customerDAO = new CustomerDAO();

         MainViewController controller=new MainViewController();



    public ObservableList<User> getAllUsers(){
       return userDAO.getAllUsers();
    }
    public ObservableList<UserEvent> getAllUserEvents(){
        return userToEventDAO.getUserToEvent();
    }

    public ObservableList<Event> getAllUserToEventsName(User selectedUser){
        ObservableList<Event> userToEventsNames = FXCollections.observableArrayList();

            for (Event e: getAllEvents()) {
                for (UserEvent uToE: getAllUserEvents()) {
                    if (selectedUser.getId() == uToE.getUserId() && e.getId() == uToE.getEventId()){

                        userToEventsNames.add(e);
                    }
                }
            }
        return userToEventsNames;
    }
        public ObservableList<User> getAllManagers(){
            ObservableList<User>  managers = FXCollections.observableArrayList();
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
        return    eventDAO.getAllEvents();
        }
        public void deleteUser(String index) throws SQLException {
                userDAO.removeUser(index);
        }
        public void deleteEvent(int eventId,String index) throws SQLException {
                userToEventDAO.deleteEventForAllUsers(eventId);
                eventDAO.removeEvent(index);
        }
        public ObservableList<Customer> getAllCustomers(){
        return customerDAO.getAllCustomers();
    }
    public User getUser(String username){
        List<User> allUsers = UserDAO.getAllUsers();
        for (User u:allUsers) {
            if (u.getUsername().equals(username)){
                return u;
            }
        }
        return null;
    }




}
