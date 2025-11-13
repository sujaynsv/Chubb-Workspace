package com.app.work;

public class OptionalEmailDemo {
    public static void main(String[] args) {
        Employee emp1 = new Employee("Aashish", "aashish@example.com");
        Employee emp2 = new Employee("Sujay", null);  // no email

        printEmail(emp1);
        printEmail(emp2);
    }

    // Use Optional to print email if present, else print default
    public static void printEmail(Employee emp) {
        System.out.println("Employee: " + emp.getName());
        String email = emp.getEmail().orElse("No Email Provided");
        System.out.println("Email: " + email);
        System.out.println("-------------------------");
    }
}