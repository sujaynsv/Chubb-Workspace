import java.util.Objects;

public class Account implements Comparable<Account> {
    private String accountHolderName;
    private long accountNo;
    private String transCode;
    private String country;
    private String ifscCode;
    private double balance;

    public Account(String accountHolderName, long accountNo, String transCode, String country, String ifscCode, double balance) {
        this.accountHolderName = accountHolderName;
        this.accountNo = accountNo;
        this.transCode = transCode;
        this.country = country;
        this.ifscCode = ifscCode;
        this.balance = balance;
    }

    // Getters
    public String getAccountHolderName() {
        return accountHolderName;
    }

    public long getAccountNo() {
        return accountNo;
    }

    public String getTransCode() {
        return transCode;
    }

    public String getCountry() {
        return country;
    }

    public String getIfscCode() {
        return ifscCode;
    }

    public double getBalance() {
        return balance;
    }

    @Override
    public int compareTo(Account other) {
        return this.accountHolderName.compareToIgnoreCase(other.accountHolderName);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Account)) return false;
        Account account = (Account) o;
        return accountNo == account.accountNo &&
               Objects.equals(accountHolderName, account.accountHolderName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(accountHolderName, accountNo);
    }

    @Override
    public String toString() {
        return String.format("%s | %d | %s | %s | %s | %.2f",
                accountHolderName, accountNo, transCode, country, ifscCode, balance);
    }
}
