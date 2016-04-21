package flightfx;

import flightfx.model.Airport;
import static flightfx.model.Booking.BUSINESS_TYPE;
import static flightfx.model.Booking.ECONOMY_TYPE;
import static flightfx.model.Booking.FIRST_CLASS_TYPE;
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
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;

/**
 * SceneOneController, FXML Controller class för SceneOne, första fönstret för
 * resebokningen.
 *
 * @author Grupp 2
 */
public class SceneOneController implements Initializable {

    ObservableList<String> nbrOfPassengersList;
    ObservableList<Airport> fromAirportList;
    ObservableList<Airport> toAirportList;
    public Airport from;
    public Airport to;
    public static String fromCode;
    public static String toCode;
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
    private Button backButton;
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
    private Label fromErrorLbl;
    @FXML
    private Label toErrorLbl;
    @FXML
    private Label sameAirportErrorLbl;
    @FXML
    private Label date1ErrorLbl;
    @FXML
    private Label date2ErrorLbl;
    private Boolean allCorrect;

    /**
     * searchButtonAction kontrollerar så att alla uppgifter för resan är
     * valda/inmatade, och hämtar dem.
     *
     * @param event
     * @throws IOException
     */
    @FXML
    public void searchButtonAction(ActionEvent event) throws IOException {

        // Nollställer felmeddelanden
        showErrorMessages(false);
        allCorrect = true;

        from = fromAirportComboBox.getSelectionModel().getSelectedItem();
        to = toAirportComboBox.getSelectionModel().getSelectedItem();

        date1 = datePicker1.getValue();
        nbrOfPassengers = nbrOfPassengersComboBox.getSelectionModel().getSelectedIndex() + 1;

        if (firstClassRadioButton.isSelected()) {
            ticketType = FIRST_CLASS_TYPE;
        } else if (businessRadioButton.isSelected()) {
            ticketType = BUSINESS_TYPE;
        } else {
            ticketType = ECONOMY_TYPE;
        }
        /*
        FELHANTERING
         */
        if (to == null) {
            toErrorLbl.setVisible(true);
            allCorrect = false;
        }

        if (from == null) {
            fromErrorLbl.setVisible(true);
            allCorrect = false;
        }
        if (to != null && from != null && to.equals(from)) {
            sameAirportErrorLbl.setVisible(true);
            allCorrect = false;

        }
        if (date1.isBefore(LocalDate.now())) {
            date1ErrorLbl.setVisible(true);
            allCorrect = false;
        }

        /*
        Om inga fel finns, gå till nästa scen
         */
        if (allCorrect) {
            fromCode = fromAirportComboBox.getSelectionModel().getSelectedItem().toString().substring(0, 3);
            toCode = toAirportComboBox.getSelectionModel().getSelectedItem().toString().substring(0, 3);

            Parent root = FXMLLoader.load(getClass().getResource("SceneTwo.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
            stage.setScene(scene);
            stage.show();
        }

    }

    /**
     * showErrorMessages hanterar synlighet av felmeddelanden.
     *
     * @param b
     */
    private void showErrorMessages(Boolean b) {
        fromErrorLbl.setVisible(b);
        toErrorLbl.setVisible(b);
        sameAirportErrorLbl.setVisible(b);
        date1ErrorLbl.setVisible(b);
        date2ErrorLbl.setVisible(b);

    }

    /**
     * backButtonAction hanterar Tillbaka-knappen, går tillbaka till föregående
     * scen.
     *
     * @param event
     * @throws IOException
     */
    @FXML
    public void backButtonAction(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("StartScene.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Initializes the controller class. Hämtar alla flygplatser och visar dem i
     * ComboBox för avreseort respektive destination. Sätter startvärden för
     * datum, antal passagerare, enkelresa, restyp.
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

        //Group för enkel eller tur och retur RadioButtons
        ToggleGroup radioGroup = new ToggleGroup();
        oneWayRadioButton.setToggleGroup(radioGroup);
        roundTripRadioButton.setToggleGroup(radioGroup);

        //Group för biljettyp RadioButtons
        ToggleGroup typeGroup = new ToggleGroup();
        economyRadioButton.setToggleGroup(typeGroup);
        businessRadioButton.setToggleGroup(typeGroup);
        firstClassRadioButton.setToggleGroup(typeGroup);

        /*
        Startvärden
         */
        datePicker2.setDisable(true);

        datePicker1.setValue(LocalDate.now());
        datePicker2.setValue(LocalDate.now());

        oneWayRadioButton.setOnAction((event) -> {
            datePicker2.setDisable(true);
        });

        roundTripRadioButton.setOnAction((event) -> {
            datePicker2.setDisable(false);
        });

        // Felmeddelandena dolda från början
        showErrorMessages(false);
        allCorrect = true;

    }

}
