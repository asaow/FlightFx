/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flightfx;

import flightfx.model.Airport;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;

/**
 * FXML Controller class
 *
 * @author Loki
 */
public class SceneOneController implements Initializable {

    ObservableList<String> nbrOfPassengers;
    ObservableList<Airport> airportList;

    @FXML
    private ComboBox fromAirportComboBox;

    @FXML
    private ComboBox toAirportComboBox;

    @FXML
    private ComboBox nbrOfPassengersComboBox;
    
    @FXML
    private DatePicker datePicker1;

    @FXML
    private DatePicker datePicker2;

    @FXML
    private Button searchButton;

    @FXML
    private RadioButton oneWayRadioButton;

    @FXML
    private RadioButton roundTripRadioButton;

    @FXML
    public void searchButtonAction(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("SceneTwo.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        airportList = FXCollections.observableArrayList();

        //Hämtar alla flygplatser
        GenericType<List<Airport>> airports = new GenericType<List<Airport>>() {
        };
        List<Airport> c;
        c = FlightFx.client.target("http://localhost:8080/FlightServer/webresources/airports")
                .request(MediaType.APPLICATION_JSON)
                .get(airports);

        //Loopar igenom listan för att hämta flygplatsens stad, namn och kod
        //Lägger sedan till flygplatsen i ObservableList
        for (Airport a : c) {
            a.getCity();
            a.getName();
            a.getCode();
            
            airportList.add(a);
        }
        
        fromAirportComboBox.setItems(airportList);
        toAirportComboBox.setItems(airportList);

        nbrOfPassengers = FXCollections.observableArrayList(
                "1",
                "2",
                "3",
                "4",
                "5",
                "6",
                "7",
                "8",
                "9",
                "10"
        );

        nbrOfPassengersComboBox.setItems(nbrOfPassengers);

        //Group för RadioButton
        ToggleGroup radioGroup = new ToggleGroup();
        oneWayRadioButton.setToggleGroup(radioGroup);
        roundTripRadioButton.setToggleGroup(radioGroup);

        //Test för att printa ut valt datum
        datePicker1.setOnAction(event -> {
        LocalDate date = datePicker1.getValue();
        System.out.println("Selected date: " + date);
        });
    }

}
