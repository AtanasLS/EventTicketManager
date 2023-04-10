package gui.controller;

import be.Event;
import be.User;
import com.sun.tools.javac.Main;
import gui.model.AddManagerModel;
import gui.model.CreateEventModel;
import gui.model.MainViewModel;
import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.collections.ObservableList;
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
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import javax.naming.Name;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;

public class MainViewController implements Initializable {
    @FXML
    public Label nameLabel;
    @FXML
    public TableView <Event> eventTable;
    @FXML
    public TableView <User> managerTable;
    public MFXButton refreshTablesBtn;
    @FXML
    private TableColumn<Event, String> eventNameColumn, typeColumn, locationColumns;
    @FXML
    private TableColumn<Event, Date> startDateColumn, endDateColumn;
    @FXML
    private TableColumn<User, String> managerNameColumn;

    private MainViewModel model ;

    private int stages;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    public void setMainModel(MainViewModel mvm){
        model = mvm ;
       // model.loadFromDB();

        setEventTable(model.getAllEvents());
         nameLabel.setText("Welcome "+model.getLoggedInUser().getUsername() + "! Position: " + model.getLoggedInUser().getType());
        setManagerTable();
        refreshTablesBtn.setOnAction(refreshTablesBtn.getOnAction());

    }

    public void setEventTable(ObservableList<Event> events){
        eventNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        typeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
        startDateColumn.setCellValueFactory(new PropertyValueFactory<>("startDate"));
        endDateColumn.setCellValueFactory(new PropertyValueFactory<>("endDate"));
        locationColumns.setCellValueFactory(new PropertyValueFactory<>("location"));
        eventTable.setItems(events);
    }
    public void setManagerTable(){
        managerNameColumn.setCellValueFactory(new PropertyValueFactory<>("username"));
        model.setAllManagers(model.getAllUsers());
        managerTable.setItems(model.getAllManagers());

}
    public void addManagerHandle(ActionEvent actionEvent) throws IOException, InterruptedException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/AddManagerView.fxml"));
        Parent root = loader.load();
        AddManagerController ctrl = loader.getController();
        ctrl.setMainModel(model);
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setResizable(false);
        stage.setTitle("Add Manager");
        stage.show();

    }


    public void seeAllTickets(ActionEvent actionEvent) throws IOException, InterruptedException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/AllTickets.fxml"));
        Parent root = loader.load();
        AllTicketsController ctrl = loader.getController();
        ctrl.setTicketModel(model);
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setResizable(false);
        stage.setTitle("All Tickets");
        stage.show();

    }

    public void setManagerHandle(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/SetManagerView.fxml"));
        Parent root = loader.load();
        SetManagerController controller = loader.getController();
        controller.setMainModel(model);
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setResizable(false);
        stage.setTitle("Add Manager");
        stage.show();
    }
    public void addEventHandle(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/CreateEvent.fxml"));
        Parent root = loader.load();
        CreateEventController ctrl = loader.getController();
        ctrl.setMainModel(model);
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

        if (managerTable != null && managerTable.getSelectionModel().getSelectedItem() != null) {
            String index = managerTable.getSelectionModel().getSelectedItem().getUsername();
            model.deleteUser(index);
        }
    }

    public void delEventHandle(ActionEvent actionEvent) throws SQLException {
        if (eventTable != null && eventTable.getSelectionModel().getSelectedItem().getName() != null) {
            String index = eventTable.getSelectionModel().getSelectedItem().getName();
            int eventId = eventTable.getSelectionModel().getSelectedItem().getId();
            model.deleteEvent(eventId,index);
        }

    }

    public void handleRefreshTables(ActionEvent actionEvent) {
        setManagerTable();
        setEventTable(model.getAllEvents());

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


    public void handleChangeView(ActionEvent actionEvent) throws IOException {

            ((Node) ((Button) actionEvent.getSource())).getScene().getWindow().hide();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/MainView-EventCoordinator.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setResizable(false);
            stage.setTitle("Please Log In");
            stage.show();
            MainViewEventCoordinatorController controller = loader.getController();
            controller.setMainModel(model);
            controller.userNameLbl.setText("You are now logged in as Event Coordinator!");
        }

    public void handleShowAssignedEvents(MouseEvent mouseEvent) {
        if (managerTable.getSelectionModel().getSelectedItem() != null){
            User selectedUser = managerTable.getSelectionModel().getSelectedItem();
            setEventTable(model.getAllUserToEventsName(selectedUser));
        }
    }
}

