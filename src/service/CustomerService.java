package service;

import java.util.Collection;
import java.util.HashMap;

import model.Customer;

public class CustomerService {
    private static CustomerService customerServiceInstance;

    private CustomerService(){}

    public static CustomerService getCustomerService(){
        if(customerServiceInstance == null){
            customerServiceInstance = new CustomerService();
        }
        return customerServiceInstance;
    }

    public void addCustomer(String email, String firstName, String lastName){
        ;
    }

    public Customer getCustomer(String customerEmail){
        return new Customer("dummy", "dummy", "dummy");
    }

    public Collection<Customer> getAllCustomers(){
        return (Collection<Customer>) new HashMap();
    }
}
