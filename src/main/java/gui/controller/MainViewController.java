package gui.controller;

import be.Event;
import be.User;
import gui.model.MainViewModel;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import javax.naming.Name;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Date;
import java.util.ResourceBundle;

public class MainViewController implements Initializable {
    @FXML
    public Label nameLabel;
    @FXML
    public TableView <Event> eventTable;
    @FXML
    public TableView <User> managerTable;
    @FXML
    private TableColumn<Event, String> eventNameColumn, typeColumn, locationColumns;
    @FXML
    private TableColumn<Event, Date> startDateColumn, endDateColumn;
    @FXML
    private TableColumn<User, String> managerNameColumn;

    private MainViewModel model ;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        model = new MainViewModel();
        setEventTable();
        setManagerTable();

    }
    public void setEventTable(){
        eventNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        typeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
        startDateColumn.setCellValueFactory(new PropertyValueFactory<>("startDate"));
        endDateColumn.setCellValueFactory(new PropertyValueFactory<>("endDate"));
        locationColumns.setCellValueFactory(new PropertyValueFactory<>("location"));
        eventTable.setItems(model.getAllEvents());
    }
    public void setManagerTable(){

        managerNameColumn.setCellValueFactory(new PropertyValueFactory<>("username"));
        managerTable.setItems(model.getAllManagers());
    }

    public void addManagerHandle(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/AddManagerView.fxml"));
        Parent root = loader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setResizable(false);
        stage.setTitle("Add Manager");
        stage.show();
    }

    public void setManagerHandle(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/SetManagerView.fxml"));
        Parent root = loader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setResizable(false);
        stage.setTitle("Add Manager");
        stage.show();
    }
    public void addEventHandle(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/CreateEvent.fxml"));
        Parent root = loader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setResizable(false);
        stage.setTitle("Create Event");
        stage.show();
    }
    public void generateTIcketHandle(ActionEvent actionEvent) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/GenerateTicketView.fxml"));
        Parent root = loader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setResizable(false);
        stage.setTitle("Generate Ticket");
        stage.show();
    }

    public void delManagerHandle(ActionEvent actionEvent) throws SQLException {
        if (managerTable != null) {
            String index = managerTable.getSelectionModel().getSelectedItem().getUsername();
            model.deleteUser(index);
            setManagerTable();
        }

    }

    public void delEventHandle(ActionEvent actionEvent) throws SQLException {
        if (eventTable != null) {
            String index = eventTable.getSelectionModel().getSelectedItem().getName();
            model.deleteEvent(index);
            setEventTable();
        }

    }
    }

