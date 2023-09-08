import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Train {
    private static int pnrCounter = 1;
    private static Map<String, Reservation> reservations = new HashMap<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nOnline Reservation System Menu:");
            System.out.println("1. Make a Reservation");
            System.out.println("2. Cancel a Reservation");
            System.out.println("3. Exit");
            System.out.print("Enter your choice (1/2/3): ");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    makeReservation(scanner);
                    break;
                case "2":
                    cancelReservation(scanner);
                    break;
                case "3":
                    System.out.println("Thank you for using the reservation system. Goodbye!");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void makeReservation(Scanner scanner) {
        System.out.print("Enter passenger name: ");
        String name = scanner.nextLine();
        System.out.print("Enter train number: ");
        String trainNumber = scanner.nextLine();
        System.out.print("Enter class type: ");
        String classType = scanner.nextLine();
        System.out.print("Enter date of journey: ");
        String date = scanner.nextLine();
        System.out.print("Enter from location: ");
        String fromLocation = scanner.nextLine();
        System.out.print("Enter to location: ");
        String toLocation = scanner.nextLine();

        String pnr = "P" + pnrCounter++;
        Reservation reservation = new Reservation(name, trainNumber, classType, date, fromLocation, toLocation);
        reservations.put(pnr, reservation);

        System.out.println("Reservation successful! Your PNR is: " + pnr);
    }

    private static void cancelReservation(Scanner scanner) {
        System.out.print("Enter PNR number to cancel reservation: ");
        String pnr = scanner.nextLine();

        if (reservations.containsKey(pnr)) {
            reservations.remove(pnr);
            System.out.println("Reservation with PNR " + pnr + " has been canceled.");
        } else {
            System.out.println("Reservation with PNR " + pnr + " not found.");
        }
    }
}

class Reservation {
    private String name;
    private String trainNumber;
    private String classType;
    private String date;
    private String fromLocation;
    private String toLocation;

    public Reservation(String name, String trainNumber, String classType, String date, String fromLocation, String toLocation) {
        this.name = name;
        this.trainNumber = trainNumber;
        this.classType = classType;
        this.date = date;
        this.fromLocation = fromLocation;
        this.toLocation = toLocation;
    }
}
