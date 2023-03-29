package gui.model;

import be.Customer;
import be.Event;
import be.User;
import dal.dao.CustomerDAO;
import dal.dao.EventDAO;
import dal.dao.UserDAO;
import dal.dao.UserToEventDAO;
import gui.controller.MainViewController;
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

         MainViewController controller=new MainViewController();



    public ObservableList<User> getAllUsers(){
       return userDAO.getAllUsers();
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




}
