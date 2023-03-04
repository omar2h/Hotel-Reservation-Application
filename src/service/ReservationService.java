package service;

import java.util.ArrayList;
import java.util.Collection;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.Customer;
import model.IRoom;
import model.Reservation;
import model.Room;
import model.RoomType;

public class ReservationService {
    private class DatePair{
        private LocalDate checkIn;
        private LocalDate checkOut;
        
        public DatePair(LocalDate checkIn, LocalDate checkOut){
            this.checkIn = checkIn;
            this.checkOut = checkOut;
        }

        public LocalDate getCheckIn(){
            return checkIn;
        }

        public LocalDate getCheckOut(){
            return checkOut;
        }

        public boolean isOverlapping(LocalDate in, LocalDate out){
            return !(out.isBefore(checkIn) || in.isAfter(checkOut));
        }

    }

    private Map<String, IRoom> roomsData;
    private Map<Customer, List<Reservation>> reservationsData;
    private static ReservationService reservationServiceInstance;

    private ReservationService(){
        roomsData = new HashMap<>();
        reservationsData = new HashMap<>();
    }

    public static ReservationService getReservationService(){
        if(reservationServiceInstance == null)
            reservationServiceInstance = new ReservationService();
        return reservationServiceInstance;
    }

    public void addRoom(IRoom room) throws IllegalArgumentException {
        if(roomsData.containsKey(room.getRoomNumber()))
            throw new IllegalArgumentException("Room already exists");
        roomsData.put(room.getRoomNumber(), room);
    }

    public IRoom getARoom(String roomId) throws IllegalArgumentException {
        if(!roomsData.containsKey(roomId))
            throw new IllegalArgumentException("Room does not exists");
        return roomsData.get(roomId);
    }

    public Reservation reserveARoom(Customer customer, IRoom room, LocalDate checkInDate, LocalDate checkOutDate){
        Reservation reservation = new Reservation(customer, room, checkInDate, checkOutDate);
        reservationsData.computeIfAbsent(customer, k -> new ArrayList<>());
        reservationsData.get(customer).add(reservation);
        return reservation;
    }

    public Collection<IRoom> findRooms(LocalDate checkInDate, LocalDate checkOutDate){
        Map<IRoom, List<DatePair>> room2dates = new HashMap<>();
        Collection<Reservation> allReservations = getAllReservations();

        for(Reservation res: allReservations){
            IRoom room = res.getRoom();
            room2dates.computeIfAbsent(room, k -> new ArrayList<>());
            room2dates.get(room).add(new DatePair(res.getCheckInDate(), res.getCheckInDate()));
        }
        Map<String, IRoom> allRooms = new HashMap<>();
        allRooms.putAll(roomsData);
        return removeReservedRooms(allRooms, room2dates, checkInDate, checkOutDate);
    }

    public Collection<Reservation> getCustomerReservations(Customer customer){
        return reservationsData.get(customer);
    }

    public Collection<IRoom> getAllRooms(){
        return roomsData.values();
    }

    public void printAllReservations(){
        Collection<Reservation> allReservations = getAllReservations();
        for(Reservation res: allReservations)
        {
            System.out.println(res);
            System.out.println();
        }
    }

    public void printAllRooms(){
        Collection<IRoom> allRooms = roomsData.values();
        System.out.println(allRooms);
    }

    private Collection<Reservation> getAllReservations(){
        Collection<Reservation> allReservations = new ArrayList<>();
        for(Collection<Reservation> reservationList : reservationsData.values())
            for(Reservation res : reservationList)
                allReservations.add(res);
        return allReservations;
    }

    private Collection<IRoom> removeReservedRooms(Map<String, IRoom> allRooms,
                            Map<IRoom, List<DatePair>> room2dates,
                            LocalDate checkInDate, LocalDate checkOutDate){
        for(Map.Entry<IRoom, List<DatePair>> entry: room2dates.entrySet())
            for(DatePair datePair : entry.getValue())
                if(datePair.isOverlapping(checkInDate, checkOutDate))
                    allRooms.remove(entry.getKey().getRoomNumber());
        return allRooms.values();
    }
}
