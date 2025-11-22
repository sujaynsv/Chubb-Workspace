package com.MongoSpring.MongoSpring.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.MongoSpring.MongoSpring.Model.Student;
import com.MongoSpring.MongoSpring.Repository.StudentRepo;


@RestController
public class MainController {
	
	@Autowired
	StudentRepo studentRepo;
	
	@PostMapping("/addStudent")
	public int addStudent(@RequestBody Student student) {
		
		return studentRepo.save(student).getRno(); 
		
	}
	
	@GetMapping("/fetchStudents")
	public List<Student> fetchStudents() {
		return studentRepo.findAll();
	}
	
	@GetMapping("/getStudent/{id}")
	public Student getStudent(@PathVariable Integer id){
		return studentRepo.findById(id).orElse(null);
	}
	
	@PutMapping("/updateStudent")
		public void updateStudent(@RequestBody Student student) {
			Student data=studentRepo.findById(student.getRno()).orElse(null);
			System.out.println(data);
			if(data!=null) {
				data.setName(student.getName());
				data.setAddreess(student.getAddreess());
				studentRepo.save(data);
			}
		}
	
	@DeleteMapping("/deleteStudent/{id}")
	public void deleteStudent(@PathVariable Integer id) {
		studentRepo.deleteById(id);
	}
	
}
