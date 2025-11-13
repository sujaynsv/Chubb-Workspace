package com.app.work;

@FunctionalInterface
interface Operation {
	int perform(int a, int b);
}

public class LambdaTest{
	public static void main(String [] main) {
		Operation add=(a,b)->a+b;
		Operation subract=(a,b)->a-b;
		System.out.println("Sum: "+add.perform(4, 3));
		System.out.println("Subtraction: "+subract.perform(5,3));
		
	}
	

}
