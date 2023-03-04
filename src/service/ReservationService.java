package service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
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
        private Date checkIn;
        private Date checkOut;
        
        public DatePair(Date checkIn, Date checkOut){
            this.checkIn = checkIn;
            this.checkOut = checkOut;
        }

        public Date getCheckIn(){
            return checkIn;
        }

        public Date getCheckOut(){
            return checkOut;
        }

        public boolean isOverlapping(Date in, Date out){
            return !(out.before(checkIn) || in.after(checkOut));
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

    public Reservation reserveARoom(Customer customer, IRoom room, Date checkInDate, Date checkOutDate){
        Reservation reservation = new Reservation(customer, room, checkInDate, checkOutDate);
        if(!reservationsData.containsKey(customer))
            reservationsData.put(customer, new ArrayList<>());

        reservationsData.get(customer).add(reservation);
        return reservation;
    }

    public Collection<IRoom> findRooms(Date checkInDate, Date checkOutDate){
        Map<IRoom, List<DatePair>> room2dates = new HashMap<>();
        Collection<Reservation> allReservations = getAllReservations();

        for(Reservation res: allReservations){
            IRoom room = res.getRoom();
            if(!room2dates.containsKey(res.getRoom())){
                room2dates.put(room, new ArrayList<>());
            }
            room2dates.get(room).add(new DatePair(res.getCheckInDate(), res.getCheckInDate()));
        }

        Map<String, IRoom> allRooms = new HashMap<>();
        allRooms.putAll(roomsData);

        for(Map.Entry<IRoom, List<DatePair>> entry: room2dates.entrySet())
            for(DatePair datePair : entry.getValue())
                if(datePair.isOverlapping(checkInDate, checkOutDate))
                    allRooms.remove(entry.getKey().getRoomNumber());
        return allRooms.values();
    }

    public Collection<Reservation> getCustomerReservations(Customer customer){
        return reservationsData.get(customer);
    }

    public void printAllReservations(){
        Collection<Reservation> allReservations = getAllReservations();
        for(Reservation res: allReservations)
        {
            System.out.println(res);
            System.out.println();
        }
    }

    private Collection<Reservation> getAllReservations(){
        Collection<Reservation> allReservations = new ArrayList<>();
        for(Collection<Reservation> reservationList : reservationsData.values())
            for(Reservation res : reservationList)
                allReservations.add(res);
        return allReservations;
    }
}
