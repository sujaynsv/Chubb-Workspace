//public class Handling {
//    public static void main(String[] args) {
//        System.out.println("2. Try with Multiple Catch Blocks:");
//        
//        try {
//            int[] numbers = {1, 2, 3};
//            System.out.println("Accessing array element...");
//            System.out.println(numbers[5]); 
//            
//            int result = 10 / 0;
//            System.out.println(result);
//            
//        } catch (ArithmeticException ex) {
//            System.out.println("Caught ArithmeticException: " + ex.getMessage());
//            
//        } catch (ArrayIndexOutOfBoundsException ex) {
//            System.out.println("Caught ArrayIndexOutOfBoundsException: " + ex.getMessage());
//            
//        } catch (Exception ex) {
//            System.out.println("Caught general Exception: " + ex.getMessage());
//        }
//        
//        System.out.println("Program continues...");
//    }
//}
//import java.io.*;
//
//public class Handling {
//    public static void main(String[] args) {
//        
//        try {
//           
//            FileReader file = new FileReader("new.txt");
//            System.out.println("File opened successfully");
//            
//        } catch (FileNotFoundException ex) {
//            System.out.println("Error: File not found!");
//            System.out.println("Exception message: " + ex.getMessage());
//        }
//        
//    }
//}
//import java.io.*;
//
//public class Handling {
//    public static void main(String[] args) {
//        System.out.println("5. IOException:");
//        
//        BufferedReader reader = null;
//        
//        try {
//            reader = new BufferedReader(new FileReader("test.txt"));
//            String line = reader.readLine();  
//            System.out.println("File content: " + line);
//            
//        } catch (IOException ex) {
//            System.out.println("Error: IO operation failed!");
//            System.out.println("Exception message: " + ex.getMessage());
//            
//        }
//
//    }
//}

//class InvalidAgeException extends Exception{
//	InvalidAgeException(String message){
//		super(message);
//	}
//	
//}
//
//class Handling{
//	public static void validateage(int age) throws InvalidAgeException{
//		if(age<18) {
//			throw new InvalidAgeException("Age must be above 18");
//		}
//	}
//	public static void main(String[] args) {
//		try {
//			int age=17;
//			validateage(age);
//		}
//		catch(InvalidAgeException ex) {
//			ex.printStackTrace();
//		}
//	}
//}
class InvalidAgeException extends RuntimeException{
	InvalidAgeException(String message){
		super(message);
	}
	
}

class Handling{
	public static void validateage(int age) throws InvalidAgeException{
		if(age<18) {
			throw new InvalidAgeException("Age must be above 18");
		}
	}
	public static void main(String[] args) {
		try {
			int age=17;
			validateage(age);
		}
		catch(InvalidAgeException ex) {
			ex.printStackTrace();
		}
	}
}
