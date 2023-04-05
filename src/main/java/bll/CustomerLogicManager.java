package bll;

import be.Customer;
import dal.DataManagerFacade;
import dal.interfaces.IDataManager;
import javafx.collections.ObservableList;

public class CustomerLogicManager {
    private IDataManager dataManager;

    public CustomerLogicManager(){
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
}
