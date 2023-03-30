package gui.controller;

import be.Customer;
import be.Event;
import be.User;
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
    @FXML
    private TableColumn<Event, String> eventNameColumn, typeColumn, locationColumns;
    @FXML TableView<Customer> customersView;
    @FXML TableColumn<Customer, String> customersName, customersEmail;
    @FXML
    private TableColumn<Event, Date> startDateColumn, endDateColumn;

    private MainViewModel model = new MainViewModel();


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setEventTable();
        System.out.println(model.getAllCustomers().size());
        setCustomersTableView();
    }

    public void setLoggedInUser(String userName, String type){
        if (type.equals("Event Coordinator")) {
            nameLabel.setText("Welcome " + userName + "! Position: " + type);
            changeBtn.setVisible(false);
        }else {
            nameLabel.setText("Now you are logged in as an Event Coordinator!");
            changeBtn.setVisible(true);
        }
    }



    public void setEventTable() {
        eventNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        typeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
        startDateColumn.setCellValueFactory(new PropertyValueFactory<>("startDate"));
        endDateColumn.setCellValueFactory(new PropertyValueFactory<>("endDate"));
        locationColumns.setCellValueFactory(new PropertyValueFactory<>("location"));
        eventTable.setItems(model.getAllEvents());
    }
    public void setCustomersTableView(){
        customersName.setCellValueFactory(new PropertyValueFactory<>("name"));
        customersEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        customersView.setItems(model.getAllCustomers());
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

    public void delEventHandle(ActionEvent actionEvent) throws SQLException {
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
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setResizable(false);
        stage.setTitle("Create Ticket");
        stage.show();
    }

    public void handleCreateCustomer(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/AddCustomer.fxml"));
        Parent root = loader.load();
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
        User admin = null;
        for (User u: model.getAllUsers()) {
            if (u.getType().equals("Admin")){
                admin = u;
            }
        }
        assert admin != null;
        controller.setLoggedInUser(admin.getUsername(), admin.getType());

    }
}
