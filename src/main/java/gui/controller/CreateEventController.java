package gui.controller;

import gui.model.CreateEventModel;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;

public class CreateEventController implements Initializable {

    @FXML
    public MFXTextField nameField,typeField,locationField;

    @FXML
    public MFXButton createBtn,closeB;

    @FXML
    public DatePicker startDatePicker,endDatePicker;

    private CreateEventModel model;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        model=new CreateEventModel();
    }

    public void createEvent(ActionEvent actionEvent){
        model.createEvent(nameField.getText(),typeField.getText(),new Date(),new Date(),locationField.getText());

        Stage stage = (Stage) createBtn.getScene().getWindow();
        stage.close();
    }

    public void close(){

        Stage stage = (Stage) closeB.getScene().getWindow();
        stage.close();
    }
}
