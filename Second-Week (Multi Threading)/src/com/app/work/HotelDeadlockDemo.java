package com.app.work;

public class HotelDeadlockDemo {

    static class Room {
        final int roomId;
        boolean booked = false;
        Room(int id) { this.roomId = id; }
        @Override public String toString() { return "Room#" + roomId; }
    }

    static class Account {
        final int accId;
        long balance;
        Account(int id, long balance) { this.accId = id; this.balance = balance; }
        @Override public String toString() { return "Account#" + accId; }
    }

    static class BookingTask implements Runnable {
        private final Room room;
        private final Account account;
        private final boolean lockRoomFirst; 
        private final String name;

        BookingTask(String name, Room room, Account account, boolean lockRoomFirst) {
            this.name = name;
            this.room = room;
            this.account = account;
            this.lockRoomFirst = lockRoomFirst;
        }

        @Override
        public void run() {
            try {
                if (lockRoomFirst) {
                    synchronized (room) {
                        System.out.println(name + " locked " + room);
                        Thread.sleep(50);
                        synchronized (account) {
                            System.out.println(name + " locked " + account);
                            doBooking();
                        }
                    }
                } else {
                    synchronized (account) {
                        System.out.println(name + " locked " + account);
                        Thread.sleep(50);
                        synchronized (room) {
                            System.out.println(name + " locked " + room);
                            doBooking();
                        }
                    }
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println(name + " interrupted");
            }
        }

        private void doBooking() throws InterruptedException {
            if (!room.booked && account.balance >= 100) {
                Thread.sleep(20);
                room.booked = true;
                account.balance -= 100;
                System.out.println(name + " successfully booked " + room + " using " + account);
            } else {
                System.out.println(name + " failed to book (already booked or insufficient funds)");
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Room sharedRoom = new Room(101);
        Account sharedAcc = new Account(501, 1000);

        // Two threads: one locks room first, the other locks account first -> possible deadlock
        Thread t1 = new Thread(new BookingTask("UserA", sharedRoom, sharedAcc, true));
        Thread t2 = new Thread(new BookingTask("UserB", sharedRoom, sharedAcc, false));

        t1.start();
        t2.start();

        // Wait a short time; if deadlocked, join will block forever
        t1.join(2000);
        t2.join(2000);

        System.out.println("Finished main (may be stuck if deadlocked). Room booked? " + sharedRoom.booked);
        System.out.println("Account balance: " + sharedAcc.balance);
    }
}
