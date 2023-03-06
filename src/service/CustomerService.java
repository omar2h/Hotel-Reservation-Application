package service;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import exceptions.EmailNotFoundException;
import model.Customer;

public class CustomerService {
    private Map<String, Customer> customersData;
    private static CustomerService customerServiceInstance;

    private CustomerService(){
        customersData = new HashMap<>();
    }

    public static CustomerService getCustomerService(){
        if(customerServiceInstance == null){
            customerServiceInstance = new CustomerService();
        }
        return customerServiceInstance;
    }

    public void addCustomer(String email, String firstName, String lastName) throws IllegalArgumentException {
        if(customersData.containsKey(email))
            throw new IllegalArgumentException("Email already exists");
        
        customersData.put(email, new Customer(firstName, lastName, email));
    }

    public Customer getCustomer(String customerEmail) throws EmailNotFoundException {
        if(!customersData.containsKey(customerEmail))
            throw new EmailNotFoundException("Email not found: " + customerEmail);

        return customersData.get(customerEmail);
    }

    public Collection<Customer> getAllCustomers(){
        return customersData.values();
    }
}
