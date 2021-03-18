package model;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Customer {
    public String firstName;
    public String lastName;
    private String email;
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }



    public Customer(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        if (isEmailValid(email)) {
            this.email = email;
        }
    }

    public String toString() {
        return "Customer first name: " + firstName
                + " last name: " + lastName
                + " email: " + email;
    }

    public boolean isEmailValid(String email) {
        String emailRegex = "^(.+)@(.+).(.+)";
        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(email);
        boolean thisIsAValidEmail = true;

        if(matcher.find()) {
            return thisIsAValidEmail;
        } else {
            throw new IllegalArgumentException("Email does not match format name@domain.com");
        }

    }

}
