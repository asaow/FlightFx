/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flightfx;

import java.io.IOException;
import java.net.URL;
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
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Loki
 */
public class SceneOneController implements Initializable {
    ObservableList<String> nbrOfPassengers;
    
    @FXML
    private ComboBox nbrOfPassengersComboBox;

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
        
        ToggleGroup radioGroup = new ToggleGroup();
        oneWayRadioButton.setToggleGroup(radioGroup);
        roundTripRadioButton.setToggleGroup(radioGroup);
    }    
    
}
