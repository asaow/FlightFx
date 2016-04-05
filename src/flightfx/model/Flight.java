/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flightfx.model;

import java.util.Calendar;

/**
 *
 * @author Loki
 */
public class Flight {
    private int id;
    private Airport fromAirport;
    private Airport toAirport;
    private Calendar depDate;
    private Calendar arrDate;
    private Calendar depTime;
    private Calendar arrTime;
    private String duration;
    private int nbrOfConnections;
    
    public Flight(){
        
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Airport getFromAirport() {
        return fromAirport;
    }

    public void setFromAirport(Airport fromAirport) {
        this.fromAirport = fromAirport;
    }

    public Airport getToAirport() {
        return toAirport;
    }

    public void setToAirport(Airport toAirport) {
        this.toAirport = toAirport;
    }
    
        public Calendar getDepDate() {
        return depDate;
    }

    public void setDepDate(Calendar depDate) {
        this.depDate = depDate;
    }

    public Calendar getArrDate() {
        return arrDate;
    }

    public void setArrDate(Calendar arrDate) {
        this.arrDate = arrDate;
    }

    public Calendar getDepTime() {
        return depTime;
    }

    public void setDepTime(Calendar depTime) {
        this.depTime = depTime;
    }

    public Calendar getArrTime() {
        return arrTime;
    }

    public void setArrTime(Calendar arrTime) {
        this.arrTime = arrTime;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public int getNbrOfConnections() {
        return nbrOfConnections;
    }

    public void setNbrOfConnections(int nbrOfConnections) {
        this.nbrOfConnections = nbrOfConnections;
    }
    
}
