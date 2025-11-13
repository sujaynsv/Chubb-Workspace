package app;
import java.util.*;

public class Employees {
    public static void main(String[] args) {
    	Map<String, List<String>> companies = new HashMap<>();
    	companies.put("Google", Arrays.asList("Strfan","Damon","Klaus"));
    	companies.put("Microsoft", Arrays.asList("Sheldon","Leonard","Raj"));
    	companies.put("Apple", Arrays.asList("Steve","Spark","Katy"));
    	companies.put("Goldman Sachs", Arrays.asList("Jobs","devi","Hari"));
    	if(!companies.containsKey("Cognizant")) {
    		companies.put("Cognizant", Arrays.asList("Haris", "manish", "Ram"));
    	}
    	Iterator<Map.Entry<String, List<String>>> it=companies.entrySet().iterator();
    	
    	while(it.hasNext()) {
    		Map.Entry<String, List<String>> entry=it.next();
    		if(entry.getValue().contains("Ram")) {
    			System.out.println("Found Ram working in: "+entry.getKey());
    		}
    	}
    	List<String> cognizantemployees=companies.get("Cognizant");
    	if(cognizantemployees.contains("Ram")) {
    		System.out.println("Cogizant contains Ram.");
    	}
    	Iterator<Map.Entry<String, List<String>>> iterator2= companies.entrySet().iterator();
    	while(iterator2.hasNext()) {
    		Map.Entry<String, List<String>> entry= iterator2.next();
    		
    		System.out.println(entry.getKey() + " -> " + entry.getValue());
    		
    	}
    }
}
