import app.PaymentMain;
import exceptions.PaymentException;
import models.UPIPayment;
import java.util.*;
public class FirstProgram {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		PaymentMain p=new PaymentMain();
		UPIPayment upiBad = new UPIPayment(+500, "Sujay", "Merchant1", "1234");
        try {
            upiBad.processPayment();
            System.out.println("Payment Processed");
        } catch (PaymentException e) {
//        	upiBad.retry();
            System.out.println("LOG: " + e.getClass().getSimpleName() + " -> " + e.getMessage());
        }
		System.out.println("First Program");
		

	}
	public void addCustomers() {
//	    List<Customer> custList = new ArrayList<Customer>();
//	    
//	    for (int i = 0; ; i++) {
//	        Customer c6 = new Customer("Trump", "Trump@gmail.com", "43432432446", 30000, i + 10);
//	        custList.add(c6);
//	        try {
//	            Thread.sleep(40);
//	        } catch (InterruptedException e) {
//	            e.printStackTrace();
//	        }
//	    }
	}


}
