/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flightfx;

import flightfx.model.Flight;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.text.DateFormat;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
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
import javafx.scene.control.ListCell;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.converter.DoubleStringConverter;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;

/**
 * FXML Controller class
 *
 * @author Loki
 */
public class SceneTwoController implements Initializable {

    public static String fromCombo;
    public static String toCombo;
    public static LocalDate dateOneWay;
    public static int flightId;

    //Get selected date from Scene One 
    public static LocalDate getDateOneWay() {
        return SceneOneController.date1;
    }

    public static void setDateOneWay(LocalDate date1) {
        SceneOneController.date1 = date1;
    }

    //Get selected destination from SceneOne ComboBox 
    public static String getToCombo() {
        return SceneOneController.to;
    }

    public static void setToCombo(String to) {
        SceneOneController.to = to;
    }

    //Get selected source from SceneOne ComboBox 
    public static void setFromCombo(String from) {
        SceneOneController.from = from;
    }

    public static String getFromCombo() {
        return SceneOneController.from;
    }

    @FXML
    private TableView<Flight> tableView;
    @FXML
    private TableColumn<Flight, String> fromAirportColumn;
    @FXML
    private TableColumn<Flight, String> toAirportColumn;
    @FXML
    private TableColumn<Flight, Calendar> depDateColumn;
    @FXML
    private TableColumn<Flight, Calendar> arrDateColumn;
    @FXML
    private TableColumn<Flight, Double> priceColumn;
    @FXML
    private TableColumn<Flight, String> airlineColumn;

    @FXML
    private Button cancelButton;

    @FXML
    private Button nextButton;

    @FXML
    private Button backButton;

    public ObservableList<Flight> flightList;

    @FXML
    public void selectFlight(ActionEvent event) throws IOException {
        Flight selected = (Flight) tableView.getSelectionModel().getSelectedItem();

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
        Flight selected = (Flight) tableView.getSelectionModel().getSelectedItem();
        flightId = selected.getId();
        System.out.println(flightId + " id from selected flight in tableview");
        Parent root = FXMLLoader.load(getClass().getResource("SceneThree.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    public void showFlights(ActionEvent event) {
        System.out.println(SceneOneController.from.substring(0, 3));

    }

    @FXML
    public void backButtonAction(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("SceneOne.fxml"));
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
        System.out.println(getFromCombo() + " hihih");
        System.out.println(getToCombo() + " hahah");
        flightList = FXCollections.observableArrayList();

        GenericType<List<Flight>> gt = new GenericType<List<Flight>>() {
        };
        List<Flight> c;
        c = FlightFx.client.target("http://localhost:8080/FlightServer/webresources/flights")
                .path(getFromCombo() + "/" + getToCombo() + "/" + getDateOneWay())
                .request(MediaType.APPLICATION_JSON)
                .get(gt);
        System.out.println(c);

        for (Flight f : c) {
            f.getFromAirportCode();
            f.getFromAirport();
            f.getToAirport();
            f.getToAirportCode();
            f.getAirline();
            f.getPrice();
            f.getDepDate();
            f.getArrDate();

            flightList.add(f);

            DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            String date = df.format(f.getDepDate().getTime());
            //System.out.println(df.format(f.getDepDate().getTime()));
            System.out.println(date);

        }

        fromAirportColumn.setCellValueFactory(new PropertyValueFactory<Flight, String>("fromAirport"));
        toAirportColumn.setCellValueFactory(new PropertyValueFactory<Flight, String>("toAirport"));
        airlineColumn.setCellValueFactory(new PropertyValueFactory<Flight, String>("airline"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<Flight, Double>("price"));
        depDateColumn.setCellValueFactory(new PropertyValueFactory<Flight, Calendar>("depDate"));
        arrDateColumn.setCellValueFactory(new PropertyValueFactory<Flight, Calendar>("arrDate"));

        priceColumn.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));
        airlineColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        fromAirportColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        toAirportColumn.setCellFactory(TextFieldTableCell.forTableColumn());

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        depDateColumn.setCellFactory(col -> new TableCell<Flight, Calendar>() {
            @Override
            public void updateItem(Calendar item, boolean empty) {
                super.updateItem(item, empty);
                if (item == null) {
                    setText(null);
                } else {
                    setText(dateFormat.format(item.getTime()));
                }
            }
        });

        arrDateColumn.setCellFactory(col -> new TableCell<Flight, Calendar>() {
            @Override
            public void updateItem(Calendar item, boolean empty) {
                super.updateItem(item, empty);
                if (item == null) {
                    setText(null);
                } else {
                    setText(dateFormat.format(item.getTime()));
                }
            }
        });

//        fromAirportColumn.setCellValueFactory(new Callback<CellDataFeatures<Flight, String>, ObservableValue<String>>() {
//            public ObservableValue<String> call(CellDataFeatures<Flight, String> f) {
//                // f.getValue() returns the Flight instance for a particular TableView row
//                return new ReadOnlyObjectWrapper(f.getValue().getFromAirportCode());
//
//            }
//
//        });
//        toAirportColumn.setCellValueFactory(new Callback<CellDataFeatures<Flight, String>, ObservableValue<String>>() {
//            public ObservableValue<String> call(CellDataFeatures<Flight, String> f) {
//                // f.getValue() returns the Person instance for a particular TableView row
//                return new ReadOnlyObjectWrapper(f.getValue().getToAirportCode());
//
//            }
//
//        });
        tableView.setItems(flightList);
        System.out.println("sceneTwo testing date method " + getDateOneWay());

        SceneFourController.setFlightId(flightId);
    }
}
