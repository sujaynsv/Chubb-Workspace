import java.util.Comparator;

public class SortByAccountNo implements Comparator<Account> {
    @Override
    public int compare(Account a1, Account a2) {
        if(a1.getAccountNo()>a2.getAccountNo()) {
        	return 1;
        }
        else {
        	return -1;
        }
    }
}
