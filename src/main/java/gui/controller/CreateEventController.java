package gui.controller;


import gui.model.AddManagerModel;
import gui.model.CreateEventModel;
import gui.model.MainViewModel;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.stage.Stage;
import tornadofx.control.DateTimePicker;

import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class CreateEventController implements Initializable {

    @FXML
    public MFXTextField nameField,typeField,locationField;

    @FXML
    public MFXButton createBtn,closeB;

    @FXML
    public DatePicker startDatePicker,endDatePicker;

    @FXML
    public DateTimePicker startDateTime, endDateTime;

    private CreateEventModel model;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void setMainModel(MainViewModel mvm){
        model = new CreateEventModel(mvm) ;
    }

    public void createEvent(ActionEvent actionEvent) throws Exception {
        LocalDateTime startDatetimeValue = startDateTime.getDateTimeValue();
        LocalDateTime endDateTimeValue = endDateTime.getDateTimeValue();

        model.createEvent(nameField.getText(),typeField.getText(), startDatetimeValue,endDateTimeValue,locationField.getText());
        Stage stage = (Stage) createBtn.getScene().getWindow();
        stage.close();
    }

    public void close(){
        Stage stage = (Stage) closeB.getScene().getWindow();
        stage.close();
    }
}
