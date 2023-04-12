package gui.controller;


import be.Customer;
import be.Event;
import be.User;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeReader;
import com.google.zxing.qrcode.QRCodeWriter;
import gui.model.AddManagerModel;
import gui.model.AddNewTicketModel;
import gui.model.MainViewModel;
import io.github.palexdev.materialfx.controls.MFXComboBox;
import javafx.beans.binding.ObjectExpression;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.print.PrinterJob;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GenerateTicketController implements Initializable {
    @FXML
    private Button genBtn;

    public MFXComboBox eventsComboBox;
    public MFXComboBox customerComboBox;

    private AddNewTicketModel model;
    private ObservableList<Customer> customers;
    private ObservableList<Event> events;

    private ObservableList<String> allCustomersNames = FXCollections.observableArrayList();
    private ObservableList<String> allEventsName = FXCollections.observableArrayList();




    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void setMainModel(MainViewModel mvm){
        model = new AddNewTicketModel(mvm);
        this.customers=mvm.getAllCustomers();
        this.events = mvm.getAllEvents();

        for (Customer customer:customers) {
            this.allCustomersNames.add(customer.getName());
        }

        for (Event e:events) {
            this.allEventsName.add(e.getName());
            System.out.println(e.getName());
        }
        eventsComboBox.setItems( allEventsName);
        customerComboBox.setItems( allCustomersNames);
    }



    public void handleGenerateButton(ActionEvent actionEvent) throws Exception {

        Customer customer = null;
        Event event= null;

        for (Customer c:this.customers) {
            if (c.getName().equals(customerComboBox.getSelectionModel().getSelectedItem())){
                customer = c;
            }

        }

        for (Event e:events) {
            if (e.getName().equals(eventsComboBox.getSelectionModel().getSelectedItem())){
                event=e;
            }
        }

        if (model.addTicket(customer.getId(), event.getId())){
            Alert alert = new Alert(Alert.AlertType.ERROR, "Wrong username or password");
            alert.showAndWait();
        }else {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/TicketFXML.fxml"));
            Parent root = loader.load();
            Stage ticketStage = new Stage();
            TicketController controller = loader.getController();
            System.out.println(event.getName());
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
}

