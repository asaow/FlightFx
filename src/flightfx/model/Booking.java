/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flightfx.model;

import flightfx.model.Flight;
import flightfx.model.Person;

/**
 *
 * @author Loki
 */
public class Booking {
    private int id;
    private Flight flight;
    private Person person;
    
    public Booking(){
        
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
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
