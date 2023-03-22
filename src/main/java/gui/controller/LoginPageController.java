package gui.controller;

import dal.dao.UserDAO;
import gui.model.LoginPageModel;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginPageController implements Initializable {

    @FXML
    public MFXTextField usernameField, passwordField;

    private LoginPageModel model;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
         model = new LoginPageModel();
    }


    public void logIn(ActionEvent actionEvent) {
        if (model.checkIfUserExist(usernameField.getText(), passwordField.getText())){
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/MainView.fxml"));
                Parent root = loader.load();
                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.setFullScreen(false);

                stage.setResizable(false);
                stage.setTitle("Event Ticket Manager Beta");
                stage.show();

                ((Node)((Button)actionEvent.getSource())).getScene().getWindow().hide();

            } catch (IOException e) {
                e.printStackTrace();
                Alert alert = new Alert(Alert.AlertType.ERROR, "Could not load App.fxml");
                alert.showAndWait();
            }

        }
        else{
            Alert alert = new Alert(Alert.AlertType.ERROR, "Wrong username or password");
            alert.showAndWait();
        }
        }

}
