package services;
import models.*;
import exceptions.*;
import java.util.*;

public class GradeTracker {
	public final Map<String, Student> students=new LinkedHashMap<>();
	public void addStudent(Student s) {
		students.put(s.getId(), s);
	}
	
	public Collection<Student> listStudents(){
		return Collections.unmodifiableCollection(students.values());
		
	}
	public Student findStudent(String id) throws StudentNotFoundException{
		Student s=students.get(id);
		if(s==null) throw new StudentNotFoundException("Student not found");
		return s;
	}
	
	public void addGrade(String studentId, String subject, double grade) throws StudentNotFoundException, InvalidGradeException{
		Student s= findStudent(studentId);
		s.addGrade(subject, grade);
	}
	
    public String studentReport(String studentId) throws StudentNotFoundException {
        Student s = findStudent(studentId);
        StringBuilder sb = new StringBuilder();
        sb.append("Report for ").append(s.getName()).append(" (").append(s.getId()).append(")\n");
        sb.append("-----------------------------------\n");
        if (!s.hasGrade()) {
            sb.append("No grades recorded yet.\n");
            return sb.toString();
        }
        for (Map.Entry<String, Double> e : s.getGrades().entrySet()) {
            sb.append(String.format("%-20s : %.2f\n", e.getKey(), e.getValue()));
        }
        sb.append("-----------------------------------\n");
        sb.append(String.format("Average : %.2f\n", s.average()));
        sb.append(String.format("Highest : %.2f\n", s.maxGrade()));
        sb.append(String.format("Lowest  : %.2f\n", s.minGrade()));
        return sb.toString();
    }
}
