package model;

public class Tester {
    public static void main(String[] args) {
        //Customer customer = new Customer("first", "second", "j@domain.com");
        //System.out.println(customer.toString());
        Customer customer = new Customer("first", "second", "email");
        System.out.println(customer.isEmailValid(customer.getEmail()));
    }
}
