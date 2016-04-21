package flightfx;

import flightfx.model.Booking;
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
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * CancelBookingController är FXML Controller class för CancelBooking.fxml
 * Hanterar avbokning av bokningar.
 *
 * @author Grupp 2
 */
public class CancelBookingController implements Initializable {

    @FXML
    private Button backButton;
    @FXML
    private Button okButton;
    @FXML
    private Button cancelButton;
    @FXML
    private TextField bookingNrText;
    @FXML
    private TextArea bookingInfoTextArea;

    Booking b;
    private List<Passenger> passList;

    @FXML
    private Label errorLbl;
    private Boolean allCorrect;
    String bookingNrStr;

    /**
     * backButtonAction hanterar Startsida-knappen, går tillbaka till första
     * sidan.
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
     * Metod för OK-knappen, hämtar inmatad bokning och visar i TextArea.
     *
     * @param event
     * @throws IOException
     */
    @FXML
    public void okButtonAction(ActionEvent event) throws IOException {

        // Nollställer felmeddelanden och TextArea
        errorLbl.setVisible(false);
        allCorrect = true;
        bookingInfoTextArea.clear();

        // Avboka-knappen ej synlig
        cancelButton.setVisible(false);

        bookingNrStr = bookingNrText.getText();

        // Om bokningsnummer ej är inmatat, visa felmeddelande
        if (bookingNrStr == null || bookingNrStr.isEmpty()) {
            errorLbl.setVisible(true);
            allCorrect = false;
        }

        // Om bokningsnummer är inmatat, hämta bokningen
        if (allCorrect) {

            b = FlightFx.client.target("http://localhost:8080/FlightServer/webresources/bookings")
                    .path(bookingNrStr)
                    .request(MediaType.APPLICATION_JSON)
                    .get(Booking.class);

            // Om bokningsnumret ej finns i databasen
            if (b == null) {
                bookingInfoTextArea.appendText("Bokning med bokningsnummer " + bookingNrStr + " finns ej, ange korrekt bokningsnummer");
                bookingNrText.clear();
            } else { // Om bokningen finns, visa den i TextArea

                // Avboka-knappen synlig
                cancelButton.setVisible(true);

                DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                String depdate = df.format(b.getFlight().getDepDate().getTime());
                String arrdate = df.format(b.getFlight().getArrDate().getTime());

                bookingInfoTextArea.appendText("Bokningsnummer: " + bookingNrStr + "\n"
                        + "Avgång: " + depdate + "  Kl: " + b.getFlight().getDepTime() + "\n"
                        + "Flygplats: " + b.getFlight().getFromAirportCode() + " " + b.getFlight().getFromAirport() + "\n" + "\n"
                        + "Ankomst: " + arrdate + "  Kl: " + b.getFlight().getArrTime() + "\n"
                        + "Flygplats: " + b.getFlight().getToAirportCode() + " " + b.getFlight().getToAirport() + "\n" + "\n"
                        + "Restid: " + b.getFlight().getDuration() + ",  " + " Byten: " + b.getFlight().getNbrOfConnections() + "\n"
                        + "Biljettyp: " + b.getType() + "\n"
                );

                passList = (List<Passenger>) b.getPassengers();

                bookingInfoTextArea.appendText(SceneFourController.getTotalPriceAndMeal(b.getType(), b.getFlight(), passList));
                bookingInfoTextArea.appendText("==========================================");

                for (Passenger p : passList) {
                    bookingInfoTextArea.appendText("\n" + "\n" + p.toString());
                }
                bookingNrText.clear();

            }
        }
    }

    /**
     * Metod för Avboka-knappen, raderar vald bokning från databasen.
     *
     * @param event
     * @throws IOException
     */
    @FXML
    public void cancelButtonAction(ActionEvent event) throws IOException {

        Response c = FlightFx.client.target("http://localhost:8080/FlightServer/webresources/bookings")
                .path(bookingNrStr)
                .request(MediaType.APPLICATION_JSON)
                .delete();

        bookingInfoTextArea.clear();
        bookingInfoTextArea.setText("\n" + "\n" + "\n" + "\n" + "\n" + "\n"
                + "Bokning med bokningsnummer " + bookingNrStr + " har avbokats");

        // Avboka-knappen ej synlig
        cancelButton.setVisible(false);
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        // Nollställer felmeddelanden
        errorLbl.setVisible(false);
        allCorrect = true;

        // Avboka-knappen ej synlig från start
        cancelButton.setVisible(false);
    }

}
