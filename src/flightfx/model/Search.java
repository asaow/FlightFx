/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flightfx.model;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author Loki
 */
public class Search {


    private String fromAirportCode; 
    private String toAirportCode;
    private LocalDate depDate;
    
    public Search(String fromAirportCode, String toAirportCode, LocalDate depDate) {
        this.fromAirportCode = fromAirportCode;
        this.toAirportCode = toAirportCode;
        this.depDate = depDate;
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

    public LocalDate getDepDate() {
        return depDate;
    }

    public void setDepDate(LocalDate depDate) {
        this.depDate = depDate;
    }

}

    

