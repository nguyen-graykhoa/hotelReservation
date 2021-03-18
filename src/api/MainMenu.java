package api;

import model.Customer;
import model.Reservation;
import model.Room;
import model.RoomType;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class MainMenu {
    static AdminResource adminResource = AdminResource.getAdminResourceInstance();
    static HotelResource hotelResource = HotelResource.getHotelResourceInstance();

    public static void main(String[] args) {
        displayMainMenu();
    }

    public static void displayMainMenu() {
        Scanner in = new Scanner(System.in);
        char choice = '0';
        while(choice != '5') {
            printScreenMainMenu();
            choice = in.next().charAt(0);
            switch ( choice ) {
                case '1':
                    System.out.println ( "Find and reserve a room" );
                    reserveARoom();
                    break;
                case '2':
                    System.out.println ( "See my reservation" );
                    break;
                case '3':
                    System.out.println ( "Create an account" );
                    createAnAccount();
                    break;
                case '4':
                    Scanner inner = new Scanner(System.in);
                    char innerChoice = '0';
                    while(innerChoice != '5') {
                        printScreenAdminMenu();
                        innerChoice = inner.next().charAt(0);
                        switch ( innerChoice ) {
                            case '1':
                                System.out.println("See all Customers");
                                displayAllCustomers();
                                break;
                            case '2':
                                System.out.println("See all Rooms");
                                displayAllRooms();
                                break;
                            case '3':
                                System.out.println("See all Reservations");
                                break;
                            case '4':
                                System.out.println("Add a Room");
                                AddARoom();
                                break;
                            case '5':
                                break;
                            default:
                                System.err.println("Unrecognized option");
                                break;
                        }
                    }
                    break;
                case '5':
                    System.out.println("Good bye");
                    break;
                default:
                    System.err.println ( "Unrecognized option" );
                    break;
            }
        }
    }

    public static void printScreenMainMenu() {
        System.out.println("       Main Menu    ");
        System.out.println("1. Find and reserve a room");
        System.out.println("2. See my reservations");
        System.out.println("3. Create an account");
        System.out.println("4. Admin");
        System.out.println("5. Exit");
        System.out.println("");
    }

    public static void printScreenAdminMenu() {
        System.out.println("     Admin Menu   ");
        System.out.println("1. See all Customers");
        System.out.println("2. See all Rooms");
        System.out.println("3. See all Reservations");
        System.out.println("4. Add a Room");
        System.out.println("5. Back to Main Menu");
        System.out.println("");
    }

    public static void createAnAccount() {
        String firstName = "", lastName = "", email = "";
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter your first name");
        firstName = scanner.nextLine();
        System.out.println("Enter your last name");
        lastName = scanner.nextLine();
        System.out.println("Enter your email");
        email = scanner.nextLine();
        hotelResource.createACustomer(firstName, lastName, email);
    }

    public static void displayAllCustomers() {
        List<Customer> customers = hotelResource.customerService.getAllCustomers();
        System.out.println("Customer List");
        System.out.println("First name| Last name| Email");
        for (Customer cust : customers) {
            System.out.println(cust.firstName + "    |    " + cust.lastName + "    |    " + cust.getEmail());
        }
        System.out.println("\n");
    }

    public static void AddARoom() {
        String roomNumber = "";
        Double price = 0.0;
        String roomType = "";
        RoomType type;
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the room number");
        roomNumber = scanner.nextLine();

        System.out.println("Enter room type SINGLE/DOUBLE");
        roomType = scanner.nextLine();

        System.out.println("Enter room price");
        price = scanner.nextDouble();

        if (roomType.equals("SINGLE")) {
            type = RoomType.SINGLE;
        } else if (roomType.equals("DOUBLE")) {
            type = RoomType.DOUBLE;
        } else {
            throw new IllegalArgumentException("Invalid room type");
        }
        if (isNotAValidInput(roomNumber) || price < 0) {
            throw new IllegalArgumentException("Invalid room number or price data input");
        }
        adminResource.AddARoom(roomNumber, price, type);
    }

    public static void getAllSeservationForACustomer() {
        Scanner in = new Scanner(System.in);
        System.out.println("All Reservations For A Customer");
        System.out.println("Please enter your email");
        String email = in.nextLine();
        List<Reservation> reservationsForACustomer =  hotelResource.reservationService.getCustomerReservation(email);
        for(Reservation r : reservationsForACustomer) {
            System.out.println(r.getRoom() + " | " + r.getCheckInDate() + " | " + r.getCheckOutDate());
        }
    }

    public static void displayAllRooms() {
        List<Room> rooms = adminResource.getAllRooms();
        System.out.println("Room # | Price | Type");
        for (Room r : rooms) {
            System.out.println(r.getRoomNumber() + "  | " + r.getRoomPrice() + " | " + r.getRoomType());
        }
    }

    public static void reserveARoom() {
        Scanner in = new Scanner(System.in);
        String checkInString = "";
        String checkOutString = "";

        System.out.println("Enter check in date: dd-MMM-yyyy");
        checkInString = in.nextLine();
        Date checkIn = convertDateFromString(checkInString);
        System.out.println(checkIn);

        System.out.println("Enter check out date: dd-MMM-yyyy");
        checkOutString = in.nextLine();
        Date checkOut = convertDateFromString(checkOutString);
    }

    public static Date convertDateFromString(String dateString) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy");
        dateString = "11-MAR-2040";
        Date returnDate = null;
        try {
            returnDate = formatter.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return returnDate;
    }

    public static boolean isNotAValidInput(String inputString) {
        boolean isNotValid = true;
        if (inputString == null || inputString.isEmpty()) {
            isNotValid = true;
        } else if (inputString != null && !inputString.isEmpty()) {
            isNotValid = false;
        }
        return isNotValid;
    }


}
