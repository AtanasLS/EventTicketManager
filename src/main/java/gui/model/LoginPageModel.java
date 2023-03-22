package gui.model;

import be.User;
import dal.dao.UserDAO;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class LoginPageModel  {

    UserDAO userDAO = new UserDAO();
    public ObservableList<User> getAllUsers(){
     return    userDAO.getAllUsers();
    }
    public boolean checkIfUserExist(String username, String password){
        List<User> users = this.getAllUsers();
        for (User u:
            users ) {
            if (u.getUsername().equals(username) && u.getPassword().equals(password)){
                return true;
            }
        }

        return false;
    }

}
