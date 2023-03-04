package model;

import util.validator.EmailValidator;

public class Customer {
    private String firstName;
    private String lastName;
    private String email;

    public Customer(String firstName, String lastName, String email) throws IllegalArgumentException {
        EmailValidator emailValidator = new EmailValidator();
        if(!emailValidator.isValid(email)){
            throw new IllegalArgumentException("Invalid E-mail");
        }

        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public String getFirstName(){
        return firstName;
    }

    public String getLastName(){
        return lastName;
    }

    public String getEmail(){
        return email;
    }

    @Override
    public String toString(){
        return "First Name: " + getFirstName() + " Last Name: " + getLastName() + " E-mail: " + getEmail();
    }
}
