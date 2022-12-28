package com.springbootbackend.springbootbackend.controller;

import java.util.List;
import java.util.Optional;

import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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
import com.springbootbackend.springbootbackend.exceptions.EmailAlreadyRegistered;
import com.springbootbackend.springbootbackend.service.StudentService;

@RestController
@CrossOrigin("http://localhost:3000")
public class MyController {

	@Autowired
	@Qualifier("studentServiceimpl")
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
	public ResponseEntity<Object> addStudent(@RequestBody StudentDetails student) throws EmailAlreadyRegistered{
		if (studentService.emailExists(student.getStudentEmail())) {
//			Error error = new Error(HttpStatus.NOT_ACCEPTABLE);
//			return new ResponseEntity<>(error, error.getHttpStatus());
//			System.out.println("Email is already in use");
			return new ResponseEntity<>("Email is already in use",HttpStatus.NOT_ACCEPTABLE);
			//throw new EmailAlreadyRegistered("Email already registered.");

		}

			return new ResponseEntity<>(studentService.addStudent(student),HttpStatus.CREATED);
	}
	
	@PutMapping("/studentdetails")
	public StudentDetails updateStudentDetails(@RequestBody StudentDetails student) {
		return this.studentService.updateStudent(student);
		
	}
	
	
	@DeleteMapping("/studentdetails/{studentId}")
	public ResponseEntity<HttpStatus> deleteStudent(@PathVariable int studentId){
		try {
			this.studentService.deleteStudent(studentId);
			return new ResponseEntity<>(HttpStatus.OK);
		}
		catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
}
