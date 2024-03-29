import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class hotel_reservation_system{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<Room> availableRooms = initializeRooms();
        List<Reservation> reservations = new ArrayList<>();

        System.out.println("Welcome to the ADITYA'S Hotel Reservation System!");

        while (true) {
            displayMenu();
            int choice = sc.nextInt();
            sc.nextLine(); // Consume the newline character

            switch (choice) {
                case 1:
                    displayAvailableRooms(availableRooms);
                    break;
                case 2:
                    makeReservation(sc, availableRooms, reservations);
                    break;
                case 3:
                    displayReservations(reservations);
                    break;
                case 4:
                    System.out.println("Thank you for using the ADITYA'S Hotel Reservation System. Goodbye!");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static List<Room> initializeRooms() {
        List<Room> rooms = new ArrayList<>();
        rooms.add(new Room("25", "Single", 20.0));
        rooms.add(new Room("26", "Double", 50.0));
        rooms.add(new Room("27", "Suite", 100.0));
        // Add more rooms as needed
        return rooms;
    }

    private static void displayMenu() {
        System.out.println("\nChoose an option:");
        System.out.println("1. Display Available Rooms");
        System.out.println("2. Make Reservation");
        System.out.println("3. Display Reservations");
        System.out.println("4. Exit");
        System.out.println();
    }

    private static void displayAvailableRooms(List<Room> availableRooms) {
        System.out.println("\nAvailable Rooms:");
        for (Room room : availableRooms) {
            System.out.println(room);
        }
    }

    private static void makeReservation(Scanner sc2, List<Room> availableRooms, List<Reservation> reservations) {
        displayAvailableRooms(availableRooms);
        System.out.print("Enter the room number you want to reserve: ");
        String roomNumber = sc2.nextLine();

        Room selectedRoom = findRoomByNumber(availableRooms, roomNumber);
        if (selectedRoom != null) {
            System.out.print("Enter your name: ");
            String guestName = sc2.nextLine();

            Reservation reservation = new Reservation(guestName, selectedRoom);
            reservations.add(reservation);
            availableRooms.remove(selectedRoom);

            System.out.println("Reservation successful! Booking details:\n" + reservation);
        } else {
            System.out.println("Invalid room number. Reservation failed.");
        }
    }

    private static void displayReservations(List<Reservation> reservations) {
        if (reservations.isEmpty()) {
            System.out.println("No reservations yet.");
        } else {
            System.out.println("\nAll Reservations:");
            for (Reservation reservation : reservations) {
                System.out.println(reservation);
            }
        }
    }

    private static Room findRoomByNumber(List<Room> rooms, String roomNumber) {
        for (Room room : rooms) {
            if (room.getRoomNumber().equals(roomNumber)) {
                return room;
            }
        }
        return null;
    }
}

class Room {
    private String roomNumber;
    private String category;
    private double price;

    public Room(String roomNumber, String category, double price) {
        this.roomNumber = roomNumber;
        this.category = category;
        this.price = price;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    @Override
    public String toString() {
        return "Room " + roomNumber + " (" + category + ") - $" + price + " per night";
    }
}

class Reservation {
    private String guestName;
    private Room reservedRoom;

    public Reservation(String guestName, Room reservedRoom) {
        this.guestName = guestName;
        this.reservedRoom = reservedRoom;
    }

    @Override
    public String toString() {
        return "Guest: " + guestName + "\nReserved Room: " + reservedRoom;
    }
}
