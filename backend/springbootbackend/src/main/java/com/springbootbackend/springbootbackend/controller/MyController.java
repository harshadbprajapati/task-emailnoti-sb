package com.springbootbackend.springbootbackend.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.springbootbackend.springbootbackend.StudentDetails.StudentDetails;
import com.springbootbackend.springbootbackend.exceptions.EmailNotValid;
import com.springbootbackend.springbootbackend.service.StudentService;

@RestController
@CrossOrigin("http://localhost:3000")
public class MyController {
	
	@Autowired
	private StudentService studentService;
	
	@GetMapping("/home")
	public String home() {
		
		return "This is home";
		
	}
	
	@GetMapping("/studentdetails")
	public List<StudentDetails> getAllStudentDetails() {
		return this.studentService.getAllStudentDetails();
		
	}
	
	@GetMapping("/studentdetails/{studentId}")
	public Optional<StudentDetails> getStudent(@PathVariable int studentId) {
		return this.studentService.getStudent(studentId);
		
	}
	
	

	@PostMapping("/studentdetails")
	public ResponseEntity<StudentDetails> addStudent(@RequestBody StudentDetails student) throws EmailNotValid{
		return new ResponseEntity<StudentDetails>(studentService.addStudent(student),HttpStatus.CREATED);
	}
	
	@PutMapping("/studentdetails")
	public StudentDetails updateStudentDetails(@RequestBody StudentDetails student) {
		return this.studentService.updateStudent(student);
		
	}
	
	
	@DeleteMapping("/studentdetails/{studentId}")
	public ResponseEntity<HttpStatus> deleteStudent(@PathVariable String studentId){
		try {
			this.studentService.deleteStudent(Integer.parseInt(studentId));
			return new ResponseEntity<>(HttpStatus.OK);
		}
		catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
}
