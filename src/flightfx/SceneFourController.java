/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flightfx;

//import static flightfx.SceneTwoController.getDateOneWay;
//import static flightfx.SceneTwoController.getFromCombo;
//import static flightfx.SceneTwoController.getToCombo;
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
 * Bekräftelsesidan
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
    private double totalPrice;
    private List<Passenger> passList;
    private String meal;

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

        booking = new Booking();

        //booking.getPassengers().addAll(passList);
        booking.setPassengers(passList);
        booking.setFlight(c);
        booking.setType(getTicketType());

        b = FlightFx.client.target("http://localhost:8080/FlightServer/webresources/bookings")
                .request()
                .post(Entity.entity(booking, MediaType.APPLICATION_JSON), Booking.class);
        System.out.println("booking_id: " + b.getId());

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
        c = FlightFx.client.target("http://localhost:8080/FlightServer/webresources/flights")
                .path(id)
                .request(MediaType.APPLICATION_JSON)
                .get(Flight.class);

        System.out.println(c.getAirline());

        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String depdate = df.format(c.getDepDate().getTime());
        String arrdate = df.format(c.getArrDate().getTime());

        System.out.println("SceneFour flight id: " + getFlightId());
        flightTextArea.appendText("Avgång: " + depdate + "  Kl: " + c.getDepTime() + "\n"
                + "Flygplats: " + c.getFromAirportCode() + " " + c.getFromAirport() + "\n" + "\n"
                + "Ankomst: " + arrdate + "  Kl: " + c.getArrTime() + "\n"
                + "Flygplats: " + c.getToAirportCode() + " " + c.getToAirport() + "\n" + "\n"
                + "Restid: " + c.getDuration() + ",  " + " Byten: " + c.getNbrOfConnections()+ "\n"

        );
        flightTextArea.appendText("Biljettyp: " + getTicketType() + "\n");

        passList = getPassengerList();
        for (Passenger p : passList) {
            passengerTextArea.appendText(p.toString());
        }
        System.out.println(passList.size() + " (SceneFour) pass list");
        
        switch (getTicketType()) {
            case "BUSINESSKLASS":
                totalPrice = c.getPrice() * passList.size() * 1.7;
                meal = "Lättare måltid ingår";
                break;
            case "FÖRSTA KLASS":
                totalPrice = c.getPrice() * passList.size() * 2;
                meal = "Tre rätters ingår";
                break;
            default:
                totalPrice = c.getPrice() * passList.size();
                meal = "";
                break;
        }

        flightTextArea.appendText("Totalpris: " + totalPrice + " SEK" + "\n" + meal);

    }

}
