import models.*;
import exceptions.*;

import java.util.Scanner;

public class LibraryApp {
    private static final Library library = new Library();
    private static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        seedData();
        boolean exit = false;
        while (!exit) {
            printMenu();
            String choice = sc.nextLine().trim();
            try {
                switch (choice) {
                    case "1": listBooks(); break;
                    case "2": addBook(); break;
                    case "3": listMembers(); break;
                    case "4": addMember(); break;
                    case "5": borrowBook(); break;
                    case "6": returnBook(); break;
                    case "7": listBorrowed(); break;
                    case "0": exit = true; break;
                    default: System.out.println("Invalid choice");
                }
            } catch (BusinessException be) {
                System.out.println("Error: " + be.getMessage());
            } catch (Exception e) {
                System.out.println("Unexpected: " + e.getMessage());
            }
        }
        System.out.println("Goodbye!");
    }

    private static void printMenu() {
        System.out.println("\n=== Library Menu ===");
        System.out.println("1. List books");
        System.out.println("2. Add book");
        System.out.println("3. List members");
        System.out.println("4. Add member");
        System.out.println("5. Borrow book");
        System.out.println("6. Return book");
        System.out.println("7. List borrow records");
        System.out.println("0. Exit");
        System.out.print("Choose: ");
    }

    private static void seedData() {
        library.addBook(new Book("978-1", "Intro to Java", "Alice", 3));
        library.addBook(new Book("978-2", "Algorithms", "Bob", 2));
        library.addMember(new Member("Sujay", "M001", 3));
        library.addMember(new Member("Ravi", "M002", 2));
    }

    private static void listBooks() {
        System.out.println("\nBooks:");
        for (Book b : library.listBooks()) {
            System.out.println(b);
        }
    }

    private static void addBook() {
        System.out.print("ISBN: "); String isbn = sc.nextLine().trim();
        System.out.print("Title: "); String title = sc.nextLine().trim();
        System.out.print("Author: "); String author = sc.nextLine().trim();
        System.out.print("Copies: "); int copies = Integer.parseInt(sc.nextLine().trim());
        library.addBook(new Book(isbn, title, author, copies));
        System.out.println("Added book.");
    }

    private static void listMembers() {
        System.out.println("\nMembers:");
        for (Member m : library.listMembers()) {
            System.out.println(m);
        }
    }

    private static void addMember() {
        System.out.print("Name: "); String name = sc.nextLine().trim();
        System.out.print("Member ID: "); String id = sc.nextLine().trim();
        System.out.print("Limit: "); int limit = Integer.parseInt(sc.nextLine().trim());
        library.addMember(new Member(name, id, limit));
        System.out.println("Added member.");
    }

    private static void borrowBook() throws BusinessException {
        System.out.print("Member ID: "); String mid = sc.nextLine().trim();
        System.out.print("Book ISBN: "); String isbn = sc.nextLine().trim();
        library.borrowBook(mid, isbn);
        System.out.println("Borrowed successfully.");
    }

    private static void returnBook() throws BusinessException {
        System.out.print("Member ID: "); String mid = sc.nextLine().trim();
        System.out.print("Book ISBN: "); String isbn = sc.nextLine().trim();
        library.returnBook(mid, isbn);
        System.out.println("Returned successfully.");
    }

    private static void listBorrowed() {
        System.out.println("\nBorrow Records:");
        for (String r : library.listBorrowRecords()) {
            System.out.println(r);
        }
    }
}
