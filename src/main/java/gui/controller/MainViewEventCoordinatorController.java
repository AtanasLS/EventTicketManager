package gui.controller;

import be.Customer;
import be.Event;
import be.User;
import gui.model.AddManagerModel;
import gui.model.CreateEventModel;
import gui.model.MainViewModel;
import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Date;
import java.util.ResourceBundle;

public class MainViewEventCoordinatorController implements Initializable {

    @FXML
    public Label nameLabel;
    public TableView<Event> eventTable;
    public MFXButton changeBtn;
    public Label userNameLbl;

    @FXML
    private TableColumn<Event, String> eventNameColumn, typeColumn, locationColumns;
    @FXML TableView<Customer> customersView;
    @FXML TableColumn<Customer, String> customersName, customersEmail;
    @FXML
    private TableColumn<Event, Date> startDateColumn, endDateColumn;
    private MainViewModel model;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        /*
            model.loadFromDB();
            setEventTable();
            setCustomersTableView();

         */
    }

    public void setMainModel(MainViewModel mvm){
        this.model = mvm ;
         // model.loadFromDB();
        setCustomersTableView();
        setEventTable();
        nameLabel.setText("Welcome "+model.getLoggedInUser().getUsername() + "! Position: " + model.getLoggedInUser().getType());
    }

    public void setEventTable() {
        eventNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        typeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
        startDateColumn.setCellValueFactory(new PropertyValueFactory<>("startDate"));
        endDateColumn.setCellValueFactory(new PropertyValueFactory<>("endDate"));
        locationColumns.setCellValueFactory(new PropertyValueFactory<>("location"));
        System.out.println(model.getLoggedInUser().getUsername());
        if (model.getLoggedInUser().getType().equals("Event Coordinator")) {
            eventTable.setItems(model.getAllUserToEventsName(model.getLoggedInUser()));
        }else if (model.getLoggedInUser().getType().equals("Admin")){
            eventTable.setItems(model.getAllEvents());
        }
    }
    public void setCustomersTableView(){
        customersName.setCellValueFactory(new PropertyValueFactory<>("name"));
        customersEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        customersView.setItems(model.getAllCustomers());
    }
    public void addEventHandle(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/CreateEvent.fxml"));
        Parent root = loader.load();
        CreateEventController controller = loader.getController();
        controller.setMainModel(model);
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setResizable(false);
        stage.setTitle("Create Event");
        stage.show();
    }

    public void delEventHandle(ActionEvent actionEvent) throws Exception {
        if (eventTable != null && eventTable.getSelectionModel().getSelectedItem().getName() != null) {
            String index = eventTable.getSelectionModel().getSelectedItem().getName();
            int eventId = eventTable.getSelectionModel().getSelectedItem().getId();
            model.deleteEvent(eventId,index);
            setEventTable();
        }
    }

    public void handleCreateTicket(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/GenerateTicketView.fxml"));
        Parent root = loader.load();
        GenerateTicketController controller = loader.getController();
        controller.setMainModel(model);
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setResizable(false);
        stage.setTitle("Create Ticket");
        stage.show();
    }

    public void handleCreateCustomer(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/AddCustomer.fxml"));
        Parent root = loader.load();
        AddManagerController controller = loader.getController();
        controller.setMainModel(model);
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setResizable(false);
        stage.setTitle("Create Event");
        stage.show();
    }

    public void handleLogOut(ActionEvent actionEvent) throws IOException {
        ((Node) ((Button) actionEvent.getSource())).getScene().getWindow().hide();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/LoginPageView.fxml"));
        Parent root = loader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setResizable(false);
        stage.setTitle("Please Log In");

        stage.show();
    }
    public void handleRefreshTables(ActionEvent actionEvent) {
        setCustomersTableView();
        setEventTable();
    }

    public void handleChangeView(ActionEvent actionEvent) throws IOException {

        ((Node) ((Button) actionEvent.getSource())).getScene().getWindow().hide();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/MainView.fxml"));
        Parent root = loader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setResizable(false);
        stage.show();
        MainViewController controller = loader.getController();
        controller.setMainModel(model);
        controller.nameLabel.setText("Welcome back " + model.getLoggedInUser().getUsername());
    }
}
