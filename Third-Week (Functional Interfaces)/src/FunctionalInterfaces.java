import java.util.function.*;
import java.util.*;
public class FunctionalInterfaces {
	public static void main(String[] args) {
		
	Predicate<Integer> isEven= n->n%2==0;
	
	System.out.println(isEven.test(10));
	System.out.println(isEven.test(15));
	
	Consumer<Integer> print= temp->System.out.println(temp);
	
    List<Integer> nums = Arrays.asList(5, 1, 4, 2, 3);

    System.out.println("Ascending:");
    nums.stream()
        .sorted()
        .forEach(System.out::println);
	}
	
}

