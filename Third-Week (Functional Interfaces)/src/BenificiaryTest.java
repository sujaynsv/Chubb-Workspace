
public class BenificiaryTest {
	
	public static void main(String[] args) {
		var b=new Benificiary("Sujay","1001","HDFC");
		
		System.out.println(b.name());
		System.out.println(b.Accno());
		System.out.println(b.bankName());
		
		var updateBenificiary=b.updateDetails("NewName", "1002", "Kotak");
		
		var updatedName=b.updatename("Changed_Name");
		
		var updatedAcc=b.updateAccno("1004");
		
		var updatedbankName=b.updatedbankName("SBI");
		
		b.display(b);
		
		updateBenificiary.display(updateBenificiary);
		updatedName.display(updatedName);
		updatedAcc.display(updatedAcc);
		
		
		
		
		
	}

}
