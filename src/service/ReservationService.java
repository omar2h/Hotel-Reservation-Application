package service;

import java.util.Collection;
import java.util.Date;
import java.util.HashMap;

import model.Customer;
import model.IRoom;
import model.Reservation;
import model.Room;
import model.RoomType;

public class ReservationService {
    private static ReservationService reservationServiceInstance;

    private ReservationService(){}

    public ReservationService getReservationService(){
        if(reservationServiceInstance == null)
            reservationServiceInstance = new ReservationService();
        return reservationServiceInstance;
    }

    public void addRoom(IRoom room){
        ;
    }

    public IRoom getARoom(String roomId){
        return new Room("0", 0, RoomType.SINGLE);
    }

    public Reservation reserveARoom(Customer customer, IRoom room, Date checkInDate, Date checkOutDate){
        return new Reservation(customer, room, checkInDate, checkOutDate);
    }

    public Collection<IRoom> findRooms(Date checkInDate, Date checkOutDate){
        return (Collection<IRoom>) new HashMap();
    }

    public Collection<Reservation> getCustomerReservation(Customer customer){
        return (Collection<Reservation>) new HashMap();
    }

    public void printAllReservations(){
        ;
    }
}
