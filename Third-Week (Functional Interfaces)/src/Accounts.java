public class Accounts {
    private int accountNumber;
    private String name;

    public Accounts(int accountNumber, String name) {
        this.accountNumber = accountNumber;
        this.name = name;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return accountNumber + " - " + name;
    }
}
