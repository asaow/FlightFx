package flightfx.model;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Klassen Booking lagrar information om en bokning.
 *
 * @author Grupp 2
 */
public class Booking {

    private int id;
    private Flight flight;
    private Collection<Passenger> passengers;

    private String type;
    public static final String FIRST_CLASS_TYPE = "FÖRSTA KLASS";
    public static final String BUSINESS_TYPE = "BUSINESSKLASS";
    public static final String ECONOMY_TYPE = "EKONOMIKLASS";

    /*
    Konstruktor
     */
    public Booking() {
        this.passengers = new ArrayList<>();

    }

    /*
    Getters och setters
     */
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public static String getFIRST_CLASS_TYPE() {
        return FIRST_CLASS_TYPE;
    }

    public static String getBUSINESS_TYPE() {
        return BUSINESS_TYPE;
    }

    public static String getECONOMY_TYPE() {
        return ECONOMY_TYPE;
    }

    public Collection<Passenger> getPassengers() {
        return passengers;
    }

    public void setPassengers(Collection<Passenger> passengers) {
        this.passengers = passengers;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Flight getFlight() {
        return flight;
    }

    public void setFlight(Flight flight) {
        this.flight = flight;
    }

}
