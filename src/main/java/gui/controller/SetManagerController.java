package gui.controller;

import be.Event;
import be.User;
import gui.model.AddManagerModel;
import gui.model.MainViewModel;
import gui.model.SetManagerModel;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXComboBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class SetManagerController implements Initializable {

    @FXML
    public MFXComboBox managerBox,eventBox;

    @FXML
    public MFXButton cancelBtn,addBtn;

    private SetManagerModel model;

    private ObservableList<User> managers;
    private ObservableList<Event> events;

    private ObservableList<String> managersList= FXCollections.observableArrayList();
    private ObservableList<String> eventsList=FXCollections.observableArrayList();
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
    public void setMainModel(MainViewModel mvm){
        model = new SetManagerModel(mvm) ;

        this.managers=mvm.getAllManagers();
        this.events=mvm.getAllEvents();

        for (User u:managers) {
                this.managersList.add(u.getUsername());
        }

        for (Event e:events) {
            this.eventsList.add(e.getName());
            System.out.println(e.getName());
        }
        managerBox.setItems( managersList);
        eventBox.setItems( eventsList);
    }

    public void setBtn(ActionEvent actionEvent) throws Exception {
        User user= null;
        Event event= null;

        for (User u:this.managers) {
            if (u.getUsername().equals(managerBox.getSelectionModel().getSelectedItem())){
                user=u;
            }

        }
        for (Event e:events) {
            if (e.getName().equals(eventBox.getSelectionModel().getSelectedItem())){
                event=e;
            }
        }

        if (model.setManagerToEvent(user,event)){
            Alert alert = new Alert(Alert.AlertType.ERROR, "Wrong username or password");
            alert.showAndWait();
        }else {
        Stage stage = (Stage) addBtn.getScene().getWindow();
        stage.close();
        }
    }

    public void cancelBtn(ActionEvent actionEvent) {
        Stage stage = (Stage) cancelBtn.getScene().getWindow();
        stage.close();
    }
}
