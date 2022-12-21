package com.springbootbackend.springbootbackend.service;

import java.util.List;
import java.util.Optional;

import com.springbootbackend.springbootbackend.StudentDetails.StudentDetails;
import com.springbootbackend.springbootbackend.exceptions.EmailNotValid;

public interface StudentService {
	public List<StudentDetails> getAllStudentDetails();

	public Optional<StudentDetails> getStudent(int studentId);

	public StudentDetails addStudent(StudentDetails student) throws EmailNotValid;

	public StudentDetails updateStudent(StudentDetails student);

	public void deleteStudent(int parseInt);

	//public void deleteStudent(Integer parseInt);


	
}
