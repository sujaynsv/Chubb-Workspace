import java.util.Comparator;

public class SortByBalance implements Comparator<Account> {
    @Override
    public int compare(Account a1, Account a2) {
        if(a1.getBalance()>a2.getBalance()) {
        	return 1;
        }
        else {
        	return -1;
        }
    }
}
