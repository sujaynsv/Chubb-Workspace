import java.util.*;

class Car{
	String model;
	String Color;
	double cost;
	Car(String model, String Color, double cost){

		this.model=model;
		this.Color=Color;
		this.cost=cost;
	}
	
}
class Loan{
	double downpayment;
	double loanamt;
	double intrest;
	double tenure;
	Loan(double downpayment,double loanamt ,double intrest, double tenure){
		this.downpayment=downpayment;
		this.loanamt=loanamt;
		this.intrest=intrest;
		this.tenure=tenure;
	}
	double simpleIntrest() {
		return (loanamt*tenure*intrest)/100;
	}
	double totalPay() {
		return loanamt+simpleIntrest();
	}
	double calculateEMIAmount() {
		return (totalPay())/(12*tenure);
	}
	double compoundInrest() {
		return loanamt*Math.pow(1+intrest/100,tenure)-loanamt;
	}
}


public class CarLoanCalculator {

	

	public static void main(String[] args) {
		Scanner sc= new Scanner(System.in);
		Car delta= new Car("baleno-delta", "white", 800000);
		Car beta= new Car("baleno-beta", "red", 1000000);
		Car alpha= new Car("baleno-alpha", "blue", 1200000);
		
		System.out.println("Select cars from the available ones: ");
		System.out.println("1.baleno-delta");
		System.out.println("2.baleno-beta");
		System.out.println("3.baleno-alpha");
		System.out.println("Select from 1-3");
		int choice= sc.nextInt();
		Car selected=null;
		switch(choice) {
		case 1 : selected=delta; break;
		case 2 : selected=beta; break;
		case 3: selected=alpha; break;
		default: System.out.println("Invalid CHoice!"); System.exit(0);
		} 
		System.out.println("The selected car is: "+selected.model+" The color is: "+selected.Color+" And the cost is: "+selected.cost);
		System.out.println("Enter the downpayment: ");
		double amt=sc.nextDouble();
		System.out.println("Enter the Intrest amout: ");
		double intrest=sc.nextDouble();
		System.out.println("Enter the tenure: ");
		double tenure=sc.nextDouble();
		Loan loan=new Loan(amt,selected.cost-amt,intrest,tenure);
		
		System.out.println("Selected Loan & Downpayment options");
		System.out.println("Downpayment: "+loan.downpayment);
		System.out.println("Loan Amount: "+loan.loanamt);
		System.out.println("Tenure: "+loan.tenure);
		System.out.println("Inrest: "+loan.intrest);
		
		
		System.out.println("-----------------------------------------");
		
		System.out.println("Select the type of intrest(1. Simple Intrest) - (2.Compound Intrest): ");
		int type=sc.nextInt();
		if(type==1) {
	        System.out.println("Simple Interest: " + loan.simpleIntrest());
	        System.out.println("Total Payment after " + tenure + " years: " + loan.totalPay());
	        System.out.println("Monthly EMI: " + loan.calculateEMIAmount());
		}
		else {
	        System.out.println("Total Payment after " + tenure + " years: " + loan.totalPay());
	        System.out.println("Monthly EMI: " + loan.calculateEMIAmount());
	        System.out.println("Compound Intrest: "+loan.compoundInrest());
		}
        
		sc.close();
		
		
		// TODO Auto-generated method stub

	}

}
