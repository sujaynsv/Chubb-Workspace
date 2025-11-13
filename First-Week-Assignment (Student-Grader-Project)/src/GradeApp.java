import models.Student;
import services.GradeTracker;
import exceptions.InvalidGradeException;
import exceptions.StudentNotFoundException;

import java.util.Scanner;

public class GradeApp {
    private static final GradeTracker tracker = new GradeTracker();
    private static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
//        seedData();
        boolean exit = false;
        while (!exit) {
            printMenu();
            String choice = sc.nextLine().trim();
            try {
                switch (choice) {
                    case "1": addStudent(); break;
                    case "2": addGrade(); break;
                    case "3": listStudents(); break;
                    case "4": viewReport(); break;
                    case "0": exit = true; break;
                    default: System.out.println("Invalid choice.");
                }
            } catch (StudentNotFoundException | InvalidGradeException ex) {
                System.out.println("Error: " + ex.getMessage());
            } catch (Exception ex) {
                System.out.println("Unexpected: " + ex.getMessage());
            }
        }
        System.out.println("Bye!");
    }

    private static void printMenu() {
        System.out.println("\n=== Student Grade Tracker ===");
        System.out.println("1. Add Student");
        System.out.println("2. Add Grade for Student");
        System.out.println("3. List Students");
        System.out.println("4. View Student Report");
        System.out.println("0. Exit");
        System.out.print("Choose: ");
    }

    private static void seedData() {
        tracker.addStudent(new Student("Alice", "S001"));
        tracker.addStudent(new Student("Bob", "S002"));
        try {
            tracker.addGrade("S001", "Math", 92);
            tracker.addGrade("S001", "Physics", 85.5);
            tracker.addGrade("S002", "Math", 76);
        } catch (Exception e) {
            // won't happen for seeded data
        }
    }

    private static void addStudent() {
        System.out.print("Name: ");
        String name = sc.nextLine();
        while (name.isBlank()) {  // keep asking if blank
            System.out.print("Name cannot be blank. Enter name: ");
            name = sc.nextLine();
        }

        System.out.print("ID  : ");
        String id = sc.nextLine();
        while (id.isBlank()) {
            System.out.print("ID cannot be blank. Enter ID: ");
            id = sc.nextLine();
        }

        tracker.addStudent(new Student(name, id));
        System.out.println("Student added successfully!");
    }


    private static void addGrade() throws StudentNotFoundException, InvalidGradeException {
        System.out.print("Student ID: "); String id = sc.nextLine().trim();
        System.out.print("Subject   : "); String subject = sc.nextLine().trim();
        System.out.print("Grade (0-100): "); double grade = Double.parseDouble(sc.nextLine().trim());
        tracker.addGrade(id, subject, grade);
        System.out.println("Grade recorded.");
    }

    private static void listStudents() {
        System.out.println("\nStudents:");
        for (var s : tracker.listStudents()) {
            System.out.println(s);
        }
    }

    private static void viewReport() throws StudentNotFoundException {
        System.out.print("Student ID: "); String id = sc.nextLine().trim();
        System.out.println("\n" + tracker.studentReport(id));
    }
}
