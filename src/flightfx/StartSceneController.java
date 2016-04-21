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
import javafx.stage.Stage;

/**
 * StartSceneController, FXML Controller class för StartScene, applikationens
 * startfönster.
 *
 * @author Grupp 2
 */
public class StartSceneController implements Initializable {

    @FXML
    private Button bookFlightButton;
    @FXML
    private Button cancelBookingButton;
    @FXML
    private Button quitButton;

    /**
     * cancelBookingButtonAction hanterar Avboka resa-knappen, går vidare till
     * scenen för avbokning.
     *
     * @param event
     * @throws IOException
     */
    @FXML
    public void cancelBookingButtonAction(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("CancelBooking.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
    }

    /**
     * bookFlightButtonAction hanterar Boka resa-knappen, går vidare till
     * bokningssidorna.
     *
     * @param event
     * @throws IOException
     */
    @FXML
    public void bookFlightButtonAction(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("SceneOne.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
    }

    /**
     * quitButtonAction hanterar Avsluta-knappen, avslutar applikatonen.
     *
     * @param event
     * @throws IOException
     */
    @FXML
    public void quitButtonAction(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
