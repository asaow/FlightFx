package flightfx;

import flightfx.model.Booking;
import flightfx.model.Flight;
import flightfx.model.Passenger;
import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
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
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;

/**
 * SceneFourController är en bekräftelsesida som visar alla valda uppgifter och
 * skapar en bokning, ett Booking-objekt.
 *
 * @author Grupp 2
 */
public class SceneFourController implements Initializable {

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
    private TextArea flightTextArea;
    @FXML
    private Button confirmButton;
    @FXML
    private TextArea passengerTextArea;

    private Booking booking;
    private static Booking b;
    private Flight c;
    private List<Passenger> passList;

    public static int getBookingId() {
        return b.getId();
    }

    public static int getFlightId() {
        return SceneTwoController.flightId;
    }

    public static void setFlightId(int flightId) {
        SceneTwoController.flightId = flightId;
    }

    public static List<Passenger> getPassengerList() {
        return SceneThreeController.passengerList;
    }

    public String getTicketType() {
        return SceneOneController.ticketType;
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
        Parent root = FXMLLoader.load(getClass().getResource("SceneThree.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
    }

    /**
     * cancelButtonAction hanterar Startsida-knappen, går tillbaka till första
     * sidan.
     *
     * @param event
     * @throws IOException
     */
    @FXML
    public void cancelButtonAction(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("StartScene.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
    }

    /**
     * confirmButtonAction hanterar Bekräfta-knappen, skapar en bokning och går
     * till nästa scen.
     *
     * @param event
     * @throws IOException
     */
    @FXML
    public void confirmButtonAction(ActionEvent event) throws IOException {

        booking = new Booking();

        booking.setPassengers(passList);
        booking.setFlight(c);
        booking.setType(getTicketType());

        b = FlightFx.client.target("http://localhost:8080/FlightServer/webresources/bookings")
                .request()
                .post(Entity.entity(booking, MediaType.APPLICATION_JSON), Booking.class);

        Parent root = FXMLLoader.load(getClass().getResource("SceneFive.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
    }

    /**
     * getTotalPriceAndMeal, metod som returnerar en String med information om
     * respektive biljettyp.
     *
     * @param ticketType
     * @param flight
     * @param passList
     * @return String med information om respektive biljettyp
     */
    public static String getTotalPriceAndMeal(String ticketType, Flight flight, List<Passenger> passList) {
        double totalPrice;
        String meal;
        String s;
        switch (ticketType) {
            case "BUSINESSKLASS":
                totalPrice = flight.getPrice() * passList.size() * 1.7;
                meal = "Lättare måltid ingår";
                break;
            case "FÖRSTA KLASS":
                totalPrice = flight.getPrice() * passList.size() * 2;
                meal = "3-rätters måltid ingår";
                break;
            default:
                totalPrice = flight.getPrice() * passList.size();
                meal = "";
                break;
        }
        s = meal + "\n" + "\n" + "Totalpris: " + totalPrice + " SEK" + "\n";
        return s;
    }

    /**
     * Initializes the controller class. Hämtar bokningsuppgifter och visar dem
     * i TextArea.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        String id = String.valueOf(getFlightId());
        c = FlightFx.client.target("http://localhost:8080/FlightServer/webresources/flights")
                .path(id)
                .request(MediaType.APPLICATION_JSON)
                .get(Flight.class);

        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String depdate = df.format(c.getDepDate().getTime());
        String arrdate = df.format(c.getArrDate().getTime());

        flightTextArea.appendText("Avgång: " + depdate + "  Kl: " + c.getDepTime() + "\n"
                + "Flygplats: " + c.getFromAirportCode() + " " + c.getFromAirport() + "\n" + "\n"
                + "Ankomst: " + arrdate + "  Kl: " + c.getArrTime() + "\n"
                + "Flygplats: " + c.getToAirportCode() + " " + c.getToAirport() + "\n" + "\n"
                + "Restid: " + c.getDuration() + ",  " + " Byten: " + c.getNbrOfConnections() + "\n"
        );

        flightTextArea.appendText("Biljettyp: " + getTicketType() + "\n");

        passList = getPassengerList();
        for (Passenger p : passList) {
            passengerTextArea.appendText(p.toString());
        }

        flightTextArea.appendText(getTotalPriceAndMeal(getTicketType(), c, passList));

    }

}
