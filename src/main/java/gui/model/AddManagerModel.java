package gui.model;

import be.Customer;
import be.Event;
import be.User;
import dal.dao.CustomerDAO;
import dal.dao.EventDAO;
import dal.dao.UserDAO;
import gui.controller.MainViewController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public class AddManagerModel {

    UserDAO userDAO = new UserDAO();
    CustomerDAO customerDAO = new CustomerDAO();


    public boolean addManager(String username, String password, String type) throws SQLException {
        List<User> users = this.userDAO.getAllUsers();
        boolean token = false;

        for (User u : users) {
            if (u.getUsername().equals(username)) {
                token = true;
            }
        }

        if (!token) {
            if (getAllUsers().size() != 0) {
                User user = new User(userDAO.getAllUsers()
                        .get(userDAO.getAllUsers().size() - 1).getId() + 1, username, password, type);
                this.userDAO.addNewUser(user);
            }else {
                User user = new User(1, username, password, type);
                this.userDAO.addNewUser(user);

            }
            return token;
        } else {
            return token;
        }
    }
    public boolean addCustomer(String name, String email) throws SQLException {
        List<Customer> users = CustomerDAO.getAllCustomers();
        boolean token = false;

        for (Customer c : users) {
            if (c.getName().equals(name)) {
                token = true;
            }
        }

        if (!token) {

            if (getAllCustomers().size() == 0) {
                Customer customer = new Customer(1 , name, email);
                CustomerDAO.addNewCustomer(customer);


            }else {
                Customer customer = new Customer(getAllCustomers()
                        .get(getAllCustomers().size() - 1).getId() + 1, name, email);
                CustomerDAO.addNewCustomer(customer);

            }


            return token;
        } else {
            return token;
        }



    }

    public ObservableList<User> getAllUsers() {
        return UserDAO.getAllUsers();
    }

    public ObservableList<Customer> getAllCustomers() {
        return CustomerDAO.getAllCustomers();
    }
    /*
    /// TODO - Implement it in SetManagerModel
    public ObservableList<String> getEventsName() {
        ObservableList<Event> events = EventDAO.getAllEvents();
        ObservableList<String> eventsNames = FXCollections.observableArrayList();
        for (Event e : events) {
            eventsNames.add(e.getName());
        }
        return eventsNames;
    }
     */
}
