package api;

import model.Customer;
import model.IRoom;
import model.Room;
import model.RoomType;
import service.CustomerService;
import service.ReservationService;

import java.util.ArrayList;
import java.util.List;

public class AdminResource {
    private static AdminResource adminResource;
    public static ReservationService reservationService = ReservationService.getReservationServiceInstance();;
    public static CustomerService customerService;

    private AdminResource() { }

    public static AdminResource getAdminResourceInstance() {
        if(adminResource == null) {
            adminResource = new AdminResource();
            //reservationService = ReservationService.getReservationServiceInstance();
            customerService = CustomerService.getCustomerServiceInstance();
        }

        return adminResource;
    }

    public Customer getCustomer(String email) {
        Customer customer = customerService.getCustomer(email);
        return customer;
    }

    public void addRooms(List<IRoom> rooms) {
        try {
            for(IRoom room : rooms) {
                reservationService.addRoom(room.getRoomNumber(), room.getRoomPrice(), room.getRoomType());
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void AddARoom(String roomNumber, double price, RoomType roomType) {
        reservationService.addRoom(roomNumber, price, roomType);
    }

    public List<Room> getAllRooms() {
        List<Room> roomList = reservationService.getAllRooms();
        return roomList;
    }

    public List<Customer> getAllCustomers() {
        List<Customer> allCustomers = new ArrayList<Customer>();
        allCustomers = customerService.getAllCustomers();
        return allCustomers;
    }
}
