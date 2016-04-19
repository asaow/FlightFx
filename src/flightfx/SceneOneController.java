/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flightfx;

import flightfx.model.Airport;
import static flightfx.model.Booking.BUSINESS_TYPE;
import static flightfx.model.Booking.ECONOMY_TYPE;
import static flightfx.model.Booking.FIRST_CLASS_TYPE;
import flightfx.model.Flight;
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
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;

/**
 * FXML Controller class
 *
 * @author Loki
 */
public class SceneOneController implements Initializable {

    ObservableList<String> nbrOfPassengersList;
    ObservableList<Airport> fromAirportList;
    ObservableList<Airport> toAirportList;
    public static String from;
    public static String to;
    public static LocalDate date1;
    public static int nbrOfPassengers;
    public static String ticketType;

    @FXML
    ComboBox<Airport> fromAirportComboBox;

    @FXML
    ComboBox<Airport> toAirportComboBox;

    @FXML
    private ComboBox nbrOfPassengersComboBox;
    @FXML
    DatePicker datePicker1;
    @FXML
    private DatePicker datePicker2;
    @FXML
    private Button searchButton;
    @FXML
    private RadioButton oneWayRadioButton;
    @FXML
    private RadioButton roundTripRadioButton;
    @FXML
    private RadioButton economyRadioButton;
    @FXML
    private RadioButton businessRadioButton;
    @FXML
    private RadioButton firstClassRadioButton;

    @FXML
    public void searchButtonAction(ActionEvent event) throws IOException {
        from = fromAirportComboBox.getSelectionModel().getSelectedItem().toString().substring(0, 3);
        to = toAirportComboBox.getSelectionModel().getSelectedItem().toString().substring(0, 3);
        date1 = datePicker1.getValue();
        nbrOfPassengers = nbrOfPassengersComboBox.getSelectionModel().getSelectedIndex() + 1;

        if (firstClassRadioButton.isSelected()) {
            ticketType = FIRST_CLASS_TYPE;
        } else if (businessRadioButton.isSelected()) {
            ticketType = BUSINESS_TYPE;
        } else {
            ticketType = ECONOMY_TYPE;
        }

//        SceneTwoController.setFromCombo(from);
//        SceneTwoController.setToCombo(to);
//        SceneTwoController.setDateOneWay(date1);
        Parent root = FXMLLoader.load(getClass().getResource("SceneTwo.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
        stage.setScene(scene);
        stage.show();

//        Flight f = new Flight();
//        f.setFromAirport(from);
//        f.setToAirport(to);
//        
//        Flight flight; 
//        flight = FlightFx.client.target("http://localhost:8080/FlightServer/webresources/flights")
//                .request()
//                .post(Entity.entity(f, MediaType.APPLICATION_JSON),Flight.class);
//                
        //test
//        from = fromAirportComboBox.getSelectionModel().getSelectedItem().toString().substring(0, 3);
//        to = toAirportComboBox.getSelectionModel().getSelectedItem().toString().substring(0, 3);
//        System.out.println("from airport "+ from);
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        fromAirportList = FXCollections.observableArrayList();
        toAirportList = FXCollections.observableArrayList();

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
            a.getCode();
            a.getName();
            a.getCity();

            fromAirportList.add(a);
            toAirportList.add(a);
        }

        fromAirportComboBox.setItems(fromAirportList);
        toAirportComboBox.setItems(toAirportList);

        nbrOfPassengersList = FXCollections.observableArrayList(
                "1",
                "2",
                "3",
                "4",
                "5",
                "6");

        nbrOfPassengersComboBox.setItems(nbrOfPassengersList);
        nbrOfPassengersComboBox.setValue(nbrOfPassengersList.get(0));

        //Group för RadioButtons
        ToggleGroup radioGroup = new ToggleGroup();
        oneWayRadioButton.setToggleGroup(radioGroup);
        roundTripRadioButton.setToggleGroup(radioGroup);

        datePicker2.setDisable(true);

        datePicker1.setValue(LocalDate.now());
        datePicker2.setValue(LocalDate.now());
        
        oneWayRadioButton.setOnAction((event) -> {
            datePicker2.setDisable(true);
        });

        roundTripRadioButton.setOnAction((event) -> {
            datePicker2.setDisable(false);
        });

        ToggleGroup typeGroup = new ToggleGroup();
        economyRadioButton.setToggleGroup(typeGroup);
        businessRadioButton.setToggleGroup(typeGroup);
        firstClassRadioButton.setToggleGroup(typeGroup);

        //Test för att printa ut valt datum
        datePicker1.setOnAction(event -> {
            date1 = datePicker1.getValue();
            System.out.println("Selected date from sceneOne: " + date1);
        });

    }

}
