package service;

import model.*;

import java.util.*;

public class ReservationService {
    private static ReservationService reservationService;
    private static Map<String, Room> allRooms; // String is room number
    private Collection<Room> rooms = new HashSet<Room>();
    private Collection<Reservation> reservations = new HashSet<Reservation>();

    private ReservationService() {}
    public static ReservationService getReservationServiceInstance() {
        if(reservationService == null) {
            reservationService = new ReservationService();
            allRooms = new HashMap<String, Room>();
        }
        return reservationService;
    }

    public void addRoom(String roomNumber, double price, RoomType roomType) {
        Room room = new Room(roomNumber, price, roomType);
        rooms.add(room);
        allRooms.put(room.getRoomNumber(), room);
    }

    public IRoom getARoom(String roomId) {
        for(IRoom room : rooms) {
            if (roomId.equals(room.getRoomNumber())) {
                return room;
            }
        }
        return null;
    }

    public Reservation reserveARoom(Customer customer, IRoom room, Date checkinDate, Date checkoutDate) {
        Reservation reservation = new Reservation(customer, room, checkinDate, checkoutDate);
        reservations.add(reservation);
        return  reservation;
    }

    public List<Room> findRooms(Date checkinDate, Date checkoutDate) {
        List<Room> availableRoomList = new ArrayList<Room>();
        Map<String, Room> availableRoomMap = new HashMap<String, Room>();
        availableRoomMap.putAll(allRooms); // Make a copy of our original room list

        // If we find a match for chekInDate or checkOutDate, that room is not available for this reservation
        for(Reservation reservation : reservations) {
            if((reservation.getCheckInDate().compareTo(checkinDate) == 0) || (reservation.getCheckOutDate().compareTo(checkoutDate) == 0)) {
                availableRoomMap.remove(reservation.getRoom().getRoomNumber());
            }
        }

        for(Room room : availableRoomMap.values()) {
            availableRoomList.add(room);
        }

        return availableRoomList;
    }

    public List<Room> getAllRooms() {
        List<Room> allRoomList = new ArrayList<Room>();
        for(Room room : allRooms.values()) {
            allRoomList.add(room);
        }
        return allRoomList;
    }
    public List<Reservation> getCustomerReservation(String email) {
        List<Reservation> customerReservationList = new ArrayList<Reservation>();

        for(Reservation reservation : reservations) {
            if (reservation.getCustomer().getEmail().equals(email)) {
                customerReservationList.add(reservation);
            }
        }
        return customerReservationList;
    }

    public void printAllReservation() {
        for(Reservation reservation : reservations) {
            System.out.println("Customer: " + reservation.getCustomer().getEmail() + " Room "
                                + reservation.getRoom().getRoomNumber() + " room type "
                                + reservation.getRoom().getRoomType() + " price "
                                + reservation.getRoom().getRoomPrice());
        }
    }


}
