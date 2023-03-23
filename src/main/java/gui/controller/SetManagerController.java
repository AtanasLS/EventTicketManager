package gui.controller;

import be.Event;
import be.User;
import gui.model.SetManagerModel;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXComboBox;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class SetManagerController implements Initializable {

    @FXML
    public MFXComboBox managerBox,eventBox;

    @FXML
    public MFXButton cancelBtn,addBtn;

    private SetManagerModel model;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        model=new SetManagerModel();
        managerBox.setItems(FXCollections.observableArrayList(model.getAllUsers()));
        eventBox.setItems(FXCollections.observableArrayList(model.getAllEvents()));
        

    }

    public void setBtn(ActionEvent actionEvent) throws SQLException {
        if (model.setManagerToEvent((User) managerBox.getSelectionModel().getSelectedItem(),(Event) eventBox.getSelectionModel().getSelectedItem())){
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
