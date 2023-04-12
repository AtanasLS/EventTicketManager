package gui.controller;

import be.Event;
import be.Ticket;
import gui.model.AllTicketsModel;
import gui.model.MainViewModel;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import javax.swing.text.TabableView;
import java.net.URL;
import java.util.ResourceBundle;

public class AllTicketsController implements Initializable {


    @FXML
    public TableView<Ticket> ticketTable;

    @FXML
    private TableColumn<Ticket, String > eventName, customerName;

    @FXML
    private Button closeB;

    private AllTicketsModel model;
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void setTicketModel(MainViewModel model) throws Exception {
        this.model = new AllTicketsModel() ;
        setTicketTable(this.model.loadFromDB());
    }

    public void setTicketTable(ObservableList<Ticket> tickets){
        eventName.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        customerName.setCellValueFactory(new PropertyValueFactory<>("eventId"));
        ticketTable.setItems(tickets);
    }

    public void close(){
        Stage stage = (Stage) closeB.getScene().getWindow();
        stage.close();
    }

    }
