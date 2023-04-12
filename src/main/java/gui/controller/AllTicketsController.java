package gui.controller;

import be.Customer;
import be.Event;
import be.Ticket;
import be.TicketObs;
import com.google.zxing.WriterException;
import gui.model.AllTicketsModel;
import gui.model.MainViewModel;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.print.PrinterJob;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import javax.swing.text.TabableView;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AllTicketsController implements Initializable {


    @FXML
    public TableView<TicketObs> ticketTable;

    @FXML
    private TableColumn<TicketObs, String > eventName, customerName;

    @FXML
    private Button closeB,genBtn;

    private AllTicketsModel model;

    private Event currentEvent;
    private Customer currentCustomer;
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void setTicketModel(MainViewModel model) throws Exception {
        this.model = new AllTicketsModel() ;
        setTicketTable(this.model.getTickets());
    }

    public void setTicketTable(ObservableList<TicketObs> tickets){
        eventName.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        customerName.setCellValueFactory(new PropertyValueFactory<>("eventName"));
        ticketTable.setItems(tickets);
    }

    public void showTicket(ActionEvent actionEvent) throws IOException, WriterException {
       if( ticketTable.getSelectionModel().getSelectedItem() != null) {
           Event event=model.getEvent(ticketTable.getSelectionModel().getSelectedItem().getEventName());
           Customer customer=model.getCustomer(ticketTable.getSelectionModel().getSelectedItem().getCustomerName());
           FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/TicketFXML.fxml"));
           Parent root = loader.load();
           Stage ticketStage = new Stage();
           TicketController controller = loader.getController();
           controller.setLabels(event.getName(), event.getLocation(), customer.getName(), event.getEndDate(), event.getStartDate(),customer.getEmail());
           String data = "This ticket is to this customer: " + customer.getName();
           controller.createQR(data, 100, 100);
           ticketStage.setScene(new Scene(root));
           ticketStage.setResizable(false);
           ticketStage.setTitle("TicketQR");
           ticketStage.show();

           Stage stage = (Stage) genBtn.getScene().getWindow();


           System.out.println("To Printer!");
           PrinterJob job = PrinterJob.createPrinterJob();
           if(job != null) {
               job.showPrintDialog(ticketStage);
               job.printPage(root);
               job.endJob();
           }
           stage.close();
       }
    }

    public void close(){
        Stage stage = (Stage) closeB.getScene().getWindow();
        stage.close();
    }

    }
