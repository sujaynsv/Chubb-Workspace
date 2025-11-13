package models;

import exceptions.*;

public class BankAccount {
    private final String accountNumber;
    private double balance;

    public BankAccount(String accountNumber, double initialBalance) throws InvalidDepositException {
        if (initialBalance < 0) throw new InvalidDepositException("Initial balance cannot be negative.");
        this.accountNumber = accountNumber;
        this.balance = initialBalance;
    }

    public void deposit(double amount) throws InvalidDepositException {
        if (amount <= 0) throw new InvalidDepositException("Deposit amount must be positive.");
        balance += amount;
    }

    public void withdraw(double amount) throws InsufficientFundsException {
        if (amount > balance) throw new InsufficientFundsException("Not enough funds to withdraw " + amount);
        balance -= amount;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    @Override
    public String toString() {
        return accountNumber + " | Balance: " + balance;
    }
}
