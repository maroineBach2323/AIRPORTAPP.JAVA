import java.util.*;

public class AirportApp {
    static List<Passenger> passengers = new ArrayList<>();
    static List<Flight> flights = new ArrayList<>();


    static Scanner textScanner = new Scanner(System.in);

    static Scanner numberScanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("1. Add Passenger 2. Add Flight 3. Board Passenger 4. Print Flights 5. Exit");
            switch (numberScanner.nextInt()) {
                case 1 -> addPassenger();
                case 2 -> addFlight();
                case 3 -> boardPassenger();
                case 4 -> printFlights();
                case 5 -> System.exit(0);
            }
        }
    }


    static void addPassenger() {
        System.out.print("Name: ");
        String name = textScanner.next();
        System.out.print("Age: ");
        int age = numberScanner.nextInt();
        System.out.print("Address: ");
        String address = textScanner.next();
        System.out.print("Baggage Weight: ");
        double weight = numberScanner.nextDouble();

        if (weight <= 20) {
            passengers.add(new Passenger(name, age, address, weight));
            System.out.println("Passenger added.");
        } else {
            System.out.println("Baggage too heavy.");
        }
    }

    // Ajouter un vol
    static void addFlight() {
        System.out.print("Code: ");
        String code = textScanner.next();
        System.out.print("Destination: ");
        String dest = textScanner.next();
        System.out.print("Economy Seats: ");
        int econ = numberScanner.nextInt();
        System.out.print("Business Seats: ");
        int bus = numberScanner.nextInt();

        flights.add(new Flight(code, dest, econ, bus));
        System.out.println("Flight added.");
    }

    //
    static void boardPassenger() {
        System.out.print("Passenger Name: ");
        String name = textScanner.next();
        System.out.print("Flight Code: ");
        String code = textScanner.next();

        Passenger p = passengers.stream().filter(pass -> pass.name.equals(name)).findFirst().orElse(null);
        Flight f = flights.stream().filter(flight -> flight.flightCode.equals(code)).findFirst().orElse(null);

        if (p != null && f != null && (f.economySeats > 0 || f.businessSeats > 0)) {
            f.passengers.add(p);
            if (f.economySeats > 0) f.economySeats--; else f.businessSeats--;
            System.out.println("Passenger boarded.");
        } else {
            System.out.println("Boarding failed.");
        }
    }

    static void printFlights() {
        for (Flight f : flights) {
            System.out.println("Flight: " + f.flightCode + " Destination: " + f.destination);
            f.passengers.forEach(p -> System.out.println("- " + p.name));
        }
    }
}






