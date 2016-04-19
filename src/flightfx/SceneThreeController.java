/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * Mata in Personuppgifter
 *
 * @author Loki
 */
public class SceneThreeController implements Initializable {

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

    Passenger passenger;
    public static List<Passenger> passengerList;

    public static int getNbrOfPassengers() {
        return SceneOneController.nbrOfPassengers;
    }


    @FXML
    public void backButtonAction(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("SceneTwo.fxml"));
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
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
    public void nextButtonAction(ActionEvent event) throws IOException {
        passenger = new Passenger();
        passenger.setFirstName(firstNameText.getText());
        passenger.setLastName(lastNameText.getText());
        passenger.setAge(ageText.getText());
        passenger.setPhone(phoneText.getText());
        passenger.setEmail(emailText.getText());

        passengerList.add(passenger);
        System.out.println(passenger);

        Parent root = FXMLLoader.load(getClass().getResource("SceneFour.fxml"));
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
        passengerList = new ArrayList();
    }

}
