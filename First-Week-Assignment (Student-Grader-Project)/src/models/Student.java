package models;
import java.util.*;
import exceptions.*;
public class Student extends Person{

	public Student(String name, String id) {
		super(name, id);
		// TODO Auto-generated constructor stub
	}
	private final Map<String, Double> grades=new LinkedHashMap<>();
	public void addGrade(String subject, double grade) throws InvalidGradeException{
		if(subject==null||subject.isBlank()) {
			throw new IllegalArgumentException("Subject cannot be blank.!");
		}
		if(grade<0||grade>100) {
			throw new InvalidGradeException("Grade must be between 0 & 100.");
			
		}
		grades.put(subject.trim(), grade);
	}
	public Map<String, Double> getGrades(){
		return Collections.unmodifiableMap(grades);
	}
	public double average() {
		if(grades.isEmpty()) return 0.0;
		double sum=0.0;
		for(double v: grades.values()) sum+=v;
		return sum/grades.size();
	}
	public double maxGrade() {
		if(grades.isEmpty()) return 0.0;
		double max=Double.NEGATIVE_INFINITY;
		for(double v: grades.values()) {
			if(v>max) max=v;
		}
		return max;
	}
	public double minGrade() {
		if(grades.isEmpty()) return 0.0;
		double min=Double.POSITIVE_INFINITY;
		for(double v: grades.values()) if(v<min) min=v;
		return min;
	}
	public boolean hasGrade() {
		return !grades.isEmpty();
	}
	
    public String toString() {
        return String.format("%s | Subjects: %d | Avg: %.2f", super.toString(), grades.size(), average());
    }
	
} 
