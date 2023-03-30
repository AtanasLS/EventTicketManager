package gui.model;

import be.Customer;
import be.Event;
import be.User;
import dal.dao.CustomerDAO;
import dal.dao.EventDAO;
import dal.dao.UserDAO;
import dal.dao.UserToEventDAO;
import javafx.beans.binding.ObjectExpression;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.SQLException;
import java.util.List;
import java.util.NoSuchElementException;

public class MainViewModel {

         EventDAO eventDAO = new EventDAO();
         UserToEventDAO userToEventDAO = new UserToEventDAO();

         CustomerDAO customerDAO = new CustomerDAO();
         private final ObservableList<User> allUsers;
         private final ObservableList<User> allManagers;

         public MainViewModel(){
             this.allUsers = FXCollections.observableArrayList();
             this.allManagers = FXCollections.observableArrayList();
         }

        public ObservableList<User> getAllUsers(){
        return allUsers;
        }
         public void loadFromDB(){
             UserDAO userDAO = new UserDAO();
             this.allUsers.clear();
             this.allUsers.addAll(UserDAO.getAllUsers());
         }
         public ObservableList<Event> getAllEvents(){
         return    EventDAO.getAllEvents();
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
             this.allManagers.add(userToAdd);

        }
        public void deleteEvent(int eventId,String index) throws SQLException {
                UserToEventDAO.deleteEventForAllUsers(eventId);
                EventDAO.removeEvent(index);
        }
        public ObservableList<Customer> getAllCustomers(){
        return CustomerDAO.getAllCustomers();
    }


}
