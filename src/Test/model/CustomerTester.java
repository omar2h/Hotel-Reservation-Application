package Test.model;

import model.Customer;

public class CustomerTester {
    public static void main(String[] args) {
        try {
            Customer customer = new Customer("omar", "hesham", "omar@gmail.com");
            System.out.println(customer);
        } catch(IllegalArgumentException e){
            System.out.println(e.getLocalizedMessage());
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
        }

        try {
            Customer customer2 = new Customer("omar", "hesham", "omargmail.com");
            System.out.println(customer2);
        } catch(IllegalArgumentException e){
            System.out.println(e.getLocalizedMessage());
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
        }
    }
}
