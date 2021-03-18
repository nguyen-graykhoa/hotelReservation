package model;

import java.util.Date;

public class Reservation {
    private Customer customer;
    private IRoom room;
    private Date checkInDate;
    private Date checkOutDate;

    public Reservation(Customer customer, IRoom room, Date checkInDate, Date checkOutDate) {
        this.customer = new Customer(customer.firstName, customer.lastName, customer.getEmail());
        this.room = new Room(room.getRoomNumber(), room.getRoomPrice(), room.getRoomType());
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
    }

    public String toString() {
        return "Customer first name " + customer.firstName + " last name "
                + customer.lastName + " check in "
                + checkInDate + " check out "
                + checkOutDate;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public IRoom getRoom() {
        return room;
    }

    public void setRoom(IRoom room) {
        this.room = room;
    }

    public Date getCheckInDate() {
        return checkInDate;
    }

    public void setCheckInDate(Date checkInDate) {
        this.checkInDate = checkInDate;
    }

    public Date getCheckOutDate() {
        return checkOutDate;
    }

    public void setCheckOutDate(Date checkOutDate) {
        this.checkOutDate = checkOutDate;
    }
}
