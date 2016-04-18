/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flightfx;

import static flightfx.SceneTwoController.getDateOneWay;
import static flightfx.SceneTwoController.getFromCombo;
import static flightfx.SceneTwoController.getToCombo;
import flightfx.model.Flight;
import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javax.ws.rs.core.MediaType;

/**
 * FXML Controller class
 *
 * @author Loki
 */
public class SceneFourController implements Initializable {
    
    public static int getFlightId() {
        return SceneTwoController.flightId;
    }

    public static void setFlightId(int flightId) {
        SceneTwoController.flightId = flightId;
    }
    
    @FXML
    private Button backButton;
    @FXML
    private Label fromAirportLabel;
    @FXML
    private Label depDateLabel;    
    @FXML
    private Label toAirportLabel;    
    @FXML
    private Label arrDateLabel;
    @FXML
    private Label priceLabel;
    
    @FXML
    private Button confirmButton;
    
    @FXML
    public void backButtonAction(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("SceneThree.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
    }
    
    @FXML
    public void cancelButtonAction(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("SceneOne.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
    }
    
        @FXML
    public void confirmButtonAction(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("SceneFive.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
    }
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        String id = String.valueOf(getFlightId());
        Flight c;
        c = FlightFx.client.target("http://localhost:8080/FlightServer/webresources/flights")
                .path(id)
                .request(MediaType.APPLICATION_JSON)
                .get(Flight.class);
        System.out.println(c.getAirline());
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String depdate = df.format(c.getDepDate().getTime());
        String arrdate = df.format(c.getArrDate().getTime());

        fromAirportLabel.setText("Flygplats: " + c.getFromAirportCode() + " " + c.getFromAirport());
        depDateLabel.setText("Avgång: " + depdate);
        toAirportLabel.setText("Flygplats: "+ c.getToAirportCode() + " " + c.getToAirport());
        arrDateLabel.setText("Ankomst: " + arrdate);
        
        
        System.out.println("SceneFour flight id: " + getFlightId());
        
        
    }    
    
}
