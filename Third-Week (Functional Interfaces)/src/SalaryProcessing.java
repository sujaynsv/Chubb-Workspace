
import java.util.*;
import java.util.concurrent.*;

class Transaction {
    String fromAccount;
    String toAccount;
    double amount;

    public Transaction(String fromAccount, String toAccount, double amount) {
        this.fromAccount = fromAccount;
        this.toAccount = toAccount;
        this.amount = amount;
    }

    @Override
    public String toString() {
        return fromAccount + " -> " + toAccount + " : ₹" + amount;
    }
}

public class SalaryProcessing {
    public static void main(String[] args) {
        Queue<Transaction> transactions = new ConcurrentLinkedQueue<>();

        CompanyAccount company = new CompanyAccount(100000);

        transactions.add(new Transaction("Company", "Alice", 20000));
        transactions.add(new Transaction("Company", "Bob", 15000));
        transactions.add(new Transaction("Company", "Charlie", 30000));
        transactions.add(new Transaction("Company", "David", 10000));
        transactions.add(new Transaction("Company", "Eva", 25000));

        ExecutorService executor = Executors.newFixedThreadPool(3);

        for (int i = 0; i < 3; i++) {
            executor.submit(() -> {
                while (!transactions.isEmpty()) {
                    Transaction t = transactions.poll();
                    if (t != null) {
                        company.processTransaction(t);
                    }
                }
            });
        }

        executor.shutdown();

        System.out.println("\nAll transactions processed.");
        System.out.println("Final company balance: ₹" + company.getBalance());
    }
}

class CompanyAccount {
    private double balance;

    public CompanyAccount(double balance) {
        this.balance = balance;
    }

    public synchronized void processTransaction(Transaction t) {
        if (balance >= t.amount) {
            balance -= t.amount;
            System.out.println("Processed: " + t + " | Remaining balance: ₹" + balance);
        } else {
            System.out.println("Failed: " + t + " | Insufficient funds!");
        }
    }

    public double getBalance() {
        return balance;
    }
}
