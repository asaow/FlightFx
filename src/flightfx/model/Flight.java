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
    private String fromAirport;
    private String toAirport;
    private String fromAirportCode;    
    private String toAirportCode;
    private Calendar depDate;
    private Calendar arrDate;
    private String depTime;
    private String arrTime;
    private String airline;
    private String duration;
    private double price;
    private int nbrOfConnections;

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

    public String getDepTime() {
        return depTime;
    }

    public void setDepTime(String depTime) {
        this.depTime = depTime;
    }

    public String getArrTime() {
        return arrTime;
    }

    public void setArrTime(String arrTime) {
        this.arrTime = arrTime;
    }

    public String getAirline() {
        return airline;
    }

    public void setAirline(String airline) {
        this.airline = airline;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    
    public String getFromAirportCode() {
        return fromAirportCode;
    }

    public void setFromAirportCode(String fromAirportCode) {
        this.fromAirportCode = fromAirportCode;
    }

    public String getToAirportCode() {
        return toAirportCode;
    }

    public void setToAirportCode(String toAirportCode) {
        this.toAirportCode = toAirportCode;
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


    public Flight() {
 
    }
 
    public int getId() {
        return id;
    }
 
    public void setId(int id) {
        this.id = id;
    }
 
    public String getFromAirport() {
        return fromAirport;
    }
 
    public void setFromAirport(String fromAirport) {
        this.fromAirport = fromAirport;
    }
 
    public String getToAirport() {
        return toAirport;
    }
 
    public void setToAirport(String toAirport) {
        this.toAirport = toAirport;
    }

//    private Collection<Booking> bookings = new ArrayList<Booking>();
//
//    public Collection<Booking> getBookings() {
//        return bookings;
//    }
//
//    public void setBookings(Collection<Booking> bookings) {
//        this.bookings = bookings;
//    }
 
//    public Calendar getDepDate() {
//        return depDate;
//    }
// 
//    public void setDepDate(Calendar depDate) {
//        this.depDate = depDate;
//    }
// 
//    public Calendar getArrDate() {
//        return arrDate;
//    }
// 
//    public void setArrDate(Calendar arrDate) {
//        this.arrDate = arrDate;
//    }
// 
//    public Calendar getDepTime() {
//        return depTime;
//    }
// 
//    public void setDepTime(Calendar depTime) {
//        this.depTime = depTime;
//    }
// 
//    public Calendar getArrTime() {
//        return arrTime;
//    }
// 
//    public void setArrTime(Calendar arrTime) {
//        this.arrTime = arrTime;
//    }
 

 
}