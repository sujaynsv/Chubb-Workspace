import java.util.*;

public class Main {
    public static void main(String[] args) {
        List<Account> accounts = new ArrayList<>();

        accounts.add(new Account("R", 102, "TXN12", "India", "HDFC0001", 55000.50));
        accounts.add(new Account("A", 101, "TXN09", "India", "SBI0002", 87000.75));
        accounts.add(new Account("X", 104, "TXN22", "India", "ICIC0003", 33000.20));
        accounts.add(new Account("C", 103, "TXN15", "India", "AXIS0004", 99000.00));

        System.out.println("Original List:");
        accounts.forEach(System.out::println);


        Collections.sort(accounts, new SortByAccountNo());
        System.out.println("\nSorted by Account No:");
        accounts.forEach(System.out::println);

        Collections.sort(accounts, new SortByBalance());
        System.out.println("\nSorted by Balance:");
        accounts.forEach(System.out::println);

        Collections.sort(accounts);
        System.out.println("\nSorted by Account Holder Name:");
        accounts.forEach(System.out::println);

        Account a1 = new Account("Aashish Choudhary", 102, "TXN12", "India", "HDFC0001", 55000.50);
        Account a2 = new Account("Aashish Choudhary", 102, "TXN30", "India", "HDFC0005", 10000.00);

        System.out.println("\nEquality Test:");
        System.out.println("a1.equals(a2): " + a1.equals(a2));
        System.out.println("a1.hashCode() == a2.hashCode(): " + (a1.hashCode() == a2.hashCode()));
    }
}
