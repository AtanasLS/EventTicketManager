package bll;

import be.*;
import dal.DataManagerFacade;
import dal.interfaces.IDataManager;
import javafx.collections.ObservableList;

public class AppLogicManager {
    private IDataManager dataManager;

    public AppLogicManager(){
        this.dataManager = DataManagerFacade.getInstance();
    }
    public ObservableList<Customer> getAllCustomers() throws Exception{
        try {
            return dataManager.getAllCustomers();
        }catch (Exception exception){
            throw new Exception("Can't get all customers! Check the connection!");
        }
    }
    public void addNewCustomer(Customer newCustomer) throws Exception{
        try {
            dataManager.addNewCustomer(newCustomer);
        }catch (Exception exception){
            throw new Exception("Could not add new customer! Check the connection or the query");
        }
    }
    public void removeCustomer(String index) throws Exception{
        try {
            dataManager.removeCustomer(index);
        }catch (Exception exception){
            throw new Exception("Could not remove this customer! Check the connection or the query!");
        }
    }
    public ObservableList<Event> getAllEvents() throws Exception{
        try {
            return dataManager.getAllEvents();
        }catch (Exception exception){
            throw new Exception("Can't read all the events. Check the connection");
        }
    }
    public void addNewEvent(Event newEvent) throws Exception{
        try {
            dataManager.addNewEvent(newEvent);
        }catch (Exception exception){
            throw new Exception("Could not create a new event! Check the connection or the query!");
        }
    }
    public void removeEvent(String index) throws Exception{
        try {
            dataManager.removeEvent(index);
        }catch (Exception exception){
            throw new Exception("Could not remove this event! Check the connection or the query!");
        }
    }
    public ObservableList<User> getAllUsers() throws Exception{
        try {
            return dataManager.getAllUsers();
        }catch (Exception exception){
            throw new Exception("Can't read all users. Check the connection!");
        }
    }
    public void addNewUser(User newUser) throws Exception{
        try {
            dataManager.addNewUser(newUser);
        }catch (Exception exception){
            throw new Exception("Could not create a new event! Check the connection or the query!");
        }
    }
    public void removeUser(String index) throws Exception{
        try {
            dataManager.removeUser(index);
        }catch (Exception exception){
            throw new Exception("Could not remove this user! Check the connection or the query!");
        }
    }
    public ObservableList<Ticket> getAllTickets() throws Exception{
        try {
          return dataManager.getAllTickets();
        }catch (Exception exception){
            throw new Exception("Can't read all tickets. Check the connection or the query!");
        }
    }
    public void addNewTicket(Ticket newTicket) throws Exception{
        try {
            dataManager.addNewTicket(newTicket);
        }catch (Exception exception){
            throw new Exception("Could not add new ticket! Check the connection or the query!");
        }
    }
    public void removeTicket(int customerId, int eventId) throws Exception{
        try {
            dataManager.removeTicket(customerId, eventId);
        }catch (Exception exception){
            throw new Exception("Could not remove this ticket! Check the connection or the query!");
        }
    }
    public void removeTicketWithThisEvent(int eventID) throws Exception{
        try {
            dataManager.removeTicketWithThisEvent(eventID);
        }catch (Exception exception){
            throw new Exception("Could not remove this ticket! Check the connection or the query!");
        }
    }
    public ObservableList<UserEvent> getAllUserEvents() throws Exception{
        try {
            return dataManager.getUserToEvent();
        }catch (Exception exception){
            throw new Exception("Can't get all userEvents! Check the connection or the query!");
        }
    }
    public void addNewUserEvent(UserEvent newUserEvent) throws Exception{
        try {
            dataManager.addNewUserEvent(newUserEvent);
        }catch (Exception exception){
            throw new Exception("Could not add new userEvent! Check the connection or the query!");
        }
    }
    public void removeUserEvent(int eventId, int userId) throws Exception{
        try {
            dataManager.removeEvent(eventId, userId);
        }catch (Exception exception){
            throw new Exception("Could not remove this event! Check the connection or the query!");
        }
    }
    public void removeEventForAllUsers(int eventId) throws Exception{
        try {
            dataManager.deleteEventForAllUsers(eventId);
        }catch (Exception exception){
            throw new Exception("Could not remove this event for all users! Check the connection or the query!");
        }
    }
}
