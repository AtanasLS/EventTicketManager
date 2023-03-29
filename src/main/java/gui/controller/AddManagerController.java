package gui.controller;

import gui.model.AddManagerModel;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXComboBox;
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
    public MFXTextField usernameField,passwordField, cutomersNameField, customersEmailField;

    @FXML
    public MFXButton addBtn,closeB;
    @FXML
    public MFXComboBox eventsBox;

    private AddManagerModel model = new AddManagerModel();
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }


    public void addManager(ActionEvent actionEvent) throws SQLException {
        model.loadFromDB();
        if (model.addManager(usernameField.getText(),passwordField.getText(),"Event Coordinator")){
            Alert alert = new Alert(Alert.AlertType.ERROR, "This name or password already exists!");
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

    public void handleAddCustomer(ActionEvent actionEvent) throws SQLException {
        if (model.addCustomer(cutomersNameField.getText(),customersEmailField.getText())){
            Alert alert = new Alert(Alert.AlertType.ERROR, "This name or email already exist");
            alert.showAndWait();
        }else {
            Stage stage = (Stage) addBtn.getScene().getWindow();
            stage.close();
        }

    }


}
