package flightfx.model;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * 
 * @author Grupp 2
 */

public class Passenger{
    
    @Id @GeneratedValue
    private int id;
    private String firstName;
    private String lastName;
    private String age;
    private String phone;
    private String email;
    
    
    private Booking booking;

    public Booking getBooking() {
        return booking;
    }

    public void setBooking(Booking booking) {
        this.booking = booking;
    }
    
    public Passenger(){
        
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String toString(){
        return "Förnamn: " + firstName + "\n" + 
                "Efternamn: " + lastName + "\n" + 
                "Ålder: " + age + "\n" + 
                "Telefon: " + phone + "\n" + 
                "Email: " + email;
    }
    
}
