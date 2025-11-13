import models.BankAccount;
import exceptions.*;
import java.util.*;

public class BankApp {
    public static void main(String[] args) {
        try {
            BankAccount acc = new BankAccount("A101", 500);
            acc.deposit(200);
            acc.withdraw(1000); // will trigger InsufficientFundsException
        } catch (BankException e) {
            System.out.println("Transaction failed: " + e.getMessage());
        }
    }
}
