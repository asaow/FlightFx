package flightfx;

import java.io.IOException;
import java.net.URL;
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

/**
 * SceneFiveController, FXML Controller class för SceneFive, visar bekräftelse
 * för bokning och bokningsnummer.
 *
 * @author Grupp 2
 */
public class SceneFiveController implements Initializable {

    @FXML
    private Button newTripButton;

    @FXML
    private Button finishButton;

    @FXML
    private Label bookingIdLbl;

    /**
     * newTripButtonAction hanterar Startsida-knappen, går tillbaka till första
     * sidan.
     *
     * @param event
     * @throws IOException
     */
    @FXML
    public void newTripButtonAction(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("StartScene.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
    }

    /**
     * finishButtonAction hanterar Avsluta-knappen, avslutar applikatonen.
     *
     * @param event
     * @throws IOException
     */
    @FXML
    public void finishButtonAction(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

    /**
     * Initializes the controller class. Visar bokningsnummer.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        bookingIdLbl.setText("Ditt bokningsnummer är: " + SceneFourController.getBookingId());
    }

}
