package gui.model;

import be.Event;
import be.User;
import dal.dao.UserDAO;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public class AddManagerModel {

    UserDAO userDAO=new UserDAO();

    public boolean addManager(String username, String password, String type) throws SQLException {
        List<User> users= this.userDAO.getAllUsers();
        boolean token=false;

        for (User u:users) {
            if (u.getUsername().equals(username)){
                token=true;
            }
        }

        if (!token) {
            User user = new User(users.size() + 1, username, password, type);

            this.userDAO.addNewUser(user);
            return token;
        }else {
            return token;
        }
    }
}
