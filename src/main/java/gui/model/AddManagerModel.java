package gui.model;

import be.Customer;
import be.Event;
import be.User;
import bll.AppLogicManager;
import dal.dao.CustomerDAO;
import dal.dao.EventDAO;
import dal.dao.UserDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public class AddManagerModel {

    MainViewModel mainViewModel;
    private AppLogicManager appLogicManager;

    public AddManagerModel(MainViewModel mainViewModel) {
        this.appLogicManager = new AppLogicManager();
        this.mainViewModel = mainViewModel;
    }




    public boolean addManager(String username, String password, String type) throws Exception {

        List<User> users = mainViewModel.getAllUsers();

        boolean token = false;
        for (User u : users) {
            if (u.getUsername().equals(username)) {
                token = true;
            }
        }
        if (!token) {
            if (mainViewModel.getAllUsers().size() != 0) {
                User user = new User(mainViewModel.getAllUsers()
                        .get(mainViewModel.getAllUsers().size() - 1).getId() + 1, username, password, type);

                //this.allUsers.add(user);
                appLogicManager.addNewUser(user);
                mainViewModel.addUser(user);
            }else {
                User user = new User(1, username, password, type);

                appLogicManager.addNewUser(user);
                mainViewModel.addUser(user);
            }
            return token;
        } else {
            return token;
        }
    }
    public boolean addCustomer(String name, String email) throws SQLException {
        List<Customer> customers = mainViewModel.getAllCustomers();
        boolean token = false;

        for (Customer c : customers) {
            if (c.getName().equals(name)) {
                token = true;
            }
        }

        if (!token) {

            if (mainViewModel.getAllCustomers().size() == 0) {
                Customer customer = new Customer(1 , name, email);
                CustomerDAO.addNewCustomer(customer);
                mainViewModel.addCustomer(customer);

            }else {
                Customer customer = new Customer(mainViewModel.getAllCustomers()
                        .get(mainViewModel.getAllCustomers().size() - 1).getId() + 1, name, email);
                CustomerDAO.addNewCustomer(customer);
                mainViewModel.addCustomer(customer);
            }
            return token;
        } else {
            return token;
        }
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