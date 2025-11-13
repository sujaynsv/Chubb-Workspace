record Account(String name, double balance) {

    public Account withBalance(double newBalance) {
        return new Account(name, newBalance);
    }
}


record TransferRequest(Account from, Account to, double amount) {}

public class FundTransferDemo {
    public static void main(String[] args) {

        Account a1 = new Account("Alice", 1000.0);
        Account a2 = new Account("Bob", 500.0);
        
        System.out.println("Before Transfer");
        System.out.println(a1.name()+" balance: "+a1.balance());
        System.out.println(a2.name()+" balance: "+a2.balance());

        TransferRequest req = new TransferRequest(a1, a2, 200.0);

        Account updatedFrom=a1.withBalance(req.from().balance() - req.amount());
        Account updatedTo=a2.withBalance(req.to().balance() + req.amount());

        System.out.println("After Transfer");
        System.out.println(a1.name()+" original amt: "+a1.balance());
        System.out.println(updatedFrom.name() + " balance: " + updatedFrom.balance());
        System.out.println(updatedTo.name() + " balance: "   + updatedTo.balance());
    }
}
