/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flightfx.model;

import java.util.ArrayList;
import java.util.Collection;

/**
 *
 * @author Loki
 */
public class Airport {
    private int id;
    private String city;
    private String name;
    private String code;
    
    public Airport() {

    }

    private Collection<Flight> arrivals = new ArrayList<Flight>();

    private Collection<Flight> departures = new ArrayList<Flight>();

    public Collection<Flight> getArrivals() {
        return arrivals;
    }

    public void setArrivals(Collection<Flight> arrivals) {
        this.arrivals = arrivals;
    }

    public Collection<Flight> getDepartures() {
        return departures;
    }

    public void setDepartures(Collection<Flight> departures) {
        this.departures = departures;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return city + ", " + name + ", " + code;
    }
    
    
}
