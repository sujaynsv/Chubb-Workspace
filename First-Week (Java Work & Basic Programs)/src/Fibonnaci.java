
import java.util.*;
public class Fibonnaci {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int a=0;
		int b=1;
		int temp=a+b;
		Scanner sc=new Scanner(System.in);
		int n=sc.nextInt();
		System.out.println(a);
		System.out.println(b);
		for(int i=2;i<n;i++) {
			temp=a+b;
			System.out.println(temp);
			a=b;
			b=temp;
		}
	}

}
