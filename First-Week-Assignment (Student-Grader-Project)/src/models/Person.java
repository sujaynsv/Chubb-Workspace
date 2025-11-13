package models;

public class Person {
	private final String name;
	private final String id;
	
	public Person(String name, String id) {
		if(name==null || name.isBlank()) throw new IllegalArgumentException("Name cannot be blank");
		if(id==null || id.isBlank()) throw new IllegalArgumentException("Id cannot be null");
		
		this.name=name;
		this.id=id;
	
	}
	public String getName() {
		return name;
		
	}
	public String getId() {
		return id;
		
	}
	
	@Override
	public String toString() {
		return name+" ("+id+" )";
	}
		
	
	

}
