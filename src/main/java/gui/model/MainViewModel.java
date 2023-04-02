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

         EventDAO eventDAO = new EventDAO();
         UserToEventDAO userToEventDAO = new UserToEventDAO();
         LoginPageModel model = new LoginPageModel();
         CustomerDAO customerDAO = new CustomerDAO();

         MainViewController controller=new MainViewController();

        private final ObservableList<User> allUsers;
        private final ObservableList<User> allManagers;

        public MainViewModel(){
        this.allUsers = FXCollections.observableArrayList();
        this.allManagers = FXCollections.observableArrayList();
        }

        public void loadFromDB(){
        UserDAO userDAO = new UserDAO();
        this.allUsers.clear();
        this.allUsers.addAll(UserDAO.getAllUsers());
          }
        public ObservableList<User> getAllUsers(){
        return allUsers;
        }
    public ObservableList<UserEvent> getAllUserEvents(){
        return userToEventDAO.getUserToEvent();
    }

    public void setAllManagers(ObservableList<User> allUsers){
        for (User u:
                allUsers) {
            if (u.getType().equals("Event Coordinator")){
                allManagers.add(u);
            }
        }
    }
    public ObservableList<User> getAllManagers(){return allManagers;}

    public void deleteUser(String index) throws SQLException {
        UserDAO.removeUser(index);
        User userToRemoveFromAllUsers = null;
        for (User u:getAllManagers()) {
            if (u.getUsername().equals(index)){
                userToRemoveFromAllUsers = u;
            }
        }
        this.getAllManagers().remove(userToRemoveFromAllUsers);
    }
        public void addUser(User userToAdd){
        this.allUsers.add(userToAdd);
        this.allManagers.add(userToAdd);

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

         public ObservableList<Event> getAllEvents(){
        return    eventDAO.getAllEvents();

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
