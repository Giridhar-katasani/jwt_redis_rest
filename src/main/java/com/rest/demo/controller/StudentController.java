package com.rest.demo.controller;

import com.rest.demo.dao.StudentDaoImplementation;
import com.rest.demo.entity.Student;
import jakarta.persistence.EntityManager;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/test")
public class StudentController {

	@Autowired
	private StudentDaoImplementation studentDaoImplementation;

	@Autowired
	public EntityManager entityManager;

	public StudentController(EntityManager theManger) {
		this.entityManager = theManger;
	}

	@GetMapping("/readAllStudents")
	public List<Student> getStudent() {
		// Your logic to get all the students
		return studentDaoImplementation.getStudents();
	}

	@PostMapping("/saveStudent")
	public String saveStudent(@RequestBody Student s) {
		HttpHeaders headers = new HttpHeaders();
		headers.add("Access-Control-Allow-Origin", "*"); // All
		// Your logic to save the student
		System.out.println(s);
		studentDaoImplementation.saveStudent(s);
		return "hello";
	}

	@GetMapping("/readStudent/{id}")
	public ResponseEntity<Student> getStudentById(@PathVariable String id) {
		Student student = studentDaoImplementation.getStudentById(Integer.parseInt(id));
		return Objects.isNull(student) ? new ResponseEntity<>(HttpStatus.NOT_FOUND) : new ResponseEntity<>(HttpStatus.OK);
	}

	 @PutMapping("/updateStudent/{id}")
	 public ResponseEntity<Student> updateStudent(@PathVariable String id, @RequestBody Student entity) throws Exception {
	 	//TODO: process PUT request
		 HttpHeaders headers = new HttpHeaders();
	        headers.add("Access-Control-Allow-Origin", "*");
	    Student updatedStudent = studentDaoImplementation.updateStudent(entity, Integer.parseInt(id));
	 	return ResponseEntity.ok(updatedStudent);
	 }
	 
	 @DeleteMapping("/deleteStudent")
	 public ResponseEntity<String> deleteStudent(@RequestParam int id) {
		 studentDaoImplementation.deleteStudent(id);
		 return new ResponseEntity<String>("student deleted sucessfully", HttpStatus.OK);
	 }
}
