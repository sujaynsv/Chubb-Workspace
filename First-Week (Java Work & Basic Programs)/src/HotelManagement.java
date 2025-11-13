import java.util.Scanner;

class Hotel {
    String name;
    double standardPrice;
    double deluxePrice;

    Hotel(String name, double standardPrice, double deluxePrice) {
        this.name = name;
        this.standardPrice = standardPrice;
        this.deluxePrice = deluxePrice;
    }
}

class Booking {
    String location;
    String hotel;
    String roomType;
    int qty;
    double amount;

    Booking(String location, String hotel, String roomType, int qty, double amount) {
        this.location = location;
        this.hotel = hotel;
        this.roomType = roomType;
        this.qty = qty;
        this.amount = amount;
    }
}

public class HotelManagement {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter your name: ");
        String customer = sc.nextLine().trim();

        String[] locations = {"Hyderabad", "Mumbai", "Delhi"};

        Hotel[][] hotels = {
                {new Hotel("Taj", 3000, 4500), new Hotel("Marriott", 2800, 4000), new Hotel("Novotel", 2500, 3500)},
                {new Hotel("Oberoi", 3500, 5000), new Hotel("Trident", 3200, 4800), new Hotel("Hilton", 3000, 4600)},
                {new Hotel("Leela", 3400, 4900), new Hotel("ITC", 3100, 4600), new Hotel("Radisson", 2800, 4200)}
        };

        Booking[] bookings = new Booking[100];
        int bookingCount = 0;
        boolean more = true;

        while (more) {
            System.out.println("\nAvailable locations:");
            for (int i = 0; i < locations.length; i++)
                System.out.println((i + 1) + ") " + locations[i]);
            System.out.print("Select location (1-3): ");
            int locChoice = sc.nextInt() - 1;

            System.out.println("\nHotels in " + locations[locChoice] + ":");
            for (int i = 0; i < hotels[locChoice].length; i++)
                System.out.println((i + 1) + ") " + hotels[locChoice][i].name);
            System.out.print("Select hotel (1-3): ");
            int hChoice = sc.nextInt() - 1;

            System.out.println("\nRoom types:");
            System.out.println("1) Standard - ₹" + hotels[locChoice][hChoice].standardPrice);
            System.out.println("2) Deluxe   - ₹" + hotels[locChoice][hChoice].deluxePrice);
            System.out.print("Select room type (1-2): ");
            int rChoice = sc.nextInt();
            sc.nextLine();

            String roomType = (rChoice == 1) ? "Standard" : "Deluxe";
            double price = (rChoice == 1) ? hotels[locChoice][hChoice].standardPrice : hotels[locChoice][hChoice].deluxePrice;

            System.out.print("Enter number of rooms: ");
            int qty = sc.nextInt();
            sc.nextLine(); 

            double amount = qty * price;
            bookings[bookingCount++] = new Booking(locations[locChoice], hotels[locChoice][hChoice].name, roomType, qty, amount);

            System.out.println("Booked " + qty + " " + roomType + " room(s) at " + hotels[locChoice][hChoice].name + " (" + locations[locChoice] + ")");
            System.out.printf("Amount: ₹%.0f%n", amount);

            System.out.print("Do you want to book another room? (yes/no): ");
            String ans = sc.nextLine().trim().toLowerCase();
            more = ans.equals("yes") || ans.equals("y");
        }

        System.out.println("\n===== Final Transaction Summary =====");
        double total = 0;
        for (int i = 0; i < bookingCount; i++) {
            Booking b = bookings[i];
            System.out.printf("%d) %s - %s - %s (%d rooms) - ₹%.0f%n",
                    i + 1, b.location, b.hotel, b.roomType, b.qty, b.amount);
            total += b.amount;
        }
        System.out.println("-------------------------------------");
        System.out.printf("Total amount payable: ₹%.0f%n", total);
        System.out.println("\nThank you for booking, " + customer + "!");
        sc.close();
    }
}
