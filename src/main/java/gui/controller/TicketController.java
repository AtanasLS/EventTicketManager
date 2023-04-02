package gui.controller;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import gui.model.AddNewTicketModel;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TicketController implements Initializable {

    @FXML
    private  ImageView qrImage;
    @FXML
    private Label text;
    private AddNewTicketModel addNewTicketModel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        addNewTicketModel = new AddNewTicketModel();


        try {
            qrImage.setImage(SwingFXUtils.toFXImage(createQR(addNewTicketModel.getAllEvents().get(0).getName(),200,200), null));
        } catch (WriterException | IOException e) {
            throw new RuntimeException(e);
        }

    }

    public BufferedImage createQR(String data,  int height, int width) throws WriterException, IOException {
        QRCodeWriter qrCodeWriter = new QRCodeWriter();

        BufferedImage bufferedImage = null;
        try {
            BitMatrix byteMatrix = qrCodeWriter.encode(data, BarcodeFormat.QR_CODE, width, height);
            bufferedImage = new BufferedImage(width,height ,BufferedImage.TYPE_INT_RGB);
            bufferedImage.createGraphics();

            Graphics2D graphics = (Graphics2D) bufferedImage.getGraphics();
            graphics.setColor(Color.WHITE);
            graphics.fillRect(0, 0, width, height);
            graphics.setColor(Color.BLACK);

            for (int i = 0; i < height; i++) {
                for (int j = 0; j < width; j++) {
                    if (byteMatrix.get(i, j)) {
                        graphics.fillRect(i, j, 1, 1);
                    }
                }
            }
        }catch (WriterException ex){
            Logger.getLogger(SetManagerController.class.getName()).log(Level.SEVERE, null, ex);
        }

        //qrImage.setImage(SwingFXUtils.toFXImage(bufferedImage, null));
        return bufferedImage;

    }
}
