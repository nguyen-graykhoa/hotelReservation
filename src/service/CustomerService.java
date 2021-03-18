package service;

import model.Customer;

import java.util.*;

public class CustomerService {

    private static CustomerService customerService;

    public static List<Customer> customers = new ArrayList<Customer>();
    public static Map<String, Customer> mapOfCustomers = new HashMap<String, Customer>();

    private CustomerService() {
    }
    public void addCustomer(String firstName, String lastName, String email) {
        Customer customer = new Customer(firstName, lastName, email);
        customers.add(customer);
        mapOfCustomers.put(email, customer);
    }

    public Customer getCustomer(String customerEmail) {
        return mapOfCustomers.get(customerEmail);
    }

    public List<Customer> getAllCustomers() {
        return customers;
    }

    public static CustomerService getCustomerServiceInstance() {
        if(customerService == null) {
            customerService = new CustomerService();
        }
        return customerService;
    }
}
