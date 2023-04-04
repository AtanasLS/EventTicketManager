package gui.model;

import be.Customer;
import be.Event;
import be.User;
import be.UserEvent;
import dal.dao.*;
import gui.controller.MainViewController;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;

import java.sql.SQLException;
import java.util.List;
import java.util.NoSuchElementException;

public class MainViewModel {

        private User loggedInUser ;

        private final ObservableList<User> allUsers;
        private final ObservableList<User> allManagers;
        private final ObservableList<Event> allEvents;
        private final ObservableList<Customer> allCustomers;
        private final ObservableList<UserEvent> allUserEvents;
        private final ObservableList<Event> userToEventsNames;

        public MainViewModel(){
        this.allUsers = FXCollections.observableArrayList();
        this.allEvents = FXCollections.observableArrayList();
        this.allManagers = FXCollections.observableArrayList();
        this.allCustomers = FXCollections.observableArrayList();
        this.allUserEvents = FXCollections.observableArrayList();
        this.userToEventsNames = FXCollections.observableArrayList();
        this.loggedInUser = null;
        }

        public void loadFromDB(){
        UserDAO userDAO = new UserDAO();
        this.allUsers.clear();
        this.allUsers.addAll(userDAO.getAllUsers());
        EventDAO eventDAO = new EventDAO();
        this.allEvents.clear();
        this.allEvents.addAll(eventDAO.getAllEvents());
        UserToEventDAO userToEventDAO = new UserToEventDAO();
        this.allUserEvents.clear();
        this.allUserEvents.addAll(userToEventDAO. getUserToEvent());
        CustomerDAO customerDAO = new CustomerDAO();
        this.allCustomers.clear();
        this.allCustomers.addAll(customerDAO.getAllCustomers());
        }

        public ObservableList<User> getAllUsers(){
        return allUsers;
        }
        public ObservableList<UserEvent> getAllUserEvents(){return allUserEvents;}

    public void setAllManagers(ObservableList<User> allUsers){
            this.allManagers.clear();
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


            for (Event e: getAllEvents()) {
                for (UserEvent uToE: getAllUserEvents()) {
                    if (selectedUser.getId() == uToE.getUserId() && e.getId() == uToE.getEventId()){
                        this.userToEventsNames.add(e);
                    }
                }
            }
        return this.userToEventsNames;
        }
        public void addEvent(Event eventToAdd){
            allEvents.add(eventToAdd);
        }
        public void addUserEvent(UserEvent newUserEvent){
            allUserEvents.add(newUserEvent);
            getAllUserEvents();
        }

         public ObservableList<Event> getAllEvents(){return    allEvents;}

        //deletes an Event from the table and from the userEvent table bc if there is no event there is
        // no user assigned to that event
        public void deleteEvent(int eventId,String index) throws SQLException {
            TicketDAO.removeTicketWithThisEvent(eventId);
                UserToEventDAO.deleteEventForAllUsers(eventId);
                EventDAO.removeEvent(index);
                Event eventToRemove= null;
            for (Event e:getAllEvents()) {
                if (e.getName().equals(index)){
                   eventToRemove = e;
                }
            }
             allEvents.remove(eventToRemove);
            UserEvent userEventToRemove = null;
            for (UserEvent userEvent : getAllUserEvents()){
                if (userEvent.getEventId() == eventId){
                    userEventToRemove = userEvent;
                }
            }
            allUserEvents.remove(userEventToRemove);
        }
        public ObservableList<Customer> getAllCustomers(){
        return allCustomers;
    }

        public void addCustomer(Customer customerToAdd){
            allCustomers.add(customerToAdd);
        }

        //Methods for the login page
        public boolean checkIfUserExist(String username, String password){
        List<User> users = this.getAllUsers();
        for (User u: users ) {
            if (u.getUsername().equals(username) && u.getPassword().equals(password)){
                loggedInUser = u;
                return true;
            }
        }

        return false;
    }
        public User getLoggedInUser(){
            return loggedInUser;
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

         /*   idk its use -
         TODO - maybe delete later if there is no use for it ???
        public User getUser(String username){
        List<User> allUsers = UserDAO.getAllUsers();
        for (User u:allUsers) {
            if (u.getUsername().equals(username)){
                return u;
            }
        }
        return null;
    }
     */
}
