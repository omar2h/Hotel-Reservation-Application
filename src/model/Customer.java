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

    @Override
    public int hashCode(){
        return email.hashCode();
    }

    @Override
    public boolean equals(Object obj){
        if(this == obj)
            return true;
        if((obj == null) || obj.getClass() != this.getClass())
            return false;
        Customer tmp = (Customer)obj;
        return this.email == tmp.email || (email != null && email.equals(tmp.email));
    }
}
