package api;

import java.util.Collection;
import java.util.Date;
import java.util.HashMap;

import model.Customer;
import model.IRoom;
import model.Reservation;
import model.Room;
import model.RoomType;

public class HotelResource {
    private static HotelResource hotelResourceInstance;

    private HotelResource(){}

    public HotelResource getHotelResource(){
        if(hotelResourceInstance == null)
            hotelResourceInstance = new HotelResource();
        return hotelResourceInstance;
    }

    public Customer getCustomer(String email){
        return new Customer("email", "email", "email");
    }

    public void createACustomer(String email, String firstName, String lastName){

    }

    public IRoom getRoom(String roomNumber){
        return new Room("roomNumber", 0, RoomType.SINGLE);
    }

    public Reservation bookARoom(String customerEmail, IRoom room, Date checkInDate, Date checkOutDate){
        return new Reservation(new Customer(customerEmail, customerEmail, customerEmail),
        new Room("customerEmail", 0, RoomType.SINGLE),
        checkInDate, checkOutDate);
    }

    public Collection<Reservation> getCustomerReservations(String customerEmail){
        return (Collection<Reservation>) new HashMap();
    }

    public Collection<IRoom> findARoom(Date checkInDate, Date checkOuDate){
        return (Collection<IRoom>) new HashMap();
    }
}
