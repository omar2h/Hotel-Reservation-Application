package api;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import model.Customer;
import model.IRoom;

public class AdminResource {
    private static AdminResource adminResourceInstance;

    private AdminResource(){}

    public AdminResource getAdminResource(){
        if(adminResourceInstance == null)
            return adminResourceInstance;
        return adminResourceInstance;
    }

    public Customer getCustomer(String email){
        return new Customer("null", "null", "null");
    }

    public void addRoom(List<IRoom> rooms){
        ;
    }

    public Collection<IRoom> getAllRooms(){
        return (Collection<IRoom>) new HashMap();
    }

    public Collection<Customer> getAllCustomers(){
        return (Collection<Customer>) new HashMap();
    }

    public void displayAllReservations(){
        ;
    }
}
