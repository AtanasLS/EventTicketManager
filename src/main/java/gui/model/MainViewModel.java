package gui.model;

import be.Customer;
import be.Event;
import be.User;
import dal.dao.CustomerDAO;
import dal.dao.EventDAO;
import dal.dao.UserDAO;
import dal.dao.UserToEventDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.SQLException;
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
             for (User u:
                     allUsers) {
                 if (u.getType().equals("Event Coordinator")){
                     allManagers.add(u);
                 }
             }

         }

    public ObservableList<User> getAllUsers(){
       return allUsers;
    }

        public ObservableList<User> getAllManagers(){
            loadFromDB();
            for (User u:
                    allUsers) {
                if (u.getType().equals("Event Coordinator")){
                    allManagers.add(u);
                }
            }
            return allManagers;
         }
         public void loadFromDB(){
             UserDAO userDAO = new UserDAO();
             this.allUsers.clear();
             this.allUsers.addAll(UserDAO.getAllUsers());
         }
         public ObservableList<Event> getAllEvents(){
        return    EventDAO.getAllEvents();
        }
        public void deleteUser(String index) throws SQLException {
                UserDAO.removeUser(index);
                loadFromDB();
        }
        public void deleteEvent(int eventId,String index) throws SQLException {
                UserToEventDAO.deleteEventForAllUsers(eventId);
                EventDAO.removeEvent(index);
        }
        public ObservableList<Customer> getAllCustomers(){
        return CustomerDAO.getAllCustomers();
    }

}
