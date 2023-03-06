package api;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import exceptions.EmailNotFoundException;
import model.Customer;
import model.IRoom;
import service.CustomerService;
import service.ReservationService;

public class AdminResource {
    private static AdminResource adminResourceInstance;
    private static ReservationService reservationService = ReservationService.getReservationService();
    private static CustomerService customerService = CustomerService.getCustomerService();

    private AdminResource(){}

    public AdminResource getAdminResource(){
        if(adminResourceInstance == null)
            return adminResourceInstance;
        return adminResourceInstance;
    }

    public Customer getCustomer(String email) throws EmailNotFoundException{
        return customerService.getCustomer(email);
    }

    public void addRoom(IRoom room){
        reservationService.addRoom(room);
    }

    public void addRooms(List<IRoom> rooms){
        for(IRoom room : rooms)
            reservationService.addRoom(room);
    }

    public Collection<IRoom> getAllRooms(){
        return reservationService.getAllRooms();
    }

    public Collection<Customer> getAllCustomers(){
        return customerService.getAllCustomers();
    }

    public void displayAllReservations(){
        reservationService.printAllReservations();
    }
}
