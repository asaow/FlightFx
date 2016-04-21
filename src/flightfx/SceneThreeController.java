package flightfx;

import flightfx.model.Passenger;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
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
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * SceneThreeController är controller för SceneThree, där inmatning av
 * passageraruppgifter sker, och Passenger-objekt skapas.
 *
 * @author Grupp 2
 */
public class SceneThreeController implements Initializable {

    private String firstName;
    private String lastName;
    private String age;
    private String phone;
    private String email;

    @FXML
    private TextField firstNameText;
    @FXML
    private TextField lastNameText;
    @FXML
    private TextField ageText;
    @FXML
    private TextField phoneText;
    @FXML
    private TextField emailText;

    @FXML
    private Button backButton;
    @FXML
    private Button cancelButton;
    @FXML
    private Button nextButton;
    @FXML
    private Label errorLbl;
    private Boolean allCorrect;

    Passenger passenger;
    public static List<Passenger> passengerList;

    private int nbrOfPassengers;
    private int index;

    public static int getNbrOfPassengers() {
        return SceneOneController.nbrOfPassengers;
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
        Parent root = FXMLLoader.load(getClass().getResource("SceneTwo.fxml"));
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
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
     * nextButtonAction hanterar Nästa-knappen, och hanterar inmatning av
     * passageraruppgifter, och skapar Passenger-objekt. Går sedan vidare till
     * inmatning av nästa passagerare, eller till nästa scen, om inga fler
     * passagerare finns.
     *
     * @param event
     * @throws IOException
     */
    @FXML
    public void nextButtonAction(ActionEvent event) throws IOException {
        // Nollställer felmeddelanden
        errorLbl.setVisible(false);
        allCorrect = true;

        firstName = firstNameText.getText();
        lastName = lastNameText.getText();
        age = ageText.getText();
        phone = phoneText.getText();
        email = emailText.getText();

        /*
        FELHANTERING inmatning
         */
        if (firstName == null || lastName == null || age == null || phone == null || email == null
                || firstName.isEmpty() || lastName.isEmpty() || age.isEmpty() || phone.isEmpty() || email.isEmpty()) {
            errorLbl.setVisible(true);
            allCorrect = false;
        }

        /*
        Om inga fel finns, skapa passagerarobjekt och gå till nästa passagerare
         */
        if (allCorrect) {
            passenger = new Passenger();

            passenger.setFirstName(firstName);
            passenger.setLastName(lastNameText.getText());
            passenger.setAge(ageText.getText());
            passenger.setPhone(phoneText.getText());
            passenger.setEmail(emailText.getText());

            passengerList.add(passenger);

            firstNameText.clear();
            lastNameText.clear();
            ageText.clear();
            phoneText.clear();
            emailText.clear();

            index++; // öka index, dvs nästa passagerare
        }

        if (index > nbrOfPassengers) { // om ej fler passagerare, gå till nästa fönster
            Parent root = FXMLLoader.load(getClass().getResource("SceneFour.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
            stage.setScene(scene);
            stage.show();
        }

    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        index = 1;
        nbrOfPassengers = getNbrOfPassengers();
        passengerList = new ArrayList();

        // Felmeddelande dolt från början
        errorLbl.setVisible(false);
        allCorrect = true;

    }

}
