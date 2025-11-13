import java.util.*;
public class TestingVar {
    public static void main(String[] args) {
        var list = new ArrayList<String>();  
        list.add("Apple");
        list.add("Banana");

        for (var item : list) {
            System.out.println(item);
        }

    }
}
