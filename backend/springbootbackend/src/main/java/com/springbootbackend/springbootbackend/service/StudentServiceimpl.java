package com.springbootbackend.springbootbackend.service;

import java.util.List;


import java.util.Optional;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.springbootbackend.springbootbackend.Email.EmailRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springbootbackend.springbootbackend.StudentDetails.StudentDetails;
import com.springbootbackend.springbootbackend.dao.StudentDao;

@Service("studentServiceimpl")
public class StudentServiceimpl implements StudentService {

	@Autowired
	private StudentDao studentDao;

	@Autowired
	private KafkaProducerService producer;

	@Override
	public StudentDetails addStudent(StudentDetails student){
		try {
			studentDao.save(student);
			EmailRequest email=new EmailRequest();
			email.setSubject("Successful registration!!");
			email.setTo(student.getStudentEmail());
			email.setBody(student.getStudentName()+" have registered successfully!!");
			producer.sendEmailRequest(email);
			System.out.println("Entry successfull");
		} catch (JsonProcessingException e) {
			throw new RuntimeException(e);
		}
		return null;
	}
	@Override
	public List<StudentDetails> getAllStudentDetails() {
		// TODO Auto-generated method stub
		return studentDao.findAll();
	}

	@Override
	public Optional<StudentDetails> getStudent(String studentEmail) {
		// TODO Auto-generated method stub
		return studentDao.findById(studentEmail);
	}


	@Override
	public StudentDetails updateStudent(StudentDetails student) {
		// TODO Auto-generated method stub
		
		StudentDetails newStudent=new StudentDetails(student.getStudentName(),student.getStudentContactNumber(), student.getStudentEmail());
		
		return studentDao.save(newStudent);
	}



	@Override
	public void deleteStudent(String studentEmail) {
		// TODO Auto-generated method stub
		StudentDetails toDelete=studentDao.getReferenceById(studentEmail);
		studentDao.delete(toDelete);
	}

	@Override
	public boolean emailExists(String studentEmail) {
		return studentDao.findByEmail(studentEmail) != null;
	}
}





