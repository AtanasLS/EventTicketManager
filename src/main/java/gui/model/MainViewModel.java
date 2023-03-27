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
         UserDAO userDAO = new UserDAO();
         EventDAO eventDAO = new EventDAO();
         UserToEventDAO userToEventDAO = new UserToEventDAO();
         LoginPageModel model = new LoginPageModel();
         CustomerDAO customerDAO = new CustomerDAO();



    public ObservableList<User> getAllUsers(){
       return UserDAO.getAllUsers();
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
        return    EventDAO.getAllEvents();
        }
        public void deleteUser(String index) throws SQLException {
                UserDAO.removeUser(index);
        }
        public void deleteEvent(int eventId,String index) throws SQLException {
                UserToEventDAO.deleteEventForAllUsers(eventId);
                EventDAO.removeEvent(index);
        }
        public ObservableList<Customer> getAllCustomers(){
        return CustomerDAO.getAllCustomers();
    }

}
