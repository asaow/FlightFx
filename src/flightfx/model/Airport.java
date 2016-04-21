package flightfx.model;

/**
 * Klassen Airport lagrar information om en flygplats.
 *
 * @author Grupp 2
 */
public class Airport {

    private int id;
    private String code;
    private String name;
    private String city;

    /*
    Konstruktor
     */
    public Airport() {

    }

    /*
    Getters och setters
     */
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return code + " " + name + " " + city;
    }

}
