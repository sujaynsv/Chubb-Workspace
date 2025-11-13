package com.app.work;

public class TransferDemo {

    static class Account {
        private final long id;
        private long balanceCents;

        public Account(long id, long initialBalanceCents) {
            this.id = id;
            this.balanceCents = initialBalanceCents;
        }

        public long getId() { return id; }

        public synchronized boolean withdrawIfSufficient(long amountCents) {
            if (amountCents <= 0) throw new IllegalArgumentException("amount must be > 0");
            if (balanceCents >= amountCents) {
                balanceCents -= amountCents;
                return true;
            } else {
                return false;
            }
        }

        public synchronized void deposit(long amountCents) {
            if (amountCents <= 0) throw new IllegalArgumentException("amount must be > 0");
            balanceCents += amountCents;
        }

        public synchronized long getBalanceCents() {
            return balanceCents;
        }

        @Override
        public String toString() {
            return "Account{" + id + ", balance=" + (balanceCents / 100.0) + "}";
        }
    }

    static class TransferTask implements Runnable {
        private final Account src;
        private final Account dest;
        private final long amountCents;
        private final String name;

        TransferTask(String name, Account src, Account dest, long amountCents) {
            this.name = name;
            this.src = src;
            this.dest = dest;
            this.amountCents = amountCents;
        }

        @Override
        public void run() {
            System.out.printf("%s: attempting transfer of %.2f from %d to %d%n",
                    name, amountCents / 100.0, src.getId(), dest.getId());

            boolean withdrawn = src.withdrawIfSufficient(amountCents);
            if (!withdrawn) {
                System.out.printf("%s: FAILED - insufficient funds in source (balance = %.2f)%n",
                        name, src.getBalanceCents() / 100.0);
                return;
            }

            dest.deposit(amountCents);

            System.out.printf("%s: SUCCESS - transferred %.2f. New balances: src=%.2f dest=%.2f%n",
                    name,
                    amountCents / 100.0,
                    src.getBalanceCents() / 100.0,
                    dest.getBalanceCents() / 100.0);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Account source = new Account(1001, 100_00L); 
        Account destA  = new Account(2001, 10_00L);  
        Account destB  = new Account(2002, 5_00L);  

        Thread user1 = new Thread(new TransferTask("User1", source, destA, 80_00L)); // ₹80
        Thread user2 = new Thread(new TransferTask("User2", source, destB, 30_00L)); // ₹30

        user1.start();
        user2.start();

        user1.join();
        user2.join();

        System.out.println("Final balances:");
        System.out.println("Source: " + source);
        System.out.println("Dest A : " + destA);
        System.out.println("Dest B : " + destB);
    }
}
