package gui.controller;

import gui.model.AddManagerModel;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class AddManagerController implements Initializable {


    @FXML
    public MFXTextField usernameField,passwordField;

    @FXML
    public MFXButton addBtn,closeB;

    private AddManagerModel model;
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        model=new AddManagerModel();
    }


    public void addManager(ActionEvent actionEvent) throws SQLException {

        if (model.addManager(usernameField.getText(),passwordField.getText(),"manager")){
            Alert alert = new Alert(Alert.AlertType.ERROR, "Wrong username or password");
            alert.showAndWait();
        }else {
            Stage stage = (Stage) addBtn.getScene().getWindow();
            stage.close();
        }



}

    public void close(){

        Stage stage = (Stage) closeB.getScene().getWindow();
        stage.close();
    }

}
