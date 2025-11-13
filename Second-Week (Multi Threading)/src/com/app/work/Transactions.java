package com.app.work;


import java.io.IOException;
import java.nio.file.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicLong;

public class Transactions {

    // --- domain classes ---
    static class Account {
        private final String id;
        private final String name;
        private long balanceCents;

        Account(String id, String name, long initialBalanceCents) {
            this.id = id;
            this.name = name;
            this.balanceCents = initialBalanceCents;
        }

        // synchronized check-and-withdraw
        public synchronized boolean withdrawIfSufficient(long amountCents) {
            if (amountCents <= 0) return false;
            if (balanceCents >= amountCents) {
                balanceCents -= amountCents;
                return true;
            }
            return false;
        }

        public synchronized void deposit(long amountCents) {
            if (amountCents <= 0) return;
            balanceCents += amountCents;
        }

        public synchronized long getBalanceCents() { return balanceCents; }

        @Override
        public String toString() {
            return id + "(" + name + ") Balance: " + String.format("%.2f", balanceCents / 100.0);
        }
    }

    static class Transaction {
        final String txId, empId, empName, empIfsc, txnCode;
        final long amountCents;
        Transaction(String txId, String empId, String empName, String empIfsc, long amountCents, String txnCode) {
            this.txId = txId; this.empId = empId; this.empName = empName; this.empIfsc = empIfsc;
            this.amountCents = amountCents; this.txnCode = txnCode;
        }
        @Override public String toString() {
            return txId + ":" + empName + " Rs" + String.format("%.2f", amountCents/100.0);
        }
    }

    // --- helper ---
    private static long rupeesToCents(double r) { return Math.round(r * 100); }

    // --- main ---
    public static void main(String[] args) throws Exception {
        String input = args.length > 0 ? args[0] : "transactions";

        // 1) read transactions (very simple parser)
        List<Transaction> txs = new ArrayList<>();
        try {
            List<String> lines = Files.readAllLines(Paths.get(input));
            for (String raw : lines) {
                String s = raw.trim();
                if (s.isEmpty() || s.startsWith("#")) continue;
                String[] t = s.split("\\s*,\\s*");
                if (t.length < 6) continue;
                String txId = t[0], empId = t[1], empName = t[2], empIfsc = t[3];
                double rupees = Double.parseDouble(t[4]);
                long cents = rupeesToCents(rupees);
                String code = t[5];
                txs.add(new Transaction(txId, empId, empName, empIfsc, cents, code));
            }
        } catch (IOException e) {
            System.err.println("Failed to read " + input + " — exiting.");
            return;
        }

        if (txs.isEmpty()) {
            System.out.println("No transactions found in " + input);
            return;
        }

        // 2) create company and employee accounts
        Account company = new Account("COMPANY", "Acme Corp", rupeesToCents(200_000)); // ₹200,000
        Map<String, Account> employees = new ConcurrentHashMap<>();
        for (Transaction tx : txs) {
            employees.putIfAbsent(tx.empId, new Account(tx.empId, tx.empName, 0L));
        }

        // 3) simple concurrent processing with thread pool
        int pool = Math.min(txs.size(), Math.max(2, Runtime.getRuntime().availableProcessors()));
        ExecutorService exec = Executors.newFixedThreadPool(pool);

        AtomicLong totalProcessed = new AtomicLong(0);
        AtomicLong totalFailed = new AtomicLong(0);
        CountDownLatch latch = new CountDownLatch(txs.size());

        for (Transaction tx : txs) {
            exec.submit(() -> {
                try {
                    // rule: amount > 0
                    if (tx.amountCents <= 0) {
                        System.out.println(tx.txId + " SKIPPED invalid amount: " + tx);
                        totalFailed.addAndGet(Math.max(0, tx.amountCents));
                        return;
                    }

                    // withdraw from company (synchronized)
                    boolean ok = company.withdrawIfSufficient(tx.amountCents);
                    if (!ok) {
                        System.out.println(tx.txId + " SKIPPED insufficient funds for " + tx);
                        totalFailed.addAndGet(tx.amountCents);
                        return;
                    }

                    // deposit to employee
                    Account emp = employees.get(tx.empId);
                    emp.deposit(tx.amountCents);

                    System.out.println(tx.txId + " PROCESSED -> " + tx);
                    totalProcessed.addAndGet(tx.amountCents);
                } finally {
                    latch.countDown();
                }
            });
        }

        latch.await();          // wait for all tasks
        exec.shutdown();

        // 4) summary (simple)
        System.out.println("\n=== SUMMARY ===");
        System.out.printf("Company final balance: %.2f%n", company.getBalanceCents()/100.0);
        System.out.printf("Total processed: %.2f%n", totalProcessed.get()/100.0);
        System.out.printf("Total failed: %.2f%n", totalFailed.get()/100.0);
        System.out.println("\nEmployee balances:");
        employees.values().forEach(a -> System.out.println("  " + a));
    }
}
