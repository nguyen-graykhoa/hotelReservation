package api;

import model.Customer;
import model.IRoom;
import model.Reservation;
import model.Room;
import service.CustomerService;
import service.ReservationService;

import java.util.Date;
import java.util.List;

public class HotelResource {
    private static HotelResource hotelResource;
    public ReservationService reservationService = ReservationService.getReservationServiceInstance();
    public CustomerService customerService = CustomerService.getCustomerServiceInstance();
    private HotelResource() {}
    protected static HotelResource getHotelResourceInstance() {
        if(hotelResource == null) {
            hotelResource = new HotelResource();
        }
        return hotelResource;
    }

    public Customer getCustomer(String email) {
        Customer customer = customerService.getCustomer(email);
        return customer;
    }

    public IRoom getRoom(String roomNumber) {
        IRoom room = reservationService.getARoom(roomNumber);
        return room;
    }

    public void createACustomer(String firstName, String lastName, String email) {
        customerService.addCustomer(firstName, lastName, email);
    }

    public Reservation bookARoom(String customerEmail, IRoom room, Date checkinDate, Date checkOutDate) {
        Customer customer = getCustomer(customerEmail);
        Reservation reservation = reservationService.reserveARoom(customer, room, checkinDate, checkOutDate);
        return reservation;
    }

    public List<Reservation> getCustomerReservation(String customerEmail) {
        Customer customer = getCustomer(customerEmail);
        List<Reservation> customerReservationList = reservationService.getCustomerReservation(customerEmail);
        return customerReservationList;
    }

    public List<Room> findARoom(Date checkIn, Date checkOut) {
        List<Room> roomList = reservationService.findRooms(checkIn, checkOut);
        return roomList;
    }
}
