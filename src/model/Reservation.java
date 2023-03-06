package model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Reservation {
    static DateTimeFormatter f = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private Customer customer;
    private IRoom room;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;

    public Reservation(Customer customer, IRoom room, LocalDate checkInDate, LocalDate checkOutDate){
        this.customer = customer;
        this.room = room;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
    }

    public Customer getCustomer() {
        return this.customer;
    }

    public IRoom getRoom() {
        return this.room;
    }

    public LocalDate getCheckInDate() {
        return this.checkInDate;
    }

    public LocalDate getCheckOutDate() {
        return this.checkOutDate;
    }
    

    @Override
    public String toString(){
        return getCustomer().getFirstName() + " " + getCustomer().getLastName() + "\n" + getRoom() + "\n" +
        "Check In Date: " + f.format(getCheckInDate()) + " Check Out Date: " + f.format(getCheckOutDate());
    }
}
