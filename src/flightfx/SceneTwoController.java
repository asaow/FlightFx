package flightfx;

import flightfx.model.Flight;
import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
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
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;
import javafx.util.converter.DoubleStringConverter;
import javafx.util.converter.IntegerStringConverter;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;

/**
 * SceneTwoController är FXML Controller class för SceneTwo, som visar
 * tillgängliga flygavgångar för vald tidpunkt.
 *
 * @author Grupp 2
 */
public class SceneTwoController implements Initializable {

    public static String fromCombo;
    public static String toCombo;
    public static LocalDate dateOneWay;
    public static int flightId;

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
    private TableColumn<Flight, String> depTimeColumn;
    @FXML
    private TableColumn<Flight, String> arrTimeColumn;
    @FXML
    private TableColumn<Flight, String> durationColumn;
    @FXML
    private TableColumn<Flight, Integer> nbrOfConnectionsColumn;

    @FXML
    private Label flightLabel;
    @FXML
    private Button cancelButton;

    @FXML
    private Button nextButton;

    @FXML
    private Button backButton;

    public ObservableList<Flight> flightList;

    @FXML
    private Label awayErrorLbl;
    private Boolean allCorrect;

    /**
     * Returnerar valt avresedatum från SceneOne
     *
     * @return SceneOneController.date1
     */
    public static LocalDate getDateOneWay() {
        return SceneOneController.date1;
    }

    /**
     * Returnerar vald destination från SceneOne
     *
     * @return SceneOneController.toCode
     */
    public static String getToCombo() {
        return SceneOneController.toCode;
    }

    /**
     * Returnerar vald avreseort från SceneOne
     *
     * @return SceneOneController.fromCode
     */
    public static String getFromCombo() {
        return SceneOneController.fromCode;
    }

    @FXML
    public void selectFlight(ActionEvent event) throws IOException {
        Flight selected = (Flight) tableView.getSelectionModel().getSelectedItem();

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
     * nextButtonAction sparar vald flygresa och går till nästa scen.
     *
     * @param event
     * @throws IOException
     */
    @FXML
    public void nextButtonAction(ActionEvent event) throws IOException {
        // Nollställer felmeddelanden
        awayErrorLbl.setVisible(false);
        allCorrect = true;
        Flight selected = (Flight) tableView.getSelectionModel().getSelectedItem();

        /*
        FELHANTERING
         */
        if (selected == null) {
            awayErrorLbl.setVisible(true);
            allCorrect = false;
        }
        if (allCorrect) {
            flightId = selected.getId();
            Parent root = FXMLLoader.load(getClass().getResource("SceneThree.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
            stage.setScene(scene);
            stage.show();
        }

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
        Parent root = FXMLLoader.load(getClass().getResource("SceneOne.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Initializes the controller class. Hämtar flygresor enligt gjorda val och
     * visar i tabellen.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        // Nollställer felmeddelanden
        awayErrorLbl.setVisible(false);
        allCorrect = true;

        flightList = FXCollections.observableArrayList();

        GenericType<List<Flight>> gt = new GenericType<List<Flight>>() {
        };
        List<Flight> c;
        c = FlightFx.client.target("http://localhost:8080/FlightServer/webresources/flights")
                .path(getFromCombo() + "/" + getToCombo() + "/" + getDateOneWay())
                .request(MediaType.APPLICATION_JSON)
                .get(gt);

        for (Flight f : c) {
            f.getFromAirportCode();
            f.getFromAirport();
            f.getToAirport();
            f.getToAirportCode();
            f.getAirline();
            f.getPrice();
            f.getDepDate();
            f.getArrDate();
            f.getArrTime();
            f.getDepTime();
            f.getDuration();
            f.getNbrOfConnections();

            flightList.add(f);

            DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            String date = df.format(f.getDepDate().getTime());

        }

        fromAirportColumn.setCellValueFactory(new PropertyValueFactory<Flight, String>("fromAirport"));
        toAirportColumn.setCellValueFactory(new PropertyValueFactory<Flight, String>("toAirport"));
        airlineColumn.setCellValueFactory(new PropertyValueFactory<Flight, String>("airline"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<Flight, Double>("price"));
        depDateColumn.setCellValueFactory(new PropertyValueFactory<Flight, Calendar>("depDate"));
        arrDateColumn.setCellValueFactory(new PropertyValueFactory<Flight, Calendar>("arrDate"));
        arrTimeColumn.setCellValueFactory(new PropertyValueFactory<Flight, String>("arrTime"));
        depTimeColumn.setCellValueFactory(new PropertyValueFactory<Flight, String>("depTime"));
        durationColumn.setCellValueFactory(new PropertyValueFactory<Flight, String>("duration"));
        nbrOfConnectionsColumn.setCellValueFactory(new PropertyValueFactory<Flight, Integer>("nbrOfConnections"));

        priceColumn.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));
        airlineColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        fromAirportColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        toAirportColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        arrTimeColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        depTimeColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        durationColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        nbrOfConnectionsColumn.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));

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

        tableView.setItems(flightList);
        flightLabel.setText(getFromCombo() + " - " + getToCombo() + "   " + getDateOneWay());
        SceneFourController.setFlightId(flightId);
    }
}
